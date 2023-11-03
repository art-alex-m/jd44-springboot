package ru.netology.l20authservice.repository;

import ru.netology.l20authservice.model.Authorities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepositoryStubImpl implements UserRepository {
    private final Map<String, String> users = new HashMap<>() {{
        put("user", "password");
    }};

    private final Map<String, List<Authorities>> authorities = new HashMap<>() {{
        put("user", List.of(Authorities.READ, Authorities.WRITE, Authorities.DELETE));
    }};

    @Override
    public List<Authorities> getUserAuthorities(String user, String password) {

        if (users.containsKey(user) && authorities.containsKey(user)) {
            if (users.get(user).equals(password)) {
                return authorities.get(user);
            }
        }

        return null;
    }
}
