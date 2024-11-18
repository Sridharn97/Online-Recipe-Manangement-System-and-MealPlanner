package com.mealplanner;

import com.mealplanner.models.Recipe;
import com.mealplanner.models.User;
import com.mealplanner.models.Ingredient;
import com.mealplanner.models.MealPlan;
import com.mealplanner.models.MealPlanRecipe;
import com.mealplanner.services.FileHandler;
import com.mealplanner.services.DatabaseManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static DatabaseManager databaseManager;

    public static void main(String[] args) {
        try {
            databaseManager = new DatabaseManager("jdbc:mysql://localhost:3306/mealplanner", "root", "Sridharn09#");

            Scanner scanner = new Scanner(System.in);
            boolean running = true;

            while (running) {
                System.out.println("-------------------Online Recipe Management And Meal Planner--------------------------------");
                System.out.println("\nMenu:");
                System.out.println("1. Login");
                System.out.println("2. User Registration");
                System.out.println("3. Quit");

                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        if (login(scanner)) {
                            userMenu(scanner, databaseManager);
                        }
                        break;
                    case 2:
                        register(scanner);
                        break;
                    case 3:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (databaseManager != null) {
                    databaseManager.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static boolean login(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = databaseManager.login(username, password);
        if (user != null) {
            System.out.println("Login successful. Welcome, " + user.getUsername() + "!");
            return true;
        } else {
            System.out.println("Invalid username or password.");
            return false;
        }
    }

    private static void register(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        boolean success = databaseManager.register(username, password);
        if (success) {
            System.out.println("Registration successful. You can now log in.");
        } else {
            System.out.println("Error during registration.");
        }
    }

    private static void userMenu(Scanner scanner, DatabaseManager databaseManager) {
        boolean userLoggedIn = true;

        while (userLoggedIn) {
            System.out.println("\nUser Menu:");
            System.out.println("1. Add Recipe");
            System.out.println("2. Add Ingredient");
            System.out.println("3. View All Recipes");
            System.out.println("4. View All Ingredients");
            System.out.println("5. View Ingredients By Recipe Id");
            System.out.println("6. Add Meal Plan");
            System.out.println("7. Read Recipes From File");
            System.out.println("8. Read Ingredients From File");
            System.out.println("9. Read Meal Plans From File");
            System.out.println("10. Read Meal Plans Recipe From File");
            System.out.println("11. Logout");

            System.out.print("Enter your choice: ");
            int userChoice = scanner.nextInt();
            scanner.nextLine();

            switch (userChoice) {
                case 1:
                    addRecipe(scanner, databaseManager);
                    break;
                case 2:
                    addIngredient(scanner, databaseManager);
                    break;
                case 3:
                    displayAllRecipes(scanner, databaseManager);
                    break;
                case 4:
                    displayAllIngredients(scanner, databaseManager);
                    break;
                case 5:
                    displayIngredientsByRecipeId(scanner, databaseManager);
                    break;
                case 6:
                    addMealPlan(scanner, databaseManager);
                    break;
                case 7:
                    readRecipesFromFile();
                    break;
                case 8:
                    readIngredientsFromFile();
                    break;
                case 9:
                    readMealPlansFromFile();
                    break;
                case 10:
                    readMealPlanRecipesFromFile();
                    break;
                case 11:
                    userLoggedIn = false;
                    System.out.println("Logged out successfully.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addRecipe(Scanner scanner, DatabaseManager databaseManager) {
        System.out.println("\nAdd Recipe:");
        System.out.print("Enter recipe name: ");
        String name = scanner.nextLine();
        System.out.print("Enter category: ");
        String category = scanner.nextLine();
        System.out.print("Enter instructions: ");
        String instructions = scanner.nextLine();
        System.out.print("Enter servings: ");
        int servings = scanner.nextInt();
        System.out.print("Enter prep time (minutes): ");
        int prepTime = scanner.nextInt();
        System.out.print("Enter cook time (minutes): ");
        int cookTime = scanner.nextInt();
        scanner.nextLine();

        Recipe recipe = new Recipe(0, name, category, instructions, servings, prepTime, cookTime);
        saveRecipeToFile(recipe);
        try {
            databaseManager.addRecipeToDatabase(recipe);
            System.out.println("Recipe added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding recipe: " + e.getMessage());
        }
    }

    private static void addIngredient(Scanner scanner, DatabaseManager databaseManager) {
        System.out.println("\nAdd Ingredient:");

        System.out.print("Enter recipe ID to associate ingredient with: ");
        int recipeId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter ingredient name: ");
        String ingredientName = scanner.nextLine();
        System.out.print("Enter quantity: ");
        double quantity = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter unit (e.g., cups, grams): ");
        String unit = scanner.nextLine();

        Ingredient ingredient = new Ingredient(0, recipeId, ingredientName, quantity, unit);
        saveIngredientToFile(ingredient);
        try {
            databaseManager.addIngredientToDatabase(ingredient);
            System.out.println("Ingredient added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding ingredient: " + e.getMessage());
        }
    }

    private static void displayAllRecipes(Scanner scanner, DatabaseManager databaseManager) {
        try {
            ResultSet resultSet = databaseManager.getAllRecipes();
            System.out.println("\nAll Recipes:");
            while (resultSet.next()) {
                int recipeId = resultSet.getInt("recipe_id");
                String name = resultSet.getString("name");
                String category = resultSet.getString("category");
                String instructions = resultSet.getString("instructions");
                int servings = resultSet.getInt("servings");
                int prepTime = resultSet.getInt("prep_time");
                int cookTime = resultSet.getInt("cook_time");

                System.out.println("Recipe ID: " + recipeId);
                System.out.println("Name: " + name);
                System.out.println("Category: " + category);
                System.out.println("Instructions: " + instructions);
                System.out.println("Servings: " + servings);
                System.out.println("Prep Time: " + prepTime + " minutes");
                System.out.println("Cook Time: " + cookTime + " minutes");
                System.out.println("-----------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching recipes: " + e.getMessage());
        }
    }

    private static void displayAllIngredients(Scanner scanner, DatabaseManager databaseManager) {
        try {
            ResultSet resultSet = databaseManager.getAllIngredients();
            System.out.println("\nAll Ingredients:");
            while (resultSet.next()) {
                int ingredientId = resultSet.getInt("ingredient_id");
                int recipeId = resultSet.getInt("recipe_id");
                String name = resultSet.getString("name");
                double quantity = resultSet.getDouble("quantity");
                String unit = resultSet.getString("unit");

                System.out.println("Ingredient ID: " + ingredientId);
                System.out.println("Recipe ID: " + recipeId);
                System.out.println("Name: " + name);
                System.out.println("Quantity: " + quantity);
                System.out.println("Unit: " + unit);
                System.out.println("-----------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Error fetching ingredients: " + e.getMessage());
        }
    }

    private static void displayIngredientsByRecipeId(Scanner scanner, DatabaseManager databaseManager) {
        System.out.print("Enter Recipe ID to view ingredients: ");
        int recipeId = scanner.nextInt();
        scanner.nextLine();

        try {
            ResultSet resultSet = databaseManager.getIngredientsByRecipeId(recipeId);
            System.out.println("\nIngredients for Recipe ID " + recipeId + ":");
            if (!resultSet.next()) {
                System.out.println("No ingredients found for this recipe ID.");
            } else {
                do {
                    int ingredientId = resultSet.getInt("ingredient_id");
                    String name = resultSet.getString("name");
                    double quantity = resultSet.getDouble("quantity");
                    String unit = resultSet.getString("unit");

                    System.out.println("Ingredient ID: " + ingredientId);
                    System.out.println("Name: " + name);
                    System.out.println("Quantity: " + quantity);
                    System.out.println("Unit: " + unit);
                    System.out.println("-----------------------------");
                } while (resultSet.next());
            }
        } catch (SQLException e) {
            System.out.println("Error fetching ingredients: " + e.getMessage());
        }
    }

    private static void addMealPlan(Scanner scanner, DatabaseManager databaseManager) {
        System.out.println("\nAdd Meal Plan:");
        System.out.print("Enter meal plan name: ");
        String mealPlanName = scanner.nextLine();
        System.out.print("Enter start date (YYYY-MM-DD): ");
        String startDate = scanner.nextLine();
        System.out.print("Enter end date (YYYY-MM-DD): ");
        String endDate = scanner.nextLine();

        MealPlan mealPlan = new MealPlan(0, mealPlanName, startDate, endDate);
        saveMealPlanToFile(mealPlan);
        try {
            databaseManager.addMealPlanToDatabase(mealPlan);
            System.out.println("Meal plan added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding meal plan: " + e.getMessage());
        }

        System.out.print("Do you want to add recipes to this meal plan? (yes/no): ");
        String addRecipesChoice = scanner.nextLine();
        if (addRecipesChoice.equalsIgnoreCase("yes")) {
            addMealPlanRecipes(scanner, databaseManager, mealPlan.getMealPlanId());
        }
    }

    private static void addMealPlanRecipes(Scanner scanner, DatabaseManager databaseManager, int mealPlanId) {
        System.out.println("\nAdd Recipes to Meal Plan:");

        System.out.print("Enter number of recipes to add: ");
        int numberOfRecipes = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < numberOfRecipes; i++) {
            System.out.print("Enter recipe ID: ");
            int recipeId = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter day of week (e.g., Monday): ");
            String dayOfWeek = scanner.nextLine();
            System.out.print("Enter meal type (e.g., Breakfast, Lunch, Dinner): ");
            String mealType = scanner.nextLine();

            MealPlanRecipe mealPlanRecipe = new MealPlanRecipe(mealPlanId, recipeId, dayOfWeek, mealType);
            saveMealPlanRecipeToFile(mealPlanRecipe);
            
            try {
                databaseManager.addMealPlanRecipeToDatabase(mealPlanRecipe);
                System.out.println("Recipe added to meal plan successfully.");
            } catch (SQLException e) {
                System.out.println("Recepie Plan Saved To The File Successfully " );
            }
        }
    }

    private static void readRecipesFromFile() {
        FileHandler.readRecipesFromFile();
    }

    private static void readIngredientsFromFile() {
        FileHandler.readIngredientsFromFile();
    }

    private static void readMealPlansFromFile() {
        FileHandler.readMealPlansFromFile();
    }

    private static void readMealPlanRecipesFromFile() {
        FileHandler.readMealPlanRecipesFromFile();
    }

    private static void saveRecipeToFile(Recipe recipe) {
        FileHandler.saveRecipeToFile(recipe);
    }

    private static void saveIngredientToFile(Ingredient ingredient) {
        FileHandler.saveIngredientToFile(ingredient);
    }

    private static void saveMealPlanToFile(MealPlan mealPlan) {
        FileHandler.saveMealPlanToFile(mealPlan);
    }
    private static void saveMealPlanRecipeToFile(MealPlanRecipe mealplanrecipe) {
    	FileHandler.saveMealPlanRecipeToFile(mealplanrecipe);
    }
}
