package org.sqli.authentification.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice(annotations = RestController.class)
@Slf4j
public class RestExceptionHandler {
    // handle exceptions here
    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ErrorResponse> handleAuthFailure(final AuthException exception) {
        log.error(exception.getMessage());
        final ErrorResponse errorResponse = new ErrorResponse();;
        errorResponse.setHttpStatus(HttpStatus.UNAUTHORIZED.value());
        errorResponse.setException(exception.getClass().getSimpleName());
        errorResponse.setError(exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(final NotFoundException exception) {
        log.error(exception.getMessage());
        final ErrorResponse errorResponse = new ErrorResponse();;
        errorResponse.setHttpStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setException(exception.getClass().getSimpleName());
        errorResponse.setError(exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(
            final MethodArgumentNotValidException exception) {
        log.error(exception.getMessage());
        final String errorMessage = exception
                .getBindingResult()
                .getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));

        final ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setHttpStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setException(exception.getClass().getSimpleName());
        errorResponse.setError(errorMessage);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorResponse> handleThrowable(final Throwable exception) {
        log.error(exception.getMessage());
        final ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.setException(exception.getClass().getSimpleName());
        errorResponse.setError(exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
