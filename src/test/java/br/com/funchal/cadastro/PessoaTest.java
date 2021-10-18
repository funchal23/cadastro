package br.com.funchal.cadastro;

import br.com.funchal.cadastro.dtos.PessoaDTO;
import br.com.funchal.cadastro.models.Contato;
import br.com.funchal.cadastro.models.Pessoa;
import br.com.funchal.cadastro.repositorys.PessoaRepository;
import br.com.funchal.cadastro.responses.PessoaResponse;
import br.com.funchal.cadastro.services.PessoaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

public class PessoaTest {

    private PessoaService service;

    @Mock
    private PessoaRepository repository;

    @BeforeEach
    public void beforeEach(){
        MockitoAnnotations.openMocks(this);
        this.service = new PessoaService(repository);
    }

    @Test
    void testaBuscaPessoaComSucesso() throws Exception {
        Optional<Pessoa> pessoa = Optional.of(retornaUmaPessoa());
        Mockito.when(repository.findById(1l)).thenReturn(pessoa);
        PessoaResponse response = this.service.buscaPessoa(1l);
        Assertions.assertEquals(response.getNome(), pessoa.get().getNome());
        Assertions.assertEquals(response.getId(), 1l);
        Assertions.assertEquals(response.getCpf(), pessoa.get().getCpf());
        Assertions.assertEquals(response.getDataDeNascimento(), pessoa.get().getDataDeNascimento());
    }

    @Test
    void deveFalharAoBuscarPessoa() throws Exception {
        Optional<Pessoa> pessoa = Optional.empty();
        Mockito.when(repository.findById(1l)).thenReturn(pessoa);
        Assertions.assertThrows(Exception.class, () -> this.service.buscaPessoa(1l));
    }

    @Test
    void deveTerSucessoAoCadastrarUmaPessoa(){
        Mockito.when(repository.save(Mockito.any(Pessoa.class))).thenReturn(retornaUmaPessoa());
        PessoaResponse response = this.service.cadastraUmaPessoa(retornaUmaPessoaDTO());
        Assertions.assertEquals(response.getNome(), retornaUmaPessoaDTO().getNome());
        Assertions.assertEquals(response.getCpf(), retornaUmaPessoaDTO().getCpf());
        Assertions.assertEquals(response.getDataDeNascimento(), retornaUmaPessoaDTO().getDataDeNascimento());
    }

    @Test
    void deveTerSucessoAoEditarContato() throws Exception {
        Optional<Pessoa> pessoa = Optional.of(retornaUmaPessoa());
        Mockito.when(repository.findById(1l)).thenReturn(pessoa);
        Pessoa pessoaEditada = retornaUmaPessoa();
        pessoaEditada.setNome("Lucas Silva");
        Mockito.when(repository.save(Mockito.any(Pessoa.class))).thenReturn(pessoaEditada);
        PessoaResponse response = this.service.editaPessoa(retornaUmaPessoaDTOParaEditar(), 1l);
        Assertions.assertEquals(response.getNome(), retornaUmaPessoaDTOParaEditar().getNome());
    }

    @Test
    void deveTerSucessoParaAdicionarContato() throws Exception {
        Optional<Pessoa> pessoa = Optional.of(retornaUmaPessoa());
        Mockito.when(repository.findById(1l)).thenReturn(pessoa);
        Mockito.when(repository.save(Mockito.any(Pessoa.class))).thenReturn(retornaUmaPessoa());
        this.service.adicionaContato(retornaContato(), 1l);
        Assertions.assertEquals(pessoa.get().getContatos().size(), 1);
    }

    @Test
    void deveTerSucessoParaRemoverContato() throws Exception {
        Optional<Pessoa> pessoa = Optional.of(retornaPessoaComContatos());
        Contato contato = retornaContato();
        pessoa.get().adicionaContato(contato);
        Mockito.when(repository.findById(1l)).thenReturn(pessoa);
        Mockito.when(repository.save(Mockito.any(Pessoa.class))).thenReturn(retornaUmaPessoa());
        this.service.removeContato(1l, contato);
        Assertions.assertEquals(pessoa.get().getContatos().size(), 1);
    }

    @Test
    void deveFalharAoAtualizar(){
        Optional<Pessoa> pessoa = Optional.empty();
        Mockito.when(repository.findById(1l)).thenReturn(pessoa);
        Assertions.assertThrows(Exception.class, () -> this.service.editaPessoa(retornaUmaPessoaDTOParaEditar(),1l));
    }

    @Test
    void deveFalharAoExcluir(){
        Optional<Pessoa> pessoa = Optional.empty();
        Mockito.when(repository.findById(1l)).thenReturn(pessoa);
        Assertions.assertThrows(Exception.class, () -> this.service.deletaPessoa(1l));
    }

    @Test
    void deveTerSucessoAoDeletar() throws Exception {
        Optional<Pessoa> pessoa = Optional.of(retornaUmaPessoa());
        Mockito.when(repository.findById(1l)).thenReturn(pessoa);
        PessoaResponse response = this.service.deletaPessoa(1l);
        Mockito.verify(repository).delete(pessoa.get());
        Assertions.assertEquals(response.getNome(), pessoa.get().getNome());
        Assertions.assertEquals(response.getCpf(), pessoa.get().getCpf());
    }

    private Pessoa retornaUmaPessoa(){
        Pessoa pessoa = new Pessoa();
        pessoa.setId(1l);
        pessoa.setCpf("46999413894");
        pessoa.setNome("Lucas Funchal da Silva");
        pessoa.setDataDeNascimento(LocalDate.of(1998, 03, 20));
        return pessoa;
    }

    private PessoaDTO retornaUmaPessoaDTO(){
        PessoaDTO dto = new PessoaDTO();
        dto.setNome("Lucas Funchal da Silva");
        dto.setDataDeNascimento(LocalDate.of(1998, 03, 20));
        dto.setCpf("46999413894");
        return dto;
    }

    private PessoaDTO retornaUmaPessoaDTOParaEditar(){
        PessoaDTO dto = new PessoaDTO();
        dto.setNome("Lucas Silva");
        dto.setDataDeNascimento(LocalDate.of(1998, 03, 20));
        dto.setCpf("46999413894");
        return dto;
    }

    private Contato retornaContato(){
        Contato contato = new Contato();
        contato.setNome("Evellen");
        contato.setEmail("evellen@gmail.com");
        contato.setNumeroDeTelefone("18996956058");
        return contato;
    }

    private Pessoa retornaPessoaComContatos(){
        Contato contato = new Contato();
        contato.setNome("Matheus");
        contato.setEmail("matheus@gmail.com");
        contato.setNumeroDeTelefone("18996707070");
        Pessoa pessoa = new Pessoa();
        pessoa.adicionaContato(contato);
        return pessoa;
    }

}
