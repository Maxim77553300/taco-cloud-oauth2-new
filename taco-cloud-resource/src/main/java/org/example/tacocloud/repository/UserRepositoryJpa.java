package org.example.tacocloud.repository;

import org.example.tacocloud.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryJpa extends CrudRepository<User, String> {

    User findByUsername(String username);
}
