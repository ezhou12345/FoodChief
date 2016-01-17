package com.eyzhou.zest;

/**
 * Created by ddirenzo on 1/16/2016.
 */
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

    private static RecipeIngredient parseRecipeIngredient(JSONObject obj) {
        RecipeIngredient ingredient = new RecipeIngredient();
        try {
            ingredient.name = obj.getString("name");
            ingredient.aisle = obj.getString("aisle");
            ingredient.amount = obj.getDouble("amount");
            ingredient.unit = obj.getString("unit");
            ingredient.unitShort = obj.getString("unitShort");
            ingredient.unitLong = obj.getString("unitLong");
            ingredient.originalString = obj.getString("originalString");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ingredient;
    }

    private static Recipe parseRecipe(JSONObject obj) {
        Recipe recipe = new Recipe();
        try {
            recipe.id = obj.getInt("id");
            recipe.title = obj.getString("title");
            recipe.readyInMinutes = obj.getInt("readyInMinutes");
            JSONArray urls = obj.getJSONArray("imageUrls");
            recipe.imageUrls = new String[urls.length()];
            for (int i = 0; i < urls.length(); ++i) {
                recipe.imageUrls[i] = urls.getString(i);
            }
            recipe.servings = obj.getInt("servings");
            recipe.sourceUrl = obj.getString("sourceUrl");
            recipe.spoonacularSourceUrl = obj.getString("spoonacularSourceUrl");
            recipe.aggregateLikes = obj.getInt("aggregateLikes");
            recipe.sourceName = obj.getString("sourceName");
            JSONArray ingredients = obj.getJSONArray("extendedIngredients");
            recipe.extendedIngredients = new RecipeIngredient[ingredients.length()];
            for (int i = 0; i < ingredients.length(); ++i) {
                recipe.extendedIngredients[i] = parseRecipeIngredient(ingredients.getJSONObject(i));
            }

            recipe.vegetarian = obj.getBoolean("vegetarian");
            recipe.glutenFree = obj.getBoolean("glutenFree");
            recipe.dairyFree = obj.getBoolean("dairyFree");
            recipe.veryHealthy = obj.getBoolean("veryHealthy");
            recipe.cheap = obj.getBoolean("cheap");
            recipe.veryPopular = obj.getBoolean("veryPopular");
            recipe.sustainable = obj.getBoolean("sustainable");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recipe;
    }

    private static RecipePreview parseRecipePreview(JSONObject obj) {
        RecipePreview preview = new RecipePreview();
        try {
            preview.id = obj.getInt("id");
            preview.title = obj.getString("title");
            preview.image = obj.getString("image");
            preview.usedIngredientCount = obj.getInt("usedIngredientCount");
            preview.missedIngredientCount = obj.getInt("missedIngredientCount");
            preview.likes = obj.getInt("likes");
        } catch (Exception e) {
             e.printStackTrace();
        }
        return preview;
    }

    public static RecipePreview[] searchRecipePreviewsByIngredientList(String[] ingredients, int resultCount) {
        try {
            StringBuilder builder = new StringBuilder(BASE_URL);
            builder.append("/recipes/findByIngredients?").append(join(ingredients,"%2C"))
                    .append("&number=").append(Integer.toString(resultCount));
            HttpResponse<JsonNode> response = Unirest.get(builder.toString())
                    .header("X-Mashape-Key", MASHAPE_KEY)
                    .header("Accept", "application/json")
                    .asJson();
            JSONArray arr = response.getBody().getArray();
            RecipePreview[] previews = new RecipePreview[arr.length()];
            for (int i = 0; i < arr.length(); ++i) {
                previews[i] = parseRecipePreview(arr.getJSONObject(i));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
            return parseRecipe(response.getBody().getObject());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}