package org.sqli.authentification.service.user;

import lombok.Getter;

@Getter
public class SuccessMessageResponse {
    private String success;
    public SuccessMessageResponse(String message) {
        success = message;
    }
}
