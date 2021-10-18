package br.com.funchal.cadastro.models;

import br.com.funchal.cadastro.dtos.PessoaDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String cpf;
    private LocalDate dataDeNascimento;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Contato> contatos = new ArrayList<>();

    public Pessoa(PessoaDTO dto) {
        this.nome = dto.getNome();
        this.cpf = dto.getCpf();
        this.dataDeNascimento = dto.getDataDeNascimento();
    }

    public Pessoa(){}

    public void adicionaContato(Contato contato) {
        this.contatos.add(contato);
    }

    public void removeContato(Contato contato) {
        this.contatos.remove(contato);
    }
}
