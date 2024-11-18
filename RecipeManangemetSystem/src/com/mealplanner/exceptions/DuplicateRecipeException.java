package com.mealplanner.exceptions;

public class DuplicateRecipeException extends Exception {
    public DuplicateRecipeException(String message) {
        super(message);
    }
}
