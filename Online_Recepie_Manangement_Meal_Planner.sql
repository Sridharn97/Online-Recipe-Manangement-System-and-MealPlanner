create database mealplanner

use mealplanner
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);


CREATE TABLE Recipes (
    recipe_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    category VARCHAR(50) NOT NULL,
    instructions TEXT NOT NULL,
    servings INT NOT NULL,
    prep_time INT NOT NULL,
    cook_time INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
select * from Recipes
CREATE TABLE Ingredients (
    ingredient_id INT AUTO_INCREMENT PRIMARY KEY,
    recipe_id INT,
    name VARCHAR(100),
    quantity DECIMAL(5,2),
    unit VARCHAR(20),
    FOREIGN KEY (recipe_id) REFERENCES Recipes(recipe_id)
);


select * from Ingredients
CREATE TABLE MealPlans (
    meal_plan_id INT AUTO_INCREMENT PRIMARY KEY,  
    name VARCHAR(100) NOT NULL,                  
    start_date DATE NOT NULL,                     
    end_date DATE NOT NULL,                       
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP 
);

CREATE TABLE MealPlanRecipes (
    meal_plan_recipe_id INT AUTO_INCREMENT PRIMARY KEY,  
    meal_plan_id INT,                                     
    recipe_id INT,                                        
    day_of_week VARCHAR(15) NOT NULL,                     
    meal_type VARCHAR(20) NOT NULL,                       
    FOREIGN KEY (meal_plan_id) REFERENCES MealPlans(meal_plan_id), 
    FOREIGN KEY (recipe_id) REFERENCES Recipes(recipe_id)  
);


select * from MealPlans;
select * from MealPlanRecipes;
select * from users;
