package com.feedmemore.dto;

import java.util.ArrayList;

/**
 * Created by ChatSam on 8/1/15.
 */
public class Recipe {
    private String id;
    private String recipeName;
    private String recipeDetailsUrl;

    public ArrayList<String> getDirections() {
        return directions;
    }

    public void setDirections(ArrayList<String> directions) {
        this.directions = directions;
    }

    private ArrayList<String> directions;
    private String imageUrl;


    private ArrayList<Ingredient> ingredients;


    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeDetailsUrl() {
        return recipeDetailsUrl;
    }

    public void setRecipeDetailsUrl(String recipeDetailsUrl) {
        this.recipeDetailsUrl = recipeDetailsUrl;
    }

}
