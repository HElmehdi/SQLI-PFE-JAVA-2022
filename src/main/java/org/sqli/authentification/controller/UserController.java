package org.sqli.authentification.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.sqli.authentification.dto.UserLoggedInDTO;
import org.sqli.authentification.dto.UserRegisterFormDTO;
import org.sqli.authentification.service.auth.AuthService;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final AuthService authService;

    public UserController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<UserLoggedInDTO> register(@RequestBody @Valid final UserRegisterFormDTO userRegisterFormDTO) {
        return ResponseEntity.ok(authService.register(userRegisterFormDTO));
    }

}