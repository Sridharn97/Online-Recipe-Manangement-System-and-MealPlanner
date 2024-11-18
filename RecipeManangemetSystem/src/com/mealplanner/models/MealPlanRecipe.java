package com.mealplanner.models;

public class MealPlanRecipe {
    private int mealPlanId;
    private int recipeId;
    private String dayOfWeek;
    private String mealType;

    public MealPlanRecipe(int mealPlanId, int recipeId, String dayOfWeek, String mealType) {
        this.setMealPlanId(mealPlanId);
        this.setRecipeId(recipeId);
        this.setDayOfWeek(dayOfWeek);
        this.setMealType(mealType);
    }

    public int getMealPlanId() {
        return mealPlanId;
    }

    public void setMealPlanId(int mealPlanId) {
        this.mealPlanId = mealPlanId;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    @Override
    public String toString() {
        return "MealPlanId: " + mealPlanId + "\n" +
               "RecipeId: " + recipeId + "\n" +
               "DayOfWeek: " + dayOfWeek + "\n" +
               "MealType: " + mealType;
    }
}
