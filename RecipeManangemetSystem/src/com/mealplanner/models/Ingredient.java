package com.mealplanner.models;

public class Ingredient {
    private int ingredientId;
    private int recipeId;
    private String name;
    private double quantity;
    private String unit;

    public Ingredient(int ingredientId, int recipeId, String name, double quantity, String unit) {
        this.ingredientId = ingredientId;
        this.recipeId = recipeId;
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public String getName() {
        return name;
    }

    public double getQuantity() {
        return quantity;
    }

    public String getUnit() {
        return unit;
    }

    @Override
    public String toString() {
        return "Ingredient ID: " + ingredientId + "\n" +
               "Recipe ID: " + recipeId + "\n" +
               "Name: " + name + "\n" +
               "Quantity: " + quantity + " " + unit;
    }
}
