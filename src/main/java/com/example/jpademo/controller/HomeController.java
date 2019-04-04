package com.example.jpademo.controller;

import com.example.jpademo.model.Recipe;
import com.example.jpademo.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class HomeController {
    @Autowired
    private RecipeRepository recipeRepository;

    @GetMapping("/")
    public String home(){
        Optional<Recipe> recipe = recipeRepository.findByDescription("Perfect Guacamole");
        if (recipe.isPresent()){
            System.out.println("fandt: " + recipe.get().getDescription());
        }else {
            System.out.println("fandt ingen opskrift");
        }
        return "index";
    }
    @PostMapping("/opret")
    public String opretRecipe(Recipe recipe){
        recipeRepository.save(recipe);
        return "index";
    }
}
