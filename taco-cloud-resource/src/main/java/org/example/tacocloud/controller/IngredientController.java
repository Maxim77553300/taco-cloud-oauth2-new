package org.example.tacocloud.controller;

import lombok.RequiredArgsConstructor;
import org.example.tacocloud.domain.Ingredient;
import org.example.tacocloud.repository.IngredientRepositoryJpa;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/ingredients", produces = "application/json")
@CrossOrigin(origins = "http://localhost:8080")
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientRepositoryJpa repositoryJpa;



    @GetMapping
    public Iterable<Ingredient> allIngredients() {
        return repositoryJpa.findAll();
    }

    @PostMapping
//    @PreAuthorize("#{hasRole('ADMIN')}")
    @ResponseStatus(HttpStatus.CREATED)
    public Ingredient saveIngredient(@RequestBody Ingredient ingredient) {
        return repositoryJpa.save(ingredient);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("#{hasRole('ADMIN')}")
    public void deleteIngredient(@PathVariable("id") String id) {
        repositoryJpa.deleteById(id);
    }


}
