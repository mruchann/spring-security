package yte.intern.springsecurity.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;


@Configuration
public class SecurityConfiguration {

    public SecurityConfiguration(AuthenticationManagerBuilder authenticationManagerBuilder)
        throws Exception {
        UserDetails user = User.builder()
                .username("ruchan")
                .password("1234567890")
                .authorities("ADMIN")
                .build();

        authenticationManagerBuilder.inMemoryAuthentication()
                .withUser(user)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
}
