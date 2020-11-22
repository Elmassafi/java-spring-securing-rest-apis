package io.jzheaux.springsecurity.resolutions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;
import java.util.List;

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
    UserDetailsService userDetailsService(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource) {
            /*
            We haven't taken any steps yet to represent authority.
            Thus, the above declaration hardcodes the resolution:read authority into every user.
             */
            @Override
            protected List<GrantedAuthority> loadUserAuthorities(String username) {
                return AuthorityUtils.createAuthorityList("resolution:read");
            }
        };
    }
}
/*
return new InMemoryUserDetailsManager(
                User.withUsername("user")
                        .password("{bcrypt}$2a$10$MywQEqdZFNIYnx.Ro/VQ0ulanQAl34B5xVjK2I/SDZNVGS5tHQ08W")
                        .authorities("resolution:read")
                        .build()
 */