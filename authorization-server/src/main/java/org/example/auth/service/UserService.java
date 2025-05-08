package org.example.auth.service;

import lombok.RequiredArgsConstructor;
import org.example.auth.domain.AuthUser;
import org.example.auth.entity.User;
import org.example.auth.repository.UserRepositoryJpa;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private static final String USERNAME_NOT_FOUND_MESSAGE = "Active user with username [%s] not found";

    private final UserRepositoryJpa userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USERNAME_NOT_FOUND_MESSAGE, username)));


        return new AuthUser(user.getUsername(), user.getPassword(), Set.of(new SimpleGrantedAuthority("ROLE_ADMIN")));
    }
}
