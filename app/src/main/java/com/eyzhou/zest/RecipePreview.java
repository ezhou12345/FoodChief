package com.eyzhou.zest;

import org.json.JSONObject;

public class RecipePreview {
    public RecipePreview(JSONObject obj) {
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
    public int getId() { return getInt("id"); }
    public String getTitle() { return getString("title"); }
    public String getImageUrl() { return getString("image"); }
    public int getUsedIngredientCount() { return getInt("usedIngredientCount"); }
    public int getMissedIngredientCount() { return getInt("missedIngredientCount"); }
    public int getLikes() { return getInt("likes"); }
}
