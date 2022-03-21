package org.sqli.authentification.service.auth;

import org.sqli.authentification.dto.UserLoggedInDTO;
import org.sqli.authentification.dto.UserLoginFormDTO;
import org.sqli.authentification.dto.UserRegisterFormDTO;

public interface AuthService {
    UserLoggedInDTO login(final UserLoginFormDTO userDto);
    UserLoggedInDTO register(final UserRegisterFormDTO userRegisterFormDTO);
}