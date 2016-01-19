package com.eyzhou.zest;

import android.os.AsyncTask;
import android.util.Log;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;

class Util {
    public static final String MASHAPE_KEY = "N4QXJBcElTmshOHzDoewnyT6hoLfp1JeWX2jsnEuo2v0RVRjV6";
    public static final String BASE_URL = "https://spoonacular-recipe-food-nutrition-v1.p.mashape.com";
    public static String join(String[] arr, String delimiter) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0, l = arr.length; i < l; ++i) {
            if (i > 0)
                builder.append(delimiter);
            builder.append(arr[i]);
        }
        return builder.toString();
    }
}

class recipePreviewSearch extends AsyncTask<String, Void, RecipePreview[]> {
    protected RecipePreview[] doInBackground(String[] ingredients) {
        try {
            StringBuilder builder = new StringBuilder(Util.BASE_URL);
            builder.append("/recipes/findByIngredients?")
                    .append("ingredients=").append(URLEncoder.encode(ingredients[0], "UTF-8"))
                    .append("&limitLicense=false")
                    .append("&number=1")
                    .append("&ranking=1");
            Log.d("TEST", builder.toString());
            HttpResponse<JsonNode> response = Unirest.get(builder.toString())
                    .header("X-Mashape-Key", Util.MASHAPE_KEY)
                    .header("Accept", "application/json")
                    .asJson();
            JSONArray arr = response.getBody().getArray();
            RecipePreview[] previews = new RecipePreview[arr.length()];
            for (int i = 0; i < arr.length(); ++i) {
                previews[i] = new RecipePreview(arr.getJSONObject(i));
            }
            return previews;
        } catch (Exception e) {
            Log.d("TEST", e.toString());
            return null;
        }
    }
}

class recipeSearch extends AsyncTask<Integer, Void, Recipe> {
    protected Recipe doInBackground(Integer... id) {
        try {
            StringBuilder builder = new StringBuilder(Util.BASE_URL);
            builder.append("/recipes/")
                    .append(Integer.toString(id[0]))
                    .append("/information");
            Log.d("TEST", builder.toString());
            HttpResponse<JsonNode> response = Unirest.get(builder.toString())
                    .header("X-Mashape-Key", Util.MASHAPE_KEY)
                    .asJson();
            return new Recipe(response.getBody().getObject());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

class extractInstructions extends AsyncTask<String, Void, RecipeInstructions> {
    protected RecipeInstructions doInBackground(String... url) {
        try {
            StringBuilder builder = new StringBuilder(Util.BASE_URL);
            builder.append("/recipes/extract?")
                    .append("forceExtraction=false")
                    .append("&url=").append(URLEncoder.encode(url[0], "UTF-8"))
                    .append("/information");
            Log.d("TEST", builder.toString());
            HttpResponse<JsonNode> response = Unirest.get(builder.toString())
                    .header("X-Mashape-Key", Util.MASHAPE_KEY)
                    .asJson();
            JSONObject obj = response.getBody().getObject();
            RecipeInstructions result = new RecipeInstructions();
            result.plaintext = obj.getString("text");
            result.html = obj.getString("instructions");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

public class FoodAPI {
    public static RecipePreview[] searchRecipePreviewsByIngredientList(String ingredients) {
        try {
            AsyncTask<String, Void, RecipePreview[]> r = new recipePreviewSearch().execute(ingredients);
            return r.get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Recipe getRecipeById(int id) {
        try {
            AsyncTask<Integer, Void, Recipe> r = new recipeSearch().execute(id);
            return r.get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static RecipeInstructions getRecipeInstructionsByUrl(String url) {
        try {
            AsyncTask<String, Void, RecipeInstructions> r = new extractInstructions().execute(url);
            return r.get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}