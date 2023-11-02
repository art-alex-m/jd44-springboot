package ru.netology.l20authservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
