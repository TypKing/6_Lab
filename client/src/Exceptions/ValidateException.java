package Exceptions;

public class ValidateException extends RuntimeException{
    public ValidateException() {
        super("Команда не прошла валидацию.");
    }
}
