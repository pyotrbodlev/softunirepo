package mostwanted.util;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.util.Set;

public class ValidationUtilImpl implements ValidationUtil {
    @Override
    public <E> boolean isValid(E entity) {
        Set<ConstraintViolation<E>> constraintViolations = Validation.buildDefaultValidatorFactory().getValidator().validate(entity);

        return constraintViolations.size() == 0;
    }
}
