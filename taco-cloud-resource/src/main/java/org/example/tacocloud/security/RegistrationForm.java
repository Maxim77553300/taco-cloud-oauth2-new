package org.example.tacocloud.security;

import lombok.Getter;
import lombok.Setter;
import org.example.tacocloud.domain.User;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Setter
public class RegistrationForm {

    private String username;
    private String password;
    private String fullName;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phone;

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(username, passwordEncoder.encode(password),
                fullName, street, city, state, zip, phone);
    }

}
