package ru.netology.l20authservice;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayWithSize;
import static org.hamcrest.Matchers.equalTo;

/**
 * <a href="https://spring.io/guides/gs/testing-web/">Testing the Web Layer</a>
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthorizationControllerTest {

    @LocalServerPort
    private int port;

    @MockBean
    private AuthorizationService authorizationService;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void whenInvalidCredentials_thenStatusBadRequest() {
        String url = "http://localhost:" + port + "/authorize?user=&password=";

        ResponseEntity<ErrorPair[]> result = restTemplate.getForEntity(url, ErrorPair[].class);

        assertThat(result.getStatusCode(), equalTo(HttpStatus.BAD_REQUEST));
        assertThat(result.getBody(), arrayWithSize(2));
    }

    @Test
    public void whenUnauthorizedUser_thenStatusUnauthorized() {
        String url = "http://localhost:" + port + "/authorize?user=user&password=password";
        String message = "Test message";
        Mockito.when(authorizationService.getAuthorities(Mockito.any()))
                .thenThrow(new UnauthorizedUserException(message));

        ResponseEntity<String> result = restTemplate.getForEntity(url, String.class);

        assertThat(result.getStatusCode(), equalTo(HttpStatus.UNAUTHORIZED));
        assertThat(result.getBody(), equalTo(message));
    }
}