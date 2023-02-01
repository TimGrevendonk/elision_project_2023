package fact.it.p4_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<Object> HandleUserNotFoundException(RuntimeException exception) {
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = MailAlreadyExistsException.class)
    public ResponseEntity<Object> HandleMailAlreadyExistsException(RuntimeException exception) {
        return new ResponseEntity<>(exception, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
