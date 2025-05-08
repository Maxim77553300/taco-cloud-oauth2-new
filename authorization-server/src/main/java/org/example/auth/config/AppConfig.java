package org.example.auth.config;

import org.example.auth.domain.AuthUser;
import org.example.auth.entity.User;
import org.example.auth.repository.UserRepositoryJpa;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.UUID;

@Configuration
public class AppConfig {


    @Bean
    public ApplicationRunner dataLoader(
            UserRepositoryJpa userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            userRepository.save(
                    new User(UUID.randomUUID().toString(), "hubama", passwordEncoder.encode("password"), "ROLE_ADMIN"));

            userRepository.save(
                    new User(UUID.randomUUID().toString(), "tacochef", passwordEncoder.encode("password"), "ROLE_ADMIN"));

            userRepository.save(
                    new User(UUID.randomUUID().toString(), "user1", passwordEncoder.encode("password"), "ROLE_ADMIN"));
        };

    }

}
