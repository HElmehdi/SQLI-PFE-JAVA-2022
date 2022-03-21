package org.sqli.authentification.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
public class UserRegisterFormDTO {

    @Size(min = 5, max = 50, message = "login must be between 5 and 50 character")
    private String login;
    @Pattern(regexp = "^[a-z][a-zA-Z_0-9]{3,7}$\n", message = "password must be valid regex")
    private String password;
    @NotNull(message = "confirm password must match password")
    // not yet implemented
    private String confirmPassword;
    @NotNull(message = "group is required")
    private String group;
}