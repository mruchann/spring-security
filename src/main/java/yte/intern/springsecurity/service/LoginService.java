package yte.intern.springsecurity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import yte.intern.springsecurity.CustomAuthenticationProvider;
import yte.intern.springsecurity.LoginRequest;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final CustomAuthenticationProvider customAuthenticationProvider;

    public String login(LoginRequest loginRequest) {
        return "Please wait...";
    }
}
