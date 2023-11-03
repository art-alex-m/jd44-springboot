package ru.netology.l20authservice.service;

import ru.netology.l20authservice.model.Authorities;
import ru.netology.l20authservice.model.User;
import ru.netology.l20authservice.repository.UserRepository;

import java.util.List;

public class AuthorizationServiceImpl implements AuthorizationService {
    private final UserRepository userRepository;

    public AuthorizationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<Authorities> getAuthorities(User user) {
        List<Authorities> userAuthorities = userRepository.getUserAuthorities(user.getUser(), user.getPassword());

        if (isEmpty(userAuthorities)) {
            throw new UnauthorizedUserException("Unknown user " + user);
        }

        return userAuthorities;
    }

    private boolean isEmpty(List<?> str) {
        return str == null || str.isEmpty();
    }
}
