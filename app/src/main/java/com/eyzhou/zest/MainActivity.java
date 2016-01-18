package com.eyzhou.zest;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

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

    public void deleteRecipe (View view) {
//            Toast.makeText(this, "-", Toast.LENGTH_SHORT).show();
            View parentRow = (View) view.getParent();
            ListView listView = (ListView) parentRow.getParent();
            final int position = listView.getPositionForView(parentRow);
            TabFragment3.recipe_indices.remove(position);

            List<RowItem> new_menuItems;
            new_menuItems = new ArrayList<RowItem>();
            for (int i = 0; i < TabFragment3.recipe_indices.size(); i++) {
                Integer index = TabFragment3.recipe_indices.get(i);
                RowItem item = new RowItem(TabFragment1.recipe_names.get(index), TabFragment1.recipe_images.get(index), TabFragment1.recipe_time.get(index),
                        TabFragment1.recipe_stars.get(index), TabFragment1.recipe_summaries.get(index),  TabFragment1.recipe_dollars.get(index), TabFragment1.ingredients.get(index),
                        TabFragment1.instructions.get(index), TabFragment1.nutrition.get(index));
                new_menuItems.add(item);
            }


            ListAdapterSmall new_adapter = new ListAdapterSmall(this, new_menuItems);// adapter with new data
            listView.setAdapter(new_adapter);
            new_adapter.notifyDataSetChanged();
    }


    public void searchRecipes(View view) {
        EditText editText   = (EditText)findViewById(R.id.search_bar);
        String searched_text = editText.getText().toString();
        if (searched_text.matches("")) {
            Toast.makeText(this, "Please enter some ingredients", Toast.LENGTH_SHORT).show();
            return;
        }
//        Toast.makeText(this, searched_text, Toast.LENGTH_SHORT).show();
        String[] search_ingredients = searched_text.split(", ");
        RecipePreview[] suggestions = FoodAPI.searchRecipePreviewsByIngredientList(search_ingredients);

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

        if (suggestions != null)
            Log.d("TEST", "Successful Request. Got back " + Integer.toString(suggestions.length) + " results.");
        else
            Log.d("TEST", "Unsuccessful Request.");

        for (int i = 0; i < suggestions.length; i++) {
            Integer id = suggestions[i].getId();
            Recipe recipe = FoodAPI.getRecipeById(id);
            names.add(suggestions[i].getTitle());
            images.add(suggestions[i].getImageUrl());
            time.add(recipe.getReadyInMinutes());
            stars.add(5); // fake
            String summary = "";
            String ing = "";
            RecipeIngredient[] ingredient_list = recipe.getExtendedIngredients();
            for (int j = 0; j < ingredient_list.length; j++) {
                String name = ingredient_list[j].getName();
                double amount = ingredient_list[j].getAmount();
                String unit = ingredient_list[j].getUnit();
                summary = summary + name + ", ";
                ing = ing + Double.toString(amount) + " " + unit + " " + name + ", ";
            }
            if (recipe.getCheap()) {
                dollars.add(1);
            } else { dollars.add(2); }
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

        Log.d("TEST", "names: " + Integer.toString(TabFragment1.recipe_names.size()));
        Log.d("TEST", "images: " + Integer.toString(TabFragment1.recipe_images.size()));
        Log.d("TEST", "time: " + Integer.toString(TabFragment1.recipe_time.size()));
        Log.d("TEST", "stars: " + Integer.toString(TabFragment1.recipe_stars.size()));
        Log.d("TEST", "summaries: " + Integer.toString(TabFragment1.recipe_summaries.size()));
        Log.d("TEST", "dollars: " + Integer.toString(TabFragment1.recipe_dollars.size()));
        Log.d("TEST", "ingredients: " + Integer.toString(TabFragment1.ingredients.size()));
        Log.d("TEST", "instructions: " + Integer.toString(TabFragment1.instructions.size()));
        Log.d("TEST", "nutrition: " + Integer.toString(TabFragment1.nutrition.size()));

        // update list adapter
        List<RowItem> new_recipes;
        new_recipes = new ArrayList<RowItem>();
        for (int i = 0; i < TabFragment1.recipe_names.size(); i++) {
            RowItem item = new RowItem(TabFragment1.recipe_names.get(i),
                    TabFragment1.recipe_images.get(i),
                    TabFragment1.recipe_time.get(i),
                    TabFragment1.recipe_stars.get(i),
                    TabFragment1.recipe_summaries.get(i),
                    TabFragment1.recipe_dollars.get(i),
                    TabFragment1.ingredients.get(i),
                    TabFragment1.instructions.get(i),
                    TabFragment1.nutrition.get(i));
            new_recipes.add(item);
        }
        ListAdapter new_adapter = new ListAdapter(this, new_recipes);
        ListView listview1 = TabFragment1.listview1;
        listview1.setAdapter(new_adapter);
//        listview1.setOnItemClickListener(this);

    }
}
