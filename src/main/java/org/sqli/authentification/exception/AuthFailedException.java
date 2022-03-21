package org.sqli.authentification.exception;

public class AuthFailedException extends RuntimeException {
    public AuthFailedException(String msg) {
        super(msg);
    }
}