package org.example.tacocloud.config;

import jakarta.annotation.Resource;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.example.tacocloud.domain.User;
import org.example.tacocloud.repository.UserRepositoryJpa;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Arrays;
import java.util.stream.Collectors;

@Log4j2
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
//@EnableGlobalMethodSecurity//for @PreAuthorize
public class SecurityConfig {

    @Resource
    private Environment environment;

    @Bean
    public PasswordEncoder passwordEncoder() {
        log.info("Active profile : " + Arrays.stream(environment.getActiveProfiles()).collect(Collectors.joining()));// to show active profiles
//        return new BCryptPasswordEncoder();
        return NoOpPasswordEncoder.getInstance();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        List<UserDetails> users = new ArrayList<>();
//        users.add(new User(
//                "buzz", passwordEncoder().encode("password"),
//                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
//        users.add(new User(
//                "woody", passwordEncoder().encode("password"),
//                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
//        return new InMemoryUserDetailsManager(users);
//    }

    @Bean
    public SecurityFilterChain securityWebFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()//!! dont do it!! to do: implement csrf
                .authorizeRequests()
                .requestMatchers("/design", "/orders").access("hasRole('USER')")
//                .requestMatchers(new AntPathRequestMatcher("/api/ingredients")).access("hasRole('ADMIN')")
//                .requestMatchers(HttpMethod.DELETE, new AntPathRequestMatcher("/api/ingredients/**").hasRole("ADMIN")
                .requestMatchers("/design", "/orders").fullyAuthenticated()
                .requestMatchers(HttpMethod.GET, "/public/**").permitAll()
                .anyRequest().permitAll()
//                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .and()
                .oauth2Login()
                .loginPage("/login")
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers(new AntPathRequestMatcher("/h2-console/**"))
                .requestMatchers(new AntPathRequestMatcher("/register"));
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepositoryJpa userRepository) {
        return username -> {
            User user = userRepository.findByUsername(username);
            if (user != null) return user;

            throw new UsernameNotFoundException("User '" + username + "' not found");
        };
    }
}
