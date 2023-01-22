package fact.it.p4_backend.exception;

import fact.it.p4_backend.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<User> HandleUserNotFoundException(
            UserNotFoundException except, WebRequest request
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
