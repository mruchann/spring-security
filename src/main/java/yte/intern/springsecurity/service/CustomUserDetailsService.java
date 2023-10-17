package yte.intern.springsecurity.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import yte.intern.springsecurity.Authority;
import yte.intern.springsecurity.entity.Users;
import yte.intern.springsecurity.repository.UserRepository;

import java.util.List;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @PostConstruct
    public void init() {
        userRepository.save(new Users(null, "user", passwordEncoder.encode("user"), Set.of(new Authority("ROLE_USER"))));
        userRepository.save(new Users(null, "admin", passwordEncoder.encode("admin"), Set.of(new Authority("ROLE_USER"), new Authority("ROLE_ADMIN"))));

    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
