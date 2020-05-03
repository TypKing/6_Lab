package Exceptions;

public class ValidateException extends RuntimeException{
    @Override
    public String getMessage() {
        return "Команда не прошла валидацию.";
    }
}
