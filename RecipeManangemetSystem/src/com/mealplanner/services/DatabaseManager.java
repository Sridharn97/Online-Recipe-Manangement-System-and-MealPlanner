package com.mealplanner.services;

import com.mealplanner.models.Recipe;
import com.mealplanner.models.User;
import com.mealplanner.models.Ingredient;
import com.mealplanner.models.MealPlan;
import com.mealplanner.models.MealPlanRecipe;

import java.sql.*;

public class DatabaseManager {
    private Connection connection;

    public DatabaseManager(String dbUrl, String user, String password) throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mealplanner","root","Sridharn09#");
    }
    public User login(String username, String password) {

            String query = "SELECT * FROM users WHERE username = ?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    String storedPassword = rs.getString("password");
                    if (storedPassword.equals(password)) { 
                        return new User(rs.getInt("user_id"), rs.getString("username"), storedPassword);
                    }
                }
            }
         catch (SQLException e) {
            System.out.println("Error logging in: " + e.getMessage());
        }
        return null;
    }
    public boolean register(String username, String password) {

            String query = "INSERT INTO users (username, password) VALUES (?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, username);
                stmt.setString(2, password); 
                int rowsAffected = stmt.executeUpdate();
                return rowsAffected > 0;
            }
        catch (SQLException e) {
            System.out.println("Error registering user: " + e.getMessage());
        }
        return false;
    }


    public void addRecipeToDatabase(Recipe recipe) throws SQLException {
        String sql = "INSERT INTO Recipes (recipe_id, name, category, instructions, servings, prep_time, cook_time, created_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, recipe.getRecipeId());
            stmt.setString(2, recipe.getName());
            stmt.setString(3, recipe.getCategory());
            stmt.setString(4, recipe.getInstructions());
            stmt.setInt(5, recipe.getServings());
            stmt.setInt(6, recipe.getPrepTime());
            stmt.setInt(7, recipe.getCookTime());
            stmt.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
            stmt.executeUpdate();
        }
    }

    public void addIngredientToDatabase(Ingredient ingredient) throws SQLException {
        String sql = "INSERT INTO Ingredients (ingredient_id, recipe_id, name, quantity, unit) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, ingredient.getIngredientId());
            stmt.setInt(2, ingredient.getRecipeId());
            stmt.setString(3, ingredient.getName());
            stmt.setDouble(4, ingredient.getQuantity());
            stmt.setString(5, ingredient.getUnit());
            stmt.executeUpdate();
        }
    }
    public ResultSet getAllRecipes() throws SQLException {
        String sql = "SELECT * FROM Recipes";  
        Statement stmt = connection.createStatement();
        return stmt.executeQuery(sql);  
    }
    public ResultSet getAllIngredients() throws SQLException {
        String sql = "SELECT * FROM Ingredients";  
        Statement stmt = connection.createStatement();
        return stmt.executeQuery(sql);  
    }
    public ResultSet getIngredientsByRecipeId(int recipeId) throws SQLException {
        String sql = "SELECT * FROM Ingredients WHERE recipe_id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, recipeId);  
        return stmt.executeQuery();  
    }


    public void addMealPlanToDatabase(MealPlan mealPlan) throws SQLException {
        String sql = "INSERT INTO MealPlans (meal_plan_id, name, start_date, end_date, created_at) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, mealPlan.getMealPlanId());
            stmt.setString(2, mealPlan.getName());
            stmt.setDate(3, Date.valueOf(mealPlan.getStartDate()));
            stmt.setDate(4, Date.valueOf(mealPlan.getEndDate()));
            stmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            stmt.executeUpdate();
        }
    }

    public void addMealPlanRecipeToDatabase(MealPlanRecipe mealPlanRecipe) throws SQLException {
        String sql = "INSERT INTO MealPlanRecipes (meal_plan_id, recipe_id, day_of_week, meal_type) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, mealPlanRecipe.getMealPlanId());
            stmt.setInt(2, mealPlanRecipe.getRecipeId());
            stmt.setString(3, mealPlanRecipe.getDayOfWeek());
            stmt.setString(4, mealPlanRecipe.getMealType());
            stmt.executeUpdate();
        }
    }
    public void fetchMealPlans() throws SQLException {
        String sql = "SELECT mp.meal_plan_id, mp.name AS meal_plan_name, mp.start_date, mp.end_date, mp.created_at, "
                + "mpr.recipe_id, mpr.day_of_week, mpr.meal_type "
                + "FROM MealPlans mp "
                + "JOIN MealPlanRecipes mpr ON mp.meal_plan_id = mpr.meal_plan_id "
                + "ORDER BY mp.meal_plan_id, mpr.day_of_week, mpr.meal_type";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int mealPlanId = rs.getInt("meal_plan_id");
                String mealPlanName = rs.getString("meal_plan_name");
                String startDate = rs.getString("start_date");
                String endDate = rs.getString("end_date");
                String createdAt = rs.getString("created_at");
                int recipeId = rs.getInt("recipe_id");
                String dayOfWeek = rs.getString("day_of_week");
                String mealType = rs.getString("meal_type");

               
                System.out.printf("Meal Plan ID: %d, Name: %s, Start Date: %s, End Date: %s, Created At: %s\n", 
                        mealPlanId, mealPlanName, startDate, endDate, createdAt);
                System.out.printf("  Recipe ID: %d, Day: %s, Meal Type: %s\n", recipeId, dayOfWeek, mealType);
            }
        }
    }
  
    public ResultSet getAllMealPlans() throws SQLException {
        String sql = "SELECT * FROM MealPlanRecipes";  
        Statement stmt = connection.createStatement();
        return stmt.executeQuery(sql);
    }

    public void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
