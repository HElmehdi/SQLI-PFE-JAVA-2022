package org.sqli.authentification.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = RestController.class)
@Slf4j
public class RestExceptionHandler {
    // handle exceptions here
    @ExceptionHandler(AuthFailedException.class)
    public ResponseEntity<ErrorResponse> handleAuthFailure(final AuthFailedException exception) {
        log.error(exception.getMessage());
        final ErrorResponse errorResponse = new ErrorResponse();;
        errorResponse.setError(exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }
}