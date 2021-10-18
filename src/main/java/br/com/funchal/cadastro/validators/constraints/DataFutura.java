package br.com.funchal.cadastro.validators.constraints;

import br.com.funchal.cadastro.validators.DataValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DataValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataFutura {

    String message() default "Data invalida";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    
}
