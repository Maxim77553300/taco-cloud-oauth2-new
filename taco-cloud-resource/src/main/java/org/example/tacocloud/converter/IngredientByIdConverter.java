package org.example.tacocloud.converter;

import org.example.tacocloud.domain.Ingredient;
import org.example.tacocloud.repository.IngredientRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

//@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private IngredientRepository ingredientRepository;

    public IngredientByIdConverter(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    //    private Map<String, Ingredient> ingredients = new HashMap<String, Ingredient>();


//    public IngredientByIdConverter() {
//        ingredients.put("FLTO", new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP));
//        ingredients.put("COTO", new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP));
//        ingredients.put("GRBF", new Ingredient("GRBF", "Ground beef", Ingredient.Type.PROTEIN));
//        ingredients.put("CARN", new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN));
//        ingredients.put("TMTO", new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES));
//        ingredients.put("LETC", new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES));
//        ingredients.put("CHED", new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE));
//        ingredients.put("JACK", new Ingredient("JACK", "Monterrey jack", Ingredient.Type.CHEESE));
//        ingredients.put("SLSA", new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE));
//    }

    @Override
    public Ingredient convert(String id) {
        return ingredientRepository.findById(id).orElse(null);
    }
}
