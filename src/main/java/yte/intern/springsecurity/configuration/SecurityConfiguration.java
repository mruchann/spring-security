package yte.intern.springsecurity.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
public class SecurityConfiguration {

    public SecurityConfiguration(AuthenticationManagerBuilder authenticationManagerBuilder)
        throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        UserDetails user = User.builder()
                .username("ruchan")
                .password(passwordEncoder.encode("1234"))
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("1234"))
                .roles("ADMIN")
                .build();

        authenticationManagerBuilder.inMemoryAuthentication()
                .withUser(user)
                .withUser(admin)
                .passwordEncoder(passwordEncoder);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.authorizeHttpRequests()
                .requestMatchers(AntPathRequestMatcher.antMatcher("/user"))
                    .hasAnyRole("USER", "ADMIN")
                .requestMatchers(AntPathRequestMatcher.antMatcher("/admin"))
                    .hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .build();

    }
}
