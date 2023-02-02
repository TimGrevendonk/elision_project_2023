package fact.it.p4_backend.exception;

public class MailAlreadyExistsException extends RuntimeException{
    public MailAlreadyExistsException(String message){

        super(message);
    }
    public MailAlreadyExistsException(String message, Throwable cause){
        super(message, cause);
    }
}
