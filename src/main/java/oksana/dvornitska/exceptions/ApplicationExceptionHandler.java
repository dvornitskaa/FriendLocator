package oksana.dvornitska.exceptions;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
@RequiredArgsConstructor
public class ApplicationExceptionHandler {
    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<?> handlerClientError(Exception e){
        e.printStackTrace();
        return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handlerEntityNotFound(Exception e){
        e.printStackTrace();
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
