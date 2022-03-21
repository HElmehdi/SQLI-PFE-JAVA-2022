package org.sqli.authentification.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    // object to return in case of exception
    private String error;
}