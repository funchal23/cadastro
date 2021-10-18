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

    @NotBlank
    @NotNull
    private String nome;
    @NotBlank @Length(min = 11)
    private String numeroDeTelefone;
    @Email
    private String email;
}
