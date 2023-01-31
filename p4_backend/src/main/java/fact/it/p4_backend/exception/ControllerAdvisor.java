package fact.it.p4_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
    private ResponseEntity<Object> buildResponseEntity(ErrorResponse errorResponse) {
        return new ResponseEntity<>(errorResponse, errorResponse.getStatusCode());
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<Object> HandleUserNotFoundException(
            RuntimeException exception
    ) {
        return buildResponseEntity(
                ErrorResponse.create(
                        exception,
                        HttpStatus.NOT_FOUND,
                        "User not found in query."
                )
        );
    }

    @ExceptionHandler(value = MailAlreadyExistsException.class)
    public ResponseEntity<Object> HandleMailAlreadyExistsException(
            RuntimeException exception
    ) {
        return buildResponseEntity(
                ErrorResponse.create(
                        exception,
                        HttpStatus.UNPROCESSABLE_ENTITY,
                        "Mail is already in use."
                )
        );
    }
}
