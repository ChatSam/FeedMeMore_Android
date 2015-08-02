package com.feedmemore.dto;

/**
 * Created by ChatSam on 7/26/15.
 */
public class Ingredient {
    private String name;
    private String quantity;
    private String unit;

    public Ingredient(){
        this.name = null;
        this.quantity = null;
        this.unit = null;
    }

    public Ingredient(String name, String quantity, String unit){
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
