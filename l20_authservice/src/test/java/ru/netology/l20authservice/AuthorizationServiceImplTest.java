package ru.netology.l20authservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AuthorizationServiceImplTest {

    @ParameterizedTest
    @MethodSource
    public void whenEmptyUserAuthorities_thenThrowUnauthorizedUser(List<Authorities> authoritiesList) {
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        Mockito.when(userRepository.getUserAuthorities(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(authoritiesList);
        User user = new User("user", "password");
        AuthorizationService sut = new AuthorizationServiceImpl(userRepository);

        Executable result = () -> sut.getAuthorities(user);

        assertThrows(UnauthorizedUserException.class, result);
    }

    @Test
    public void whenCorrectCredentials_thenListAuthorities() {
        List<Authorities> expected = List.of(Authorities.READ, Authorities.WRITE);
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        Mockito.when(userRepository.getUserAuthorities(Mockito.anyString(), Mockito.anyString())).thenReturn(expected);
        User user = new User("user", "password");
        AuthorizationService sut = new AuthorizationServiceImpl(userRepository);

        List<Authorities> result = sut.getAuthorities(user);

        assertEquals(expected, result);
        Mockito.verify(userRepository, Mockito.times(1)).getUserAuthorities(user.getUser(), user.getPassword());
    }

    public static Stream<Arguments> whenEmptyUserAuthorities_thenThrowUnauthorizedUser() {
        return Stream.of(
                null,
                Arguments.of(List.of())
        );
    }
}