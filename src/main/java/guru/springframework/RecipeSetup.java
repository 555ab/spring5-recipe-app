package guru.springframework;

import guru.springframework.model.Ingredient;
import guru.springframework.model.Recipe;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;

@Component
public class RecipeSetup implements ApplicationListener<ContextRefreshedEvent> {

    private RecipeRepository recipeRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;
    private CategoryRepository categoryRepository;

    public RecipeSetup(RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository, CategoryRepository categoryRepository) {
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        Recipe guacamole = new Recipe();

        Ingredient avocado = new Ingredient();
        avocado.setAmount(new BigDecimal(2));
        avocado.setDescription("Avocado");
        avocado.setRecipe(guacamole);
        avocado.setUom(unitOfMeasureRepository.findByDescription("Piece").get());

        Ingredient serranoChile = new Ingredient();
        serranoChile.setAmount(new BigDecimal(2));
        serranoChile.setDescription("Serrano Chile");
        serranoChile.setRecipe(guacamole);
        serranoChile.setUom(unitOfMeasureRepository.findByDescription("Tablespoon").get());

        Ingredient tomato = new Ingredient();
        tomato.setAmount(new BigDecimal(0.5));
        tomato.setDescription("Tomato");
        tomato.setRecipe(guacamole);
        tomato.setUom(unitOfMeasureRepository.findByDescription("Piece").get());

        guacamole.setDescription("How to make perfect guacamole");
        guacamole.setPrepTime(10);
        guacamole.setIngredients(new HashSet<>(Arrays.asList(avocado, serranoChile, tomato)));
        guacamole.setCategories(new HashSet<>(Arrays.asList(categoryRepository.findByDescription("Mexican").get())));

        recipeRepository.save(guacamole);
    }
}