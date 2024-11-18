package com.mealplanner.models;

public class MealPlan {
    private int mealPlanId;
    private String name;
    private String startDate;
    private String endDate;
    private String createdAt;

    public MealPlan(int mealPlanId, String name, String startDate, String endDate, String createdAt) {
        this.mealPlanId = mealPlanId;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdAt = createdAt;
    }

    public MealPlan(int mealPlanId, String name, String startDate, String endDate ) {
        this.mealPlanId = mealPlanId;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdAt = new java.sql.Timestamp(System.currentTimeMillis()).toString();
    }

    public int getMealPlanId() {
        return mealPlanId;
    }

    public String getName() {
        return name;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "MealPlanId: " + mealPlanId + "\n" +
               "Name: " + name + "\n" +
               "Start Date: " + startDate + "\n" +
               "End Date: " + endDate + "\n" +
               "Created At: " + createdAt;
    }
}
