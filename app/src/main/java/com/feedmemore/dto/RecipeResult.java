package com.feedmemore.dto;

/**
 * Created by ChatSam on 7/26/15.
 */
public class RecipeResult {


    private String id;
    private String recipeName;
    private String recipeDetailsUrl;

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
