package Exceptions;

public class ConnectException extends RuntimeException{
    @Override
    public String getMessage() {
        return "Не удалось подключиться к серверу. Повторите попытку позже.";
    }
}
