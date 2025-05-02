package org.example.tacocloud.repository;

import org.example.tacocloud.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepositoryJpa extends CrudRepository<Ingredient, String> {
}
