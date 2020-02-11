package guru.springframework.services;

import guru.springframework.model.Recipe;

public interface RecipeService {

    public Iterable<Recipe> getAllRecipes();
}
