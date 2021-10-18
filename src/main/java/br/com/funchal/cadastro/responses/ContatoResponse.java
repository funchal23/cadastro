package br.com.funchal.cadastro.responses;

import br.com.funchal.cadastro.models.Contato;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ContatoResponse {
    private Long id;
    private String nome;
    private String numeroDeTelefone;
    private String email;

    public ContatoResponse(Contato contato) {
        this.id = contato.getId();
        this.nome = contato.getNome();
        this.numeroDeTelefone = contato.getNumeroDeTelefone();
        this.email = contato.getEmail();
    }
}
