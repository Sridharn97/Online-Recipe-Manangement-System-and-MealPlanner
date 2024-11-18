package com.mealplanner.interfaces;

import com.mealplanner.models.Recipe;

import java.util.List;

public interface RecipeManagement {
    void addRecipe(Recipe recipe);
    Recipe getRecipe(int recipeId);
    List<Recipe> getAllRecipes();
    void deleteRecipe(int recipeId);
}
