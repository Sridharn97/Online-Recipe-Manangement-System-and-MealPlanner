package com.mealplanner.models;

public class Recipe {
    private int recipeId;
    private String name;
    private String category;
    private String instructions;
    private int servings;
    private int prepTime;
    private int cookTime;

    public Recipe(int recipeId, String name, String category, String instructions, int servings, int prepTime, int cookTime) {
        this.recipeId = recipeId;
        this.name = name;
        this.category = category;
        this.instructions = instructions;
        this.servings = servings;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getInstructions() {
        return instructions;
    }

    public int getServings() {
        return servings;
    }

    public int getPrepTime() {
        return prepTime;
    }

    public int getCookTime() {
        return cookTime;
    }

    @Override
    public String toString() {
        return "Recipe ID: " + recipeId + "\n" +
               "Name: " + name + "\n" +
               "Category: " + category + "\n" +
               "Instructions: " + instructions + "\n" +
               "Servings: " + servings + "\n" +
               "Prep Time: " + prepTime + " minutes\n" +
               "Cook Time: " + cookTime + " minutes";
    }
}
