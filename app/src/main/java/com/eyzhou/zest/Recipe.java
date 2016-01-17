package com.eyzhou.zest;
/**
 * Created by ddirenzo on 1/16/2016.
 */
public class Recipe {
    public int id;
    public String title;
    public int readyInMinutes;
    public String[] imageUrls;

    public int servings;
    public String sourceUrl;
    public String spoonacularSourceUrl;
    public int aggregateLikes;
    public String sourceName;
    public RecipeIngredient[] extendedIngredients;

    public boolean vegetarian;
    public boolean glutenFree;
    public boolean dairyFree;
    public boolean veryHealthy;
    public boolean cheap;
    public boolean veryPopular;
    public boolean sustainable;
}
