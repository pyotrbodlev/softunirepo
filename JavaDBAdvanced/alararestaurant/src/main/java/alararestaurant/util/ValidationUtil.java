package alararestaurant.util;

public interface ValidationUtil {

    <E> boolean isValid(E entity);

    <E> String getErrorMessage(E importDto);
}
