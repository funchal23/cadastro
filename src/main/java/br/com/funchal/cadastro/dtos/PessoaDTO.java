package br.com.funchal.cadastro.dtos;

import br.com.funchal.cadastro.validators.constraints.DataFutura;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class PessoaDTO {

    @NotBlank
    @NotNull
    private String nome;
    @CPF
    private String cpf;
    @DataFutura
    private LocalDate dataDeNascimento;

}
