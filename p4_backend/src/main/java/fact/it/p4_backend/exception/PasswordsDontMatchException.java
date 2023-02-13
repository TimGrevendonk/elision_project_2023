package fact.it.p4_backend.exception;

public class PasswordsDontMatchException extends RuntimeException{
    public PasswordsDontMatchException(String message){
        super(message);
    }
    public PasswordsDontMatchException(String message, Throwable cause){
        super(message, cause);
    }
}
