package alararestaurant.util;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class ValidationUtilImpl implements ValidationUtil {
    @Override
    public <E> boolean isValid(E entity) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        return validator.validate(entity).isEmpty();
    }

    @Override
    public <E> String getErrorMessage(E importDto) {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

        Validator validator = validatorFactory.getValidator();

        Set<ConstraintViolation<E>> constraintViolations = validator.validate(importDto);

        StringBuilder sb = new StringBuilder();

        for (ConstraintViolation<E> constraintViolation : constraintViolations) {
            sb.append(constraintViolation.getMessage()).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
