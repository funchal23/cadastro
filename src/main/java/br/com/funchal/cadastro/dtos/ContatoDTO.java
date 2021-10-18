package br.com.funchal.cadastro.dtos;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ContatoDTO {

    @NotBlank(message = "O campo nome está vazio")
    @NotNull(message = "O campo nome está nulo")
    private String nome;
    @NotBlank(message = "O campo nome está vazio")
    @Length(min = 11,message = "O campo numero não tem 11 digitos, por favor colocar DDD")
    @NotNull(message = "O campo nome está nulo")
    private String numeroDeTelefone;
    @Email(message = "Formato de email invalido")
    private String email;
}
