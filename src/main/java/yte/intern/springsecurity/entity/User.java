package yte.intern.springsecurity.entity;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    private String username;
    private String password;
    private String authorities;
}
