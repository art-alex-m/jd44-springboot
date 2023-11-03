package ru.netology.l20authservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthorizationController {
    private final AuthorizationService authorizationService;

    public AuthorizationController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @GetMapping("/authorize")
    public List<Authorities> getAuthorities(
            @RequestParam("user") String user,
            @RequestParam("password") String password
    ) {
        return authorizationService.getAuthorities(user, password);
    }
}
