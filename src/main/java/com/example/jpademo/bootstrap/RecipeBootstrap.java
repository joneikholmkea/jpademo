package com.example.jpademo.bootstrap;

import com.example.jpademo.model.Category;
import com.example.jpademo.model.Ingredient;
import com.example.jpademo.model.Notes;
import com.example.jpademo.model.Recipe;
import com.example.jpademo.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private RecipeRepository recipeRepository;

   private List<Recipe> createRecipes(){
       List<Recipe> recipes = new ArrayList<>();
       Recipe r1 = new Recipe();
       r1.setDescription("Perfect Guacamole");
       Notes notes = new Notes();
       notes.setNotes("Avocado, hvidløg, citron, spidskommen" +
               " creme-freche, salt, peber, tomatskal");
       notes.setRecipe(r1);
       r1.setNotes(notes);

       Ingredient ingredient = new Ingredient();
       ingredient.setName("Avocado");
       ingredient.setGrams(BigDecimal.valueOf(500));
       r1.getIngredients().add(ingredient);
       ingredient.setRecipe(r1);

       Category category1 = new Category();
       category1.setCategoryName("Mexican");
       category1.getRecipes().add(r1);
       r1.getCategories().add(category1);

       recipes.add(r1);
       return recipes;
   }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(createRecipes());
    }
}
