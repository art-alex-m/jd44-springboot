package ru.netology.l20authservice.repository;

import ru.netology.l20authservice.model.Authorities;

import java.util.List;

public interface UserRepository {
    List<Authorities> getUserAuthorities(String user, String password);
}
