package exceptions;

/**
 * Класс исключения для индикации недопустимого значения
 */
public class ValueException extends IllegalArgumentException {
    public ValueException(String what) {
        super(what);
    }
}
