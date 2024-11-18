package com.mealplanner.interfaces;

import com.mealplanner.models.MealPlan;

import java.util.List;

public interface MealPlanning {
    void createMealPlan(MealPlan mealPlan);
    MealPlan getMealPlan(int mealPlanId);
    List<MealPlan> getAllMealPlans();
    void deleteMealPlan(int mealPlanId);
}
