package com.mealplanner.services;

import com.mealplanner.models.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {


    private static final String RECIPE_FILE = "recipes.txt";
    private static final String INGREDIENT_FILE = "ingredients.txt";
    private static final String MEAL_PLAN_FILE = "mealplans.txt";
    private static final String MEAL_PLAN_RECIPE_FILE = "mealplan_recipes.txt"; 

 
    public static void saveRecipeToFile(Recipe recipe) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RECIPE_FILE, true))) {
           
            writer.write(recipe.toString()); 
            writer.newLine();  
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

  
    public static void saveIngredientToFile(Ingredient ingredient) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(INGREDIENT_FILE, true))) {
      
            writer.write(ingredient.toString());  
            writer.newLine();  
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    public static void saveMealPlanToFile(MealPlan mealPlan) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(MEAL_PLAN_FILE, true))) {
       
            writer.write(mealPlan.toString());  
            writer.newLine(); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void saveMealPlanRecipeToFile(MealPlanRecipe mealPlanRecipe) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(MEAL_PLAN_RECIPE_FILE, true))) {
            
            writer.write(mealPlanRecipe.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static List<Recipe> readRecipesFromFile() {
        List<Recipe> recipes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("recipes.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
  
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split(",");
                if (data.length != 7) {
                    System.out.println(" " + line);
                    continue;
                }

                try {
                    int recipeId = Integer.parseInt(data[0].trim());
                    String name = data[1].trim();
                    String category = data[2].trim();
                    String instructions = data[3].trim();
                    int servings = Integer.parseInt(data[4].trim());
                    int prepTime = Integer.parseInt(data[5].trim());
                    int cookTime = Integer.parseInt(data[6].trim());

                 
                    Recipe recipe = new Recipe(recipeId, name, category, instructions, servings, prepTime, cookTime);
                    recipes.add(recipe);
                } catch (NumberFormatException e) {
                    System.out.println("" + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading recipes from file: " + e.getMessage());
        }
        return recipes;
    }

    
    public static List<Ingredient> readIngredientsFromFile() {
        List<Ingredient> ingredients = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("ingredients.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
        
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split(",");
                if (data.length != 5) {
                    System.out.println(" " + line);
                    continue;
                }

                try {
                    int ingredientId = Integer.parseInt(data[0].trim());
                    int recipeId = Integer.parseInt(data[1].trim());
                    String name = data[2].trim();
                    double quantity = Double.parseDouble(data[3].trim());
                    String unit = data[4].trim();

                    Ingredient ingredient = new Ingredient(ingredientId, recipeId, name, quantity, unit);
                    ingredients.add(ingredient);
                } catch (NumberFormatException e) {
                    System.out.println(" " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading ingredients from file: " + e.getMessage());
        }
        return ingredients;
    }

    public static List<MealPlan> readMealPlansFromFile() {
        List<MealPlan> mealPlans = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("mealplans.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                
                if (line.trim().isEmpty()) {
                    continue;
                }

                
                String[] data = line.split(",");
                if (data.length != 5) {
                    System.out.println(" " + line);
                    continue;
                }

                try {
                    int mealPlanId = Integer.parseInt(data[0].trim());   
                    String name = data[1].trim();                         
                    String startDate = data[2].trim();                    
                    String endDate = data[3].trim();                      
                    String createdAt = data[4].trim();                    

                    MealPlan mealPlan = new MealPlan(mealPlanId, name, startDate, endDate, createdAt);
                    mealPlans.add(mealPlan);
                } catch (NumberFormatException e) {
                    System.out.println("Skipping line with invalid number format: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading meal plans from file: " + e.getMessage());
        }
        return mealPlans;
    }


    public static List<MealPlanRecipe> readMealPlanRecipesFromFile() {
        List<MealPlanRecipe> mealPlanRecipes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("mealplan_recipes.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }


                String[] data = line.split(",");
                if (data.length != 4) {
                    System.out.println(" " + line);
                    continue;
                }

                try {
                    int mealPlanId = Integer.parseInt(data[0].trim());   
                    int recipeId = Integer.parseInt(data[1].trim());     
                    String dayOfWeek = data[2].trim();                   
                    String mealType = data[3].trim();                    

                    
                    MealPlanRecipe mealPlanRecipe = new MealPlanRecipe(mealPlanId, recipeId, dayOfWeek, mealType);
                    mealPlanRecipes.add(mealPlanRecipe);
                } catch (NumberFormatException e) {
                    System.out.println(" " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading meal plan recipes from file: " + e.getMessage());
        }
        return mealPlanRecipes;
    }

    }