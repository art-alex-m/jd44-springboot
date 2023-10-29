package ru.netology.l10_conditionalapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class JavaConfigSystemProfileTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner();


    @Test
    public void whenProfileDevIsTrue_thenCreateDevProfile() {
        this.contextRunner.withPropertyValues("netology.profile.dev = true")
                .withUserConfiguration(JavaConfig.class)
                .run(context -> {
                    assertThat(context).hasBean("devProfile");
                    SystemProfile systemProfile = context.getBean(SystemProfile.class);
                    assertThat(systemProfile.getProfile()).isEqualTo("Current profile is dev");
                    assertThat(context).doesNotHaveBean("prodProfile");
                });
    }

    @Test
    public void whenProfileDevIsFalse_thenCreateProductionProfile() {
        this.contextRunner.withPropertyValues("netology.profile.dev = false")
                .withUserConfiguration(JavaConfig.class)
                .run(context -> {
                    assertThat(context).hasBean("prodProfile");
                    SystemProfile systemProfile = context.getBean(SystemProfile.class);
                    assertThat(systemProfile.getProfile()).isEqualTo("Current profile is production");
                    assertThat(context).doesNotHaveBean("devProfile");
                });
    }
}
