package ru.netology.l20authservice;

import java.util.List;

public interface AuthorizationService {
    List<Authorities> getAuthorities(String user, String password);
}
