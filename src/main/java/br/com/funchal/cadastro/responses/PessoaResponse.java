package br.com.funchal.cadastro.responses;

import br.com.funchal.cadastro.models.Contato;
import br.com.funchal.cadastro.models.Pessoa;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class PessoaResponse {

    private Long id;
    private String nome;
    private String cpf;
    private LocalDate dataDeNascimento;
    private List<Contato> contatos;

    public PessoaResponse(Pessoa pessoa) {
        this.id = pessoa.getId();
        this.nome = pessoa.getNome();
        this.cpf = pessoa.getCpf();
        this.dataDeNascimento = pessoa.getDataDeNascimento();
        this.contatos = pessoa.getContatos();
    }

}
