package br.com.funchal.cadastro.dtos;

import br.com.funchal.cadastro.validators.constraints.DataFutura;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class PessoaDTO {

    @NotBlank(message = "O campo nome está vazio")
    @NotNull(message = "O campo nome está nulo")
    private String nome;
    @CPF(message = "CPF está com numero inválido")
    private String cpf;
    @DataFutura(message = "A data inserida é futura")
    private LocalDate dataDeNascimento;

}
