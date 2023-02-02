package fact.it.p4_backend.exception;

import java.awt.event.FocusEvent;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message){
        super(message);
    }
    public UserNotFoundException(String message, Throwable cause){
        super(message, cause);
    }
}
