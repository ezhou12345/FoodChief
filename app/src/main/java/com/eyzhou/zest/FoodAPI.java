package com.eyzhou.zest;

import android.util.Log;

import org.json.*;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

public class FoodAPI {
    public static final String MASHAPE_KEY = "N4QXJBcElTmshOHzDoewnyT6hoLfp1JeWX2jsnEuo2v0RVRjV6";
    public static final String BASE_URL = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com";
    private static String join(String[] arr, String delimiter) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0, l = arr.length; i < l; ++i) {
            if (i > 0)
                builder.append(delimiter);
            builder.append(arr[i]);
        }
        return builder.toString();
    }

    public static RecipePreview[] searchRecipePreviewsByIngredientList(String[] ingredients, int resultCount) {
        try {
            StringBuilder builder = new StringBuilder(BASE_URL);
            builder.append("/recipes/findByIngredients?").append(join(ingredients,"%2C"))
                    .append("&number=").append(Integer.toString(resultCount));
            Log.d("TEST", builder.toString());
            return null;
//            HttpResponse<JsonNode> response = Unirest.get(builder.toString())
//                    .header("X-Mashape-Key", MASHAPE_KEY)
//                    .header("Accept", "application/json")
//                    .asJson();
//            JSONArray arr = response.getBody().getArray();
//            RecipePreview[] previews = new RecipePreview[arr.length()];
//            for (int i = 0; i < arr.length(); ++i) {
//                previews[i] = new RecipePreview(arr.getJSONObject(i));
//            }
//            return previews;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Recipe getRecipeById(int id) {
        try {
            StringBuilder builder = new StringBuilder(BASE_URL);
            builder.append("/recipes/")
                    .append(Integer.toString(id))
                    .append("/information");
            HttpResponse<JsonNode> response = Unirest.get(builder.toString())
                    .header("X-Mashape-Key", MASHAPE_KEY)
                    .asJson();
            return new Recipe(response.getBody().getObject());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}