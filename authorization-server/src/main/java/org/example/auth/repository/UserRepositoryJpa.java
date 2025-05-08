package org.example.auth.repository;


import org.example.auth.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositoryJpa extends CrudRepository<User, String> {

    Optional<User> findByUsername(String username);
}
