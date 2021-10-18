package br.com.funchal.cadastro.models;

import br.com.funchal.cadastro.dtos.ContatoDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String numeroDeTelefone;
    private String email;

    public Contato(ContatoDTO dto) {
        this.nome = dto.getNome();
        this.email = dto.getEmail();
        this.numeroDeTelefone = dto.getNumeroDeTelefone();
    }

    public Contato(){}
}
