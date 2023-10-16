package yte.intern.springsecurity.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import yte.intern.springsecurity.service.CustomerUserDetailsService;


@Configuration
@EnableMethodSecurity
public class SecurityConfiguration {

    @Autowired
    public SecurityConfiguration(AuthenticationManagerBuilder authenticationManagerBuilder,
                                 CustomerUserDetailsService customerUserDetailsService)
        throws Exception {
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        UserDetails user = User.builder()
//                .username("ruchan")
//                .password(passwordEncoder.encode("1234"))
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder.encode("1234"))
//                .roles("ADMIN")
//                .build();

        authenticationManagerBuilder
                .userDetailsService(customerUserDetailsService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());

//                .inMemoryAuthentication()
//                .withUser(user)
//                .withUser(admin)
//                .passwordEncoder(passwordEncoder);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.authorizeHttpRequests()

                .requestMatchers(AntPathRequestMatcher.antMatcher("/login"))
                    .permitAll()
                .requestMatchers(AntPathRequestMatcher.antMatcher("/secret"))
                    .denyAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .build();

    }
}
