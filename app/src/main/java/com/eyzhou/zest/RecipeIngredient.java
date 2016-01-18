package com.eyzhou.zest;

import org.json.*;

public class RecipeIngredient {
    public RecipeIngredient(JSONObject obj) {
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
    private double getDouble(String key) {
        try {
            return obj.getDouble(key);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public String getName() { return getString("name"); }
    public String getAisle() { return getString("aisle"); }
    public double getAmount() { return getDouble("amount"); }
    public String getUnit() { return getString("unit"); }
    public String getUnitShort() { return getString("unitShort"); }
    public String getUnitLong() { return getString("unitLong"); }
    public String getOriginalString() { return getString("originalString"); }

}
