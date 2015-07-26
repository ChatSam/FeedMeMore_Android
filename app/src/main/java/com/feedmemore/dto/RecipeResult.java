package com.feedmemore.dto;

import java.util.ArrayList;

/**
 * Created by ChatSam on 7/26/15.
 */
public class RecipeResult {
    private int id;
    private String recipeName;
    private ArrayList<Ingredient> ingredients;
    private String steps;
    private String imgUrl;


    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
