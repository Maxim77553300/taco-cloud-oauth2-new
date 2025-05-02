package org.example.tacocloud.repository;

import org.example.tacocloud.domain.Ingredient;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

//@Repository
public class JdbcIngredientRepository {

    private JdbcTemplate jdbcTemplate;

    public JdbcIngredientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

//    @Override
//    public Iterable<Ingredient> findAll() {
//        return jdbcTemplate.query(
//                "select id, name, type from Ingredient",
//                this::mapRowToIngredient
//        );
//    }
//
//    @Override
//    public Optional<Ingredient> findById(String id) {
//        List<Ingredient> results = jdbcTemplate.query(
//                "select id, name, type from Ingredient where id=?",
//                this::mapRowToIngredient,
//                id
//        );
//        return results.size() == 0 ?
//                Optional.empty() :
//                Optional.of(results.get(0));
//    }
//
//    @Override
//    public Ingredient save(Ingredient ingredient) {
//        jdbcTemplate.update(
//                "insert into Ingredient (id, name, type) values (?, ?, ?)",
//                ingredient.getId(),
//                ingredient.getName(),
//                ingredient.getType().name()
//        );
//        return ingredient;
//    }
//
//    private Ingredient mapRowToIngredient(ResultSet resultSet, int i) throws SQLException {
//        return new Ingredient(
//                resultSet.getString("id"),
//                resultSet.getString("name"),
//                Ingredient.Type.valueOf(resultSet.getString("type"))
//        );
//    }
}
