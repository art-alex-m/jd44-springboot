package ru.netology.l10_conditionalapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProfileControllerIntegrationTest {
    private static final int DEV_APP_PORT = 8080;
    public static final String DEV_APP_TAG = "condapp:dev";
    private static final int PROD_APP_PORT = 8081;
    public static final String PROD_APP_TAG = "condapp:prod";

    // Как вариант использования одного образа приложения
    // private static final GenericContainer<?> devApp = new GenericContainer<>(PROD_APP_TAG)
    //     .withEnv("SERVER_PORT", String.valueOf(DEV_APP_PORT))
    //     .withEnv("NETOLOGY_PROFILE_DEV", "true")
    //     .withExposedPorts(DEV_APP_PORT);

    private static final GenericContainer<?> devApp = new GenericContainer<>(DEV_APP_TAG)
            .withExposedPorts(DEV_APP_PORT);
    private static final GenericContainer<?> prodApp = new GenericContainer<>(PROD_APP_TAG)
            .withExposedPorts(PROD_APP_PORT);

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeAll
    static void setUp() {
        devApp.start();
        prodApp.start();
    }

    @Test
    void whenAppIsDev_thenProfileIsDev() {
        ResponseEntity<String> profile = restTemplate.getForEntity(
                "http://localhost:" + devApp.getMappedPort(DEV_APP_PORT) + "/profile", String.class);

        Assertions.assertEquals(HttpStatus.OK, profile.getStatusCode());
        Assertions.assertEquals("Current profile is dev", profile.getBody());
    }

    @Test
    void whenAppIsProd_thenProfileIsProd() {
        ResponseEntity<String> profile = restTemplate.getForEntity(
                "http://localhost:" + prodApp.getMappedPort(PROD_APP_PORT) + "/profile", String.class);

        Assertions.assertEquals(HttpStatus.OK, profile.getStatusCode());
        Assertions.assertEquals("Current profile is production", profile.getBody());
    }
}
