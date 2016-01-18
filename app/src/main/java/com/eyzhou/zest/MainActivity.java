package com.eyzhou.zest;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Recipes"));
        tabLayout.addTab(tabLayout.newTab().setText("Favorites"));
        tabLayout.addTab(tabLayout.newTab().setText("Menu"));
        tabLayout.addTab(tabLayout.newTab().setText("Plan"));
        tabLayout.addTab(tabLayout.newTab().setText("Grocery"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void searchRecipes(View view) {
        EditText editText   = (EditText)findViewById(R.id.search_bar);
        String searched_text = editText.getText().toString();
//        Toast.makeText(this, searched_text, Toast.LENGTH_SHORT).show();
        String[] search_ingredients = searched_text.split(",");
        RecipePreview[] suggestions = FoodAPI.searchRecipePreviewsByIngredientList(search_ingredients, 1);

//        String title, int icon, int time, int stars, String summary, int dollar_signs,
//        String ingredients, String instructions, String nutrition

        ArrayList<String> names = new ArrayList<String>();
        ArrayList<String> images = new ArrayList<String>();
        ArrayList<Integer> time = new ArrayList<Integer>();
        ArrayList<Integer> stars = new ArrayList<Integer>(); // currently fake
        ArrayList<String> summaries = new ArrayList<String>(); // should be a list of ingredients
        ArrayList<Integer> dollars = new ArrayList<Integer>(); // boolean => 1 or 2 dollar signs
        ArrayList<String> ingredients = new ArrayList<String>(); // need to piece together to a large string
        ArrayList<String> instructions = new ArrayList<String>(); // EMPTY
        ArrayList<String> nutrition = new ArrayList<String>(); // EMPTY

        for (int i = 0; i < suggestions.length; i++) {
            Integer id = suggestions[i].getId();
            Recipe recipe = FoodAPI.getRecipeById(id);
            names.add(suggestions[i].getTitle());
            images.add(suggestions[i].getImageUrl());
            time.add(recipe.getReadyInMinutes());
            stars.add(5); // fake

            String summary = "";
            String ing = "";
            for (int j = 0; j < recipe.getExtendedIngredients().length; j++) {
                String name = recipe.getExtendedIngredients()[j].getName();
                double amount = recipe.getExtendedIngredients()[j].getAmount();
                String unit = recipe.getExtendedIngredients()[j].getUnit();

                summary = summary + name + ", ";
                ing = ing + Double.toString(amount) + " " + unit + " " + name + ", ";
            }
            summaries.add(summary);
            ingredients.add(ing);
            instructions.add("instructions"); // EMPTY
            nutrition.add("nutrition"); // EMPTY
        }
        TabFragment1.recipe_names = names;
        TabFragment1.recipe_images = images;
        TabFragment1.recipe_time = time;
        TabFragment1.recipe_stars = stars;
        TabFragment1.recipe_summaries = summaries;
        TabFragment1.recipe_dollars = dollars;
        TabFragment1.ingredients = ingredients;
        TabFragment1.instructions = instructions;
        TabFragment1.nutrition = nutrition;

    }
}
