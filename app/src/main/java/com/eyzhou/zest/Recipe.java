package com.eyzhou.zest;
import org.json.*;

public class Recipe {
    public Recipe(JSONObject obj) {
        this.obj = obj;
    }
    private JSONObject obj;
    private int getInt(String key) {
        try {
            return obj.getInt(key);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    private String getString(String key) {
        try {
            return obj.getString(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    private boolean getBoolean(String key) {
        try {
            return obj.getBoolean(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public int getId() { return getInt("id"); }
    public String getTitle() { return getString("title"); }
    public int getReadyInMinutes() { return getInt("readyInMinutes"); }
    public String[] getImageUrls() {
        try {
            JSONArray arr = obj.getJSONArray("imageUrls");
            String[] result = new String[arr.length()];
            for (int i = 0, l = arr.length(); i < l; ++i) {
                result[i] = arr.getString(i);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public int getServings() { return getInt("servings"); }
    public String getSourceUrl() { return getString("sourceUrl"); }
    public String getSpoonacularSourceUrl() { return getString("spoonacularSourceUrl"); }
    public int getAggregateLikes() { return getInt("aggregateLikes"); }
    public String getSourceName() { return getString("sourceName"); }
    public RecipeIngredient[] getExtendedIngredients() {
        try {
            JSONArray arr = obj.getJSONArray("extendedIngredients");
            RecipeIngredient[] result = new RecipeIngredient[arr.length()];
            for (int i = 0, l = arr.length(); i < l; ++i) {
                result[i] = new RecipeIngredient(arr.getJSONObject(i));
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public boolean getVegetarian() { return getBoolean("vegetarian"); }
    public boolean getGlutenFree() { return getBoolean("glutenFree"); }
    public boolean getDairyFree() { return getBoolean("dairyFree"); }
    public boolean getVeryHealthy() { return getBoolean("veryHealthy"); }
    public boolean getCheap() { return getBoolean("cheap"); }
    public boolean getVeryPopular() { return getBoolean("veryPopular"); }
    public boolean getSustainable() { return getBoolean("sustainable"); }
}
