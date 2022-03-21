package org.sqli.authentification.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.sqli.authentification.dto.UserLoggedInDTO;
import org.sqli.authentification.dto.UserRegisterFormDTO;
import org.sqli.authentification.service.auth.AuthService;
import org.sqli.authentification.service.user.SuccessMessageResponse;
import org.sqli.authentification.service.user.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class UserController {

    private final AuthService authService;
    private final UserService userService;

    public UserController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserLoggedInDTO> register(@RequestBody @Valid final UserRegisterFormDTO userRegisterFormDTO) {
        return ResponseEntity.ok(authService.register(userRegisterFormDTO));
    }

    @DeleteMapping("/{login}")
    public ResponseEntity<SuccessMessageResponse> deleteUser(@PathVariable final String login) {
        return ResponseEntity.ok(userService.deleteByLogin(login));
    }
}
