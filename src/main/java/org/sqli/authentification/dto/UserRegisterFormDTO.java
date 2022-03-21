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

    @Size(min = 5, max = 50, message = "login must be between 5 and 60 characters" )
    private String login;
    @Pattern(regexp = "^[a-z][a-zA-Z_0-9]{3,7}$", message = "password should match at least 1 char and at least be of size 3")
    private String password;
    private String confirmPassword;
    @NotNull(message = "group must be a valid user group")
    @Size(min = 3, max = 50)
    private String group;
}
