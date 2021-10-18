package br.com.funchal.cadastro.services;

import br.com.funchal.cadastro.dtos.PessoaDTO;
import br.com.funchal.cadastro.models.Contato;
import br.com.funchal.cadastro.models.Pessoa;
import br.com.funchal.cadastro.repositorys.PessoaRepository;
import br.com.funchal.cadastro.responses.PessoaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository repository;

    public PessoaService(PessoaRepository repository) {
        this.repository = repository;
    }

    public PessoaResponse buscaPessoa(Long id) throws Exception {
        Optional<Pessoa> pessoa = repository.findById(id);
        return retornaResponse(verificaExistencia(pessoa));
    }

    public PessoaResponse cadastraUmaPessoa(PessoaDTO dto) {
        return retornaResponse(repository.save(retornaEntidade(dto)));
    }

    public PessoaResponse editaPessoa(PessoaDTO dto, Long id) throws Exception {
        Optional<Pessoa> pessoaEncontrada = repository.findById(id);
        Pessoa pessoa = verificaExistencia(pessoaEncontrada);
        pessoa.setNome(dto.getNome());
        pessoa.setCpf(dto.getCpf());
        pessoa.setDataDeNascimento(dto.getDataDeNascimento());
        return retornaResponse(repository.save(pessoa));
    }

    public PessoaResponse deletaPessoa(Long id) throws Exception {
        Optional<Pessoa> pessoa = repository.findById(id);
        repository.delete(verificaExistencia(pessoa));
        return retornaResponse(pessoa.get());
    }

    private Pessoa retornaEntidade(PessoaDTO dto){
        return new Pessoa(dto);
    }

    private PessoaResponse retornaResponse(Pessoa pessoa) {
        return new PessoaResponse(pessoa);
    }

    private Pessoa verificaExistencia(Optional<Pessoa> pessoa) throws Exception {
        if(pessoa.isPresent()) return pessoa.get();
        else throw new Exception("Pessoa não encontrada no banco de dados");
    }

    public void adicionaContato(Contato contato, Long id) throws Exception {
        Optional<Pessoa> pessoaEncontrada = repository.findById(id);
        Pessoa pessoa = verificaExistencia(pessoaEncontrada);
        pessoa.adicionaContato(contato);
        repository.save(pessoa);
    }

    public Page<PessoaResponse> buscaListaPessoas(Pageable paginacao) {
        Page<Pessoa> pessoas = repository.findAll(paginacao);
        return pessoas.map(PessoaResponse::new);
    }

    public Page<PessoaResponse> buscaListaPessoas(String nome, Pageable paginacao) {
        Page<Pessoa> pessoas = repository.findByNomeContainingIgnoreCase(nome, paginacao);
        return pessoas.map(PessoaResponse::new);
    }

    public void removeContato(Long id, Contato contato) throws Exception {
        Optional<Pessoa> pessoaEncontrada = repository.findById(id);
        Pessoa pessoa = verificaExistencia(pessoaEncontrada);
        pessoa.removeContato(contato);
        repository.save(pessoa);
    }
}
