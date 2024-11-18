# Online-Recipe-Manangement-System-and-MealPlanner
 The Online Recipe Management and Meal Planner System is a comprehensive web application designed to simplify the process of meal planning and recipe management. Developed using Java and MySQL, this 
 system offers a user-friendly interface and robust backend capabilities to help users organize their culinary activities efficiently.

Project Overview
          The Online Recipe Management and Meal Planner is a Java-based application designed to help users manage their recipes, ingredients, and meal plans efficiently. It utilizes various Java concepts 
          including classes, collections, file handling, JDBC for database interactions, and user authentication.
          This system enables users to organize their recipes, manage ingredients, plan meals, and ensure seamless data handling through file operations and database storage.

### Core Functionalities
- **User Authentication:** Manage user accounts with secure login and registration.
- **Recipe Management:** Add, update, delete, and view recipes.
- **Ingredient Management:** Add, update, delete, and view ingredients.
- **Meal Plan Management:** Create and manage meal plans by associating recipes with specific days and meals.
- **File Handling:** Save and retrieve recipes, ingredients, and meal plans from files.
- **Database Integration:** Store and retrieve data using JDBC for persistent storage.


## Technology Stack

- **Programming Language:** Java
- **Database:** MySQL
- **Libraries and Tools:**
  - Collections: `ArrayList`
  - File Handling: Java I/O APIs
  - Database Connectivity: JDBC
- **Development Environment:** Eclipse
-**Dependencies:**
   MySQL Connector/J: MySQL JDBC driver for connecting Java applications to a MySQL database

# Installation and Setup
### Prerequisites
- Java Development Kit (JDK) version 11 or higher.
- MySQL installed and running.
- JDBC Driver for MySQL.
  ### Steps

1. **Clone the Repository**
    ```bash[
    git clone : https://github.com/Sridharn97/Online-Recipe-Manangement-System-and-MealPlanner.git
    cd recipe-management-meal-planner
    ```

2. **Setup MySQL Database**
    - Create a MySQL database named `mealplanner`.
    - Execute the SQL script `schema.sql` to create the necessary tables.
    - Update the database credentials in `Main.java`:
      ```java
      databaseManager = new DatabaseManager("jdbc:mysql://localhost:3306/mealplanner", "your-username", "your-password");
      ```

3. **Add MySQL Connector JAR**
    - Download the MySQL Connector JAR file from the [official MySQL website](https://dev.mysql.com/downloads/connector/j/).
    - Add the JAR file to your project’s classpath.
4.**Program Structure**
RecipeManagementMealPlanner/
├── src/
│   ├── com/
│   │   ├── mealplanner/
│   │   │   ├── models/
│   │   │   │   ├── Ingredient.java
│   │   │   │   ├── MealPlan.java
│   │   │   │   ├── MealPlanRecipe.java
│   │   │   │   ├── Recipe.java
│   │   │   │   └── User.java
│   │   │   ├── services/
│   │   │   │   ├── DatabaseManager.java
│   │   │   │   ├── FileHandler.java
│   │   │   │   ├── MealPlanService.java
│   │   │   │   ├── RecipeService.java
│   │   │   │   └── UserService.java
│   │   │   ├── Main.java
│   │   │   └── Menu.java
├── database/
│   ├── mealplanner.sql
└── lib/
    └── mysql-connector-java.jar






