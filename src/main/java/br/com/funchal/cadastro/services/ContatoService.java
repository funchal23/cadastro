package br.com.funchal.cadastro.services;

import br.com.funchal.cadastro.dtos.ContatoDTO;
import br.com.funchal.cadastro.dtos.PessoaDTO;
import br.com.funchal.cadastro.models.Contato;
import br.com.funchal.cadastro.models.Pessoa;
import br.com.funchal.cadastro.repositorys.ContatoRepository;
import br.com.funchal.cadastro.repositorys.PessoaRepository;
import br.com.funchal.cadastro.responses.ContatoResponse;
import br.com.funchal.cadastro.responses.PessoaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository repository;

    @Autowired
    private PessoaService pessoaService;

    public Contato cadastraUmContato(ContatoDTO dto) {
        return repository.save(retornaEntidade(dto));
    }

    public ContatoResponse editaContato(ContatoDTO dto, Long id) {
        Optional<Contato> contatoEncontrado = repository.findById(id);
        Contato contato = verificaExistencia(contatoEncontrado);
        contato.setNome(dto.getNome());
        contato.setEmail(dto.getEmail());
        contato.setNumeroDeTelefone(dto.getNumeroDeTelefone());
        return retornaResponse(repository.save(contato));
    }

    public ContatoResponse deletaContato(Long id, Long idPessoa) throws Exception {
        Optional<Contato> contato = repository.findById(id);
        pessoaService.removeContato(idPessoa, verificaExistencia(contato));
        repository.delete(contato.get());
        return retornaResponse(contato.get());
    }

    public ContatoResponse buscaContato(Long id) {
        Optional<Contato> contato = repository.findById(id);
        return retornaResponse(verificaExistencia(contato));
    }

    private Contato retornaEntidade(ContatoDTO dto){
        return new Contato(dto);
    }

    private ContatoResponse retornaResponse(Contato contato) {
        return new ContatoResponse(contato);
    }
    private Contato verificaExistencia(Optional<Contato> contato) {
        if(contato.isPresent()) return contato.get();
        else return null;
    }
}
