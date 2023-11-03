package ru.netology.l20authservice;

import java.util.List;

public interface AuthorizationService {
    List<Authorities> getAuthorities(User user);
}
