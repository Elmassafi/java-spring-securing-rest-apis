package io.jzheaux.springsecurity.resolutions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/*
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
excluding SecurityAutoConfiguration.class will disable the autoConfiguration of spring security
to apply the Spring Boot Security Starter's auto-configuration to the project remove the exclusion
 */
@SpringBootApplication()
public class ResolutionsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResolutionsApplication.class, args);
    }

    @Bean
    UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.withUsername("user")
                        .password("{bcrypt}$2a$10$MywQEqdZFNIYnx.Ro/VQ0ulanQAl34B5xVjK2I/SDZNVGS5tHQ08W")
                        .authorities("resolution:read")
                        .build()
        );
    }
}
