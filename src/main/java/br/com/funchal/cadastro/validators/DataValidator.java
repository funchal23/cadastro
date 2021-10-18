package br.com.funchal.cadastro.validators;

import br.com.funchal.cadastro.validators.constraints.DataFutura;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class DataValidator implements ConstraintValidator<DataFutura, LocalDate> {
    @Override
    public void initialize(DataFutura constraintAnnotation) {

    }

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        LocalDate date = LocalDate.now();
        return localDate.isBefore(date) || localDate.isEqual(date);
    }
}
