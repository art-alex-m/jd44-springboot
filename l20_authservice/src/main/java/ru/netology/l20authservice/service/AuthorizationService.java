package ru.netology.l20authservice.service;

import ru.netology.l20authservice.model.Authorities;
import ru.netology.l20authservice.model.User;

import java.util.List;

public interface AuthorizationService {
    List<Authorities> getAuthorities(User user);
}
