package ru.netology.l20authservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.netology.l20authservice.repository.UserRepository;
import ru.netology.l20authservice.repository.UserRepositoryStubImpl;
import ru.netology.l20authservice.service.AuthorizationService;
import ru.netology.l20authservice.service.AuthorizationServiceImpl;

@Configuration
public class ApplicationConfig {

    @Bean
    public UserRepository userRepository() {
        return new UserRepositoryStubImpl();
    }

    @Bean
    public AuthorizationService authorizationService(UserRepository userRepository) {
        return new AuthorizationServiceImpl(userRepository);
    }
}
