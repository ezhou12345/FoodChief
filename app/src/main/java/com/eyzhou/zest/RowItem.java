package com.eyzhou.zest;

/**
 * Created by eyzhou on 1/6/16.
 */
public class RowItem {
    private String title;
    private String icon;
    private int time;
    private int stars;
    private String summary;

//    private int prep_time;
//    private int cook_time;
    private int dollar_signs;
    private String ingredients;
    private String instructions;
    private String nutrition;

    public RowItem(String title, String icon, int time, int stars, String summary, int dollar_signs,
                   String ingredients, String instructions, String nutrition) {
        this.title = title;
        this.icon = icon;
        this.time = time;
        this.stars = stars;
        this.summary = summary;
//        this.prep_time = prep_time;
//        this.cook_time = cook_time;
        this.dollar_signs = dollar_signs;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.nutrition = nutrition;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTime() {
        return "Total time: " + time + " minutes";
    }

    public int getStars() {
        return stars;
    }

    public String getSummary() {
        return summary;
    }

//    public int getPrep() {
//        return prep_time;
//    }
//
//    public int getCook() {
//        return cook_time;
//    }

    public int getDollars() {
        return dollar_signs;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public String getNutrition() {
        return nutrition;
    }




}
