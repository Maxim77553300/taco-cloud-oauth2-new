package org.example.tacocloud.config;

import org.example.tacocloud.domain.Ingredient;
import org.example.tacocloud.domain.Taco;
import org.example.tacocloud.repository.IngredientRepositoryJpa;
import org.example.tacocloud.repository.TacoRepository;
import org.example.tacocloud.repository.UserRepositoryJpa;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@Configuration
public class AppConfig {

    @Bean
    public CommandLineRunner dataLoader(IngredientRepositoryJpa repo,
                                        UserRepositoryJpa userRepo,
                                        PasswordEncoder encoder,
                                        TacoRepository tacoRepo) {

        return args -> {
            Ingredient flourTortilla = new Ingredient(
                    "FTLO", "Flour Tortilla", Ingredient.Type.WRAP);
            Ingredient copperTortilla = new Ingredient(
                    "COTO", "Copper Tortilla", Ingredient.Type.WRAP);
            Ingredient groundBeef = new Ingredient(
                    "CRBF", "Ground Beef", Ingredient.Type.PROTEIN);
            Ingredient carnitas = new Ingredient(
                    "CARN", "Carnitas", Ingredient.Type.PROTEIN);
            Ingredient lettuce = new Ingredient(
                    "LTEC", "Lettuce", Ingredient.Type.VEGGIES);
            Ingredient cheddar = new Ingredient(
                    "CHED", "Cheddar", Ingredient.Type.CHEESE);
            Ingredient jack = new Ingredient(
                    "JACK", "Monterey", Ingredient.Type.CHEESE);
            Ingredient sourCream = new Ingredient(
                    "SRCR", "Sour Cream", Ingredient.Type.SAUCE);
            repo.save(flourTortilla);
            repo.save(copperTortilla);
            repo.save(groundBeef);
            repo.save(carnitas);
            repo.save(lettuce);
            repo.save(cheddar);
            repo.save(jack);
            repo.save(sourCream);

            Taco taco1 = new Taco();
            taco1.setName("Carnivore");
            taco1.setIngredients(Arrays.asList(
                    flourTortilla, groundBeef, carnitas,
                    sourCream, cheddar));
            tacoRepo.save(taco1);


            Taco taco2 = new Taco();
            taco2.setName("Bovine Bounty");
            taco2.setIngredients(Arrays.asList(
                    copperTortilla, groundBeef, cheddar,
                    jack, sourCream));
            tacoRepo.save(taco2);

            Taco taco3 = new Taco();
            taco3.setName("Veg-Out");
            taco3.setIngredients(Arrays.asList(
                    flourTortilla, copperTortilla, lettuce
            ));
            tacoRepo.save(taco3);

        };

    }
}
