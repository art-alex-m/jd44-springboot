package ru.netology.l20authservice.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.l20authservice.model.Authorities;
import ru.netology.l20authservice.model.User;
import ru.netology.l20authservice.service.AuthorizationService;

import java.util.List;

@RestController
public class AuthorizationController {
    private final AuthorizationService authorizationService;

    public AuthorizationController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @GetMapping("/authorize")
    public List<Authorities> getAuthorities(@Validated User user) {
        return authorizationService.getAuthorities(user);
    }
}
