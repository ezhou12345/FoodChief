package com.eyzhou.zest;

import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by eyzhou on 12/29/15.
 */
public class TabFragment1 extends android.support.v4.app.ListFragment implements AdapterView.OnItemClickListener {

    public static ArrayList<String> recipe_names = new ArrayList(Arrays.asList(
            "Easy Pasta Primavera",
            "Basic Chili",
            "Corn & Black Bean Pizza",
            "Black-Eyed Peas with Pork & Beans",
            "Kale Salad with Apples and Cheddar",
            "Fajita-Style Quesadillas",
            "Chicken & Sun-Dried Tomato Orzo"
    ));
    public static ArrayList<String> recipe_images = new ArrayList(Arrays.asList(
//            R.drawable.easy_pasta_premavera,
//            R.drawable.basic_chili,
//            R.drawable.corn_black_bean_pizza,
//            R.drawable.black_eyed_peas_with_pork_and_greens,
//            R.drawable.kale_salad_apples_cheddar,
//            R.drawable.fajita_style_quesadillas,
//            R.drawable.chicken_sun_dried_tomato_orzo
            "http://www.mastercook.com/app/Image/1695005/840599.jpg",
            "http://www.budgetbytes.com/wp-content/uploads/2013/12/Basic-Chili-front.jpg",
            "http://www.healthforthewholeself.com/wp-content/uploads/2010/08/IMG_8518.jpg",
            "http://bed56888308e93972c04-0dfc23b7b97881dee012a129d9518bae.r34.cf1.rackcdn.com/sites/default/files/imagecache/standard/recipes/MK6757.JPG",
            "http://graphics8.nytimes.com/images/2010/12/28/science/31recipehealthnew/31recipehealthnew-articleLarge-v2.jpg",
            "https://vessysday.files.wordpress.com/2012/09/quesadillas-1-of-1-22.jpg",
            "http://assets.eatingwell.com/sites/default/files/imagecache/standard/recipes/MP6419.JPG"

    ));
    public static ArrayList<Integer> recipe_time = new ArrayList(Arrays.asList(20,35,10,45,5,30,30));
    public static ArrayList<Integer> recipe_stars = new ArrayList(Arrays.asList(4,4,5,5,4,5,5));
    public static ArrayList<String> recipe_summaries =new ArrayList(Arrays.asList(
            "Wagon wheel pasta, mixed frozen vegetables, cream cheese spread with chive and onion, milk, salt, ground black pepper, parmesan cheese",
            "Olive oil, yellow onion, garlic, ground beef, kidney beans, black beans, tomatoes, tomato paste, chili powder, ground cumin, cayenne powder, garlic powder, onion powder, brown sugar, salt, ground pepper",
            "Plum tomato, black beans, frozen corn, pizza crust, barbecue sauce, shredded Mexican-blend cheese/mozzarella",
            "Boneless pork chops, onion, tomato paste, instant brown rice, kale leaves, garlic, chicken broth, vinegar, black-eyed peas, various seasoning",
            "Kale, toasted almonds, apple, sharp cheddar cheese, lemon juice, garlic, parmesan",
            "Red pepper, onion, serrano pepper, white corn tortillas, Monterery Jack cheese, tomato, cilantro, chicken breast, cooked beans, sour cream, lime",
            "Orzo, sun-dried tomatoes, tomato, garlic, fresh marjoram, red-wine vinegar, chicken breast, artichoke hearts, Romano cheese, seasoning"
    ));

    public static ArrayList<Integer> recipe_dollars = new ArrayList(Arrays.asList(1,4,2,3,3,2,2));
    public static ArrayList<String> ingredients = new ArrayList(Arrays.asList(
            "Wagon wheel pasta\n" +
            "8 oz package frozen mixed vegetables \n" +
            "16 oz cream cheese spread with chive and onion \n" +
            "1/2 of 8 oz tub milk \n" +
            "1/4 cup salt (to taste)\n" +
            "Ground black pepper (to taste)\n" +
            "Parmesan (to taste)",
            "2 tbsp olive oil\n" +
                    "1 yellow onion, diced\n" +
                    "2 garlic cloves, minced\n" +
                    "1 lb ground beef\n" +
                    "1 can (15 oz) kidney beans, rinsed and drained\n" +
                    "1 can (15 oz) black beans, rinsed and drained\n" +
                    "1 can (15 oz) canned diced tomatoes\n" +
                    "1 cup frozen corn\n" +
                    "1 cup water\n" +
                    "1 can (6 oz) tomato paste\n" +
                    "1 tbsp chili powder\n" +
                    "1 tsp ground cumin\n" +
                    "1/4 tsp cayenne powder\n" +
                    "1/4 tsp garlic powder\n" +
                    "1/2 tsp onion powder\n" +
                    "1 tbsp brown sugar\n" +
                    "1 tsp salt\n" +
                    "1/2 tsp ground pepper",
            "1 plum tomato, diced\n" +
                    "1 cup canned black beans, rinsed\n" +
                    "1 cup frozen corn \n" +
                    "1 large pizza crust - store-bought\n" +
                    "1/3 cup barbecue sauce\n" +
                    "1 cup shredded Mexican-blend cheese/Mozzarella",
            "1 lb boneless pork chops, trimmed, cut into 1/2 inch pieces\n" +
                    "1/2 tsp salt, divided\n" +
                    "1/4 tsp freshly ground pepper\n" +
                    "1 tbsp olive oil\n" +
                    "1 medium onion, chopped\n" +
                    "2 tbsp tomato paste\n" +
                    "1 cup instant brown rice\n" +
                    "8 cups (about 1 small bunch) roughly chopped kale leaves\n" +
                    "4 minced garlic cloves\n" +
                    "1 14 oz can reduced-sodium chicken broth\n" +
                    "2 tbsp cider vinegar or sherry vinegar\n" +
                    "1/2 tsp smoked paprika\n" +
                    "1 15 oz can black-eyed peas, rinsed",
            "4 cups finely chopped kale (about 6 oz or half of a 3/4 pound bunch)\n" +
                    "2 tbsp coarsely chopped toasted almonds\n" +
                    "1 apple (Fuji, Gala, or Pink Lady)\n" +
                    "1 oz sharp cheddar cheese, cut in 1/4 inch dice\n" +
                    "2 tbsp fresh lemon juice\n" +
                    "1 very small garlic clove, pureed\n" +
                    "5 tbsp olive oil \n" +
                    "2 tbsp grated Parmesan",
            "1/2 red pepper\n" +
                    "1/2 medium onion\n" +
                    "1 serrano pepper\n" +
                    "2 tsp vegetable oil\n" +
                    "6 inch white corn tortillas\n" +
                    "1/2 cup (2 oz) shredded Monterery Jack cheese\n" +
                    "tomato 2 thin slices (1/4 tomato)\n" +
                    "1 tbsp fresh cilantro\n" +
                    "2 chicken breasts\n" +
                    "1/2 of 15 oz can cooked beans\n" +
                    "light dairy sour cream (to taste)\n" +
                    "lime (to taste)",
            "8 oz orzo, preferably whole-wheat\n" +
                    "1 cup water\n" +
                    "1/2 cup chopped sun-dried tomatoes, divided\n" +
                    "1 plum tomato, diced\n" +
                    "1 garlic clove, peeled\n" +
                    "3 chopped fresh marjoram, divided\n" +
                    "1 tbsp red-wine vinegar\n" +
                    "2 tsp and 1 tbsp olive oil, divided\n" +
                    "4 boneless, skinless chicken breasts, (1 to 1 1/4 pounds)\n" +
                    "1/4 tsp salt\n" +
                    "1/4 tsp freshly ground pepper\n" +
                    "1 9-oz package forzen artichoke hearts, thawed\n" +
                    "1/2 cup finely shredded Romano cheese\n"
    ));
    public static ArrayList<String> instructions = new ArrayList(Arrays.asList(
            "1) In a Dutch oven (large cooking pot), cook pasta in a large amount of boiling, lightly salted water for 4 minutes. Add frozen vegetables. Cook about 5 minutes more or until pasta and vegetables are tender; drain. Return pasta mixture to hot pan.\n" +
            "2) Add cream cheese spread to pasta mixture. Cook until heated through, stirring occasionally. Stir in enough of the milk to reach desired consistency. Season to taste with salt and pepper. Sprinkle with Parmesan cheese before serving.",
    "1) Dice the onion and mince the garlic. Add both to a large pot with the olive oil and cook over medium heat until they are soft and transparent. Add the ground beef and continue to sauté until the beef is fully browned. \n" +
            "2) Drain the beans and add them to the pot along with the diced tomatoes, tomato paste, 1 cup water, and all of the ingredients for the chili seasoning. Stir until well combined. Place a lid on the pot and allow it to simmer over a low flame for at least 15 minutes, stirring occasionally (the flavor gets better the longer it simmers).",
    "1. Combine tomatoes, beans, and corn in a medium bowl.\n" +
            "2. Place cooked pizza crust on grill over medium heat. Spread with BBQ sauce, then top with tomato/bean/corn mixture. Sprinkle evenly with cheese.\n" +
            "3. Close the lid and grill until the cheese is melted, all ingredients are heated through, and the crust is slightly crispy – about 5 minutes.",
    "1) Toss pork with 1/4 teaspoon salt and pepper. Heat oil in a large nonstick skillet over medium heat.\n" +
            "2) Add the pork and cook, stirring, until just cooked through, 4 to 6 minutes. \n" +
            "3) Transfer to a bowl with a slotted spoon. Add onion, tomato paste and rice to the pan and cook until the onion softens, about 4 minutes. \n" +
            "4) Add kale and garlic and cook until the kale begins to wilt, 1 to 2 minutes. \n" +
            "5) Stir in broth, vinegar, paprika and the remaining 1/4 teaspoon salt. Bring to a boil. Cover, reduce heat and simmer until the rice is done, 15 to 20 minutes. \n" +
            "6) Stir in the reserved pork and black-eyed peas and heat for 1 minute.",
    "1) Combine the kale, almonds, apple and Cheddar in a large bowl.\n" +
            "2) Puree garlic: Crush the garlic with the side of a knife and remove the skin.  Cut the clove in half and roughly chop.  Sprinkle fine salt on to the garlic and then start crushing with the side of the knife.  The salt draws out the water and softens the garlic, allowing it to turn to a purée.\n"+
            "3) Whisk together the lemon juice, salt, garlic and olive oil. Add to the salad, and toss well. Sprinkle the Parmesan over the top, and serve.",
    "1) In a large skillet cook sweet pepper, onion, and serrano pepper in hot oil over medium-high heat for 3 to 5 minutes or until vegetables are just tender. Remove from heat. \n"+
            "2) Lightly coat one side of each tortilla with cooking spray. On the uncoated side of two of the tortillas, divide half of the cheese. Top with onion mixture, tomato slices, the 1 tablespoon cilantro, and the remaining cheese. Top with remaining tortillas, coated sides up.\n"+
            "3) Heat a very large skillet or griddle over medium heat. Cook quesadillas for 4 to 5 minutes per side or until cheese melts and tortillas are lightly browned. Cut each quesadilla into 4 wedges. Serve warm and, if desired, with sour cream, additional cilantro and lime wedges.",
    "1) Cook orzo in a large saucepan of boiling water until just tender, 8 to 10 minutes or according to package directions. Drain and rinse.\n"+
            "2) Meanwhile, place 1 cup water, 1/4 cup sun-dried tomatoes, plum tomato, garlic, 2 teaspoons marjoram, vinegar and 2 teaspoons oil in a blender. Blend until just a few chunks remain.\n" +
            "3) Season chicken with salt and pepper on both sides. Heat remaining 1 tablespoon oil in a large skillet over medium-high heat. Add the chicken and cook, adjusting the heat as necessary to prevent burning, until golden outside and no longer pink in the middle, 3 to 5 minutes per side. Transfer to a plate; tent with foil to keep warm.\n" +
            "4) Pour the tomato sauce into the pan and bring to a boil. Measure out 1/2 cup sauce to a small bowl. Add the remaining 1/4 cup sun-dried tomatoes to the pan along with the orzo, artichoke hearts and 6 tablespoons cheese. Cook, stirring, until heated through, 1 to 2 minutes. Divide among 4 plates.\n" +
            "5) Slice the chicken. Top each portion of pasta with sliced chicken, 2 tablespoons of the reserved tomato sauce and a sprinkling of the remaining cheese and marjoram."
            ));
    public static ArrayList<String> nutrition = new ArrayList(Arrays.asList(
            "Per serving: 412 kcal cal., \n" +
            " 12 g fat\n" +
            " (8 g sat. fat, \n" +
            " 1 g polyunsaturated fat, \n" +
            " 1 g monounsatured fat), \n" +
            " 32 mg chol., \n" +
            " 415 mg sodium, \n" +
            " 60 g carb., \n" +
            " 6 g fiber, \n" +
            " 4 g sugar, \n" +
            " 14 g pro.\n" +
            " Percent Daily Values are based on a 2,000 calorie diet",

            "nutrition2",

            "Per serving: 316 calories \n" +
                    "6 g fat (3 g sat, 0 g mono)\n" +
                    "13 mg cholesterol \n" +
                    "48 g carbohydrates \n" +
                    "3 g added sugars \n" +
                    "14 g protein \n" +
                    "4 g fiber \n" +
                    "530 mg sodium \n" +
                    "94 mg potassium.",
            "Per serving: 281 calories\n" +
                    "8 g fat (2 g sat, 3 g mono) \n" +
                    "45 mg cholesterol \n" +
                    "32 g carbohydrates \n" +
                    "0 g added sugars \n" +
                    "22 g protein \n" +
                    "5 g fiber \n" +
                    "405 mg sodium \n" +
                    "740 mg potassium.",
            "blank nutrition",
            "Per serving: 61 kcal cal., \n" +
                    " 4 g fat\n" +
                    " (1 g sat. fat, \n" +
                    " 1 g polyunsaturated fat, \n" +
                    " 1 g monounsatured fat), \n" +
                    " 6 mg chol., \n" +
                    " 41 mg sodium, \n" +
                    " 5 g carb., \n" +
                    " 1 g fiber, \n" +
                    " 1 g sugar, \n" +
                    " 2 g pro.\n" +
                    " Percent Daily Values are based on a 2,000 calorie diet\n" +
                    " ",
            "Per serving: 457 calories\n"+
                    "12 g fat (3 g sat, 6 g mono)\n"+
                    "68 mg cholesterol\n"+
                    "54 g carbohydrates\n"+
                    "0 g added sugars\n"+
                    "36 g protein\n"+
                    "10 g fiber\n"+
                    "372 mg sodium\n"+
                    "546 mg potassium"
    ));

    public static ListView listview1;
    public static ListAdapter adapter;
    private List<RowItem> rowItems;
    public static Integer current_index = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.tab_fragment_1, container, false);
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);

        // Associate searchable configuration with the SearchView
//        SearchManager searchManager =
//                (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
//        SearchView searchView =
//                (SearchView) menu.findItem(R.id.search).getActionView();
//        searchView.setSearchableInfo(
//                searchManager.getSearchableInfo(getActivity().getComponentName()));
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        List<RowItem> rowItems;
        rowItems = new ArrayList<RowItem>();

        for (int i = 0; i < recipe_names.size(); i++) {
            RowItem item = new RowItem(recipe_names.get(i),recipe_images.get(i), recipe_time.get(i), recipe_stars.get(i), recipe_summaries.get(i),
                    recipe_dollars.get(i), ingredients.get(i), instructions.get(i), nutrition.get(i));
            rowItems.add(item);
        }
        listview1 = getListView();
        adapter = new ListAdapter(getActivity(), rowItems);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
//        listview1.setAdapter(adapter);
//        listview1.setOnItemClickListener(this);
    }

    public void refreshList() {
        List<RowItem> new_recipes;
        new_recipes = new ArrayList<RowItem>();
        for (int i = 0; i < TabFragment1.recipe_names.size(); i++) {
            RowItem item = new RowItem(TabFragment1.recipe_names.get(i),TabFragment1.recipe_images.get(i), TabFragment1.recipe_time.get(i),
                    TabFragment1.recipe_stars.get(i), TabFragment1.recipe_summaries.get(i),
                    TabFragment1.recipe_dollars.get(i), TabFragment1.ingredients.get(i), TabFragment1.instructions.get(i), TabFragment1.nutrition.get(i));
            new_recipes.add(item);
        }
        adapter = new ListAdapter(getActivity(), rowItems);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        current_index = position;
        // custom dialog
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.custom_dialog_box);
        dialog.setTitle(recipe_names.get(position));

        // set the custom dialog components - text, image and button
        TextView text = (TextView) dialog.findViewById(R.id.text);
        text.setText(recipe_names.get(position));

        ImageView image = (ImageView) dialog.findViewById(R.id.image);
        new DownloadImageTask(image).execute(recipe_images.get(position));

        TextView total_time = (TextView) dialog.findViewById(R.id.total_time);
        total_time.setText("Total time: " + recipe_time.get(position) + " minutes");
        ImageView rating = (ImageView) dialog.findViewById(R.id.rating);
        int num_stars = recipe_stars.get(position);
        if (num_stars == 1) {
            rating.setImageResource(R.drawable.one_star);
        } else if (num_stars == 2) {
            rating.setImageResource(R.drawable.two_stars);
        } else if (num_stars == 3) {
            rating.setImageResource(R.drawable.three_stars);
        } else if (num_stars == 4) {
            rating.setImageResource(R.drawable.four_stars);
        } else if (num_stars == 5) {
            rating.setImageResource(R.drawable.five_stars);
        } else {
            // unrated?
        }

        ImageView dollars = (ImageView) dialog.findViewById(R.id.dollars);
        int num_dollars = recipe_dollars.get(position);
        if (num_dollars == 1) {
            dollars.setImageResource(R.drawable.one_dollar);
        } else {
            dollars.setImageResource(R.drawable.two_dollars);
        }

        TextView summary = (TextView) dialog.findViewById(R.id.ingredients_summary);
        summary.setText(recipe_summaries.get(position));

        TextView ingred = (TextView) dialog.findViewById(R.id.ingredients);
        ingred.setText(ingredients.get(position));

        TextView instruct = (TextView) dialog.findViewById(R.id.instructions);
        instruct.setText(instructions.get(position));

        TextView nutrit = (TextView) dialog.findViewById(R.id.nutrition);
        nutrit.setText(nutrition.get(position));



        Button addButton = (Button) dialog.findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TabFragment3.recipe_indices.add(current_index);
                Toast.makeText(getActivity(), "Recipe added to menu!", Toast.LENGTH_SHORT).show();

                List<RowItem> new_rowItems;
                new_rowItems = new ArrayList<RowItem>();
                for (int i = 0; i < TabFragment3.recipe_indices.size(); i++) {
                    Integer index = TabFragment3.recipe_indices.get(i);
                    RowItem item = new RowItem(recipe_names.get(index),recipe_images.get(index), recipe_time.get(index),
                            recipe_stars.get(index), recipe_summaries.get(index),
                            recipe_dollars.get(index), ingredients.get(index), instructions.get(index), nutrition.get(index));
                    new_rowItems.add(item);
                }
                ListView listview3 = TabFragment3.listview3;
                ListAdapterSmall new_menu_adapter = new ListAdapterSmall(getActivity(), new_rowItems);
                listview3.setAdapter(new_menu_adapter);
                new_menu_adapter.notifyDataSetChanged();

            }
        });

        Button favButton = (Button) dialog.findViewById(R.id.fav_button);
        favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TabFragment2.fav_indices.add(current_index);
                Toast.makeText(getActivity(), "Recipe added to favorites!", Toast.LENGTH_SHORT).show();
//                Toast.makeText(getActivity(), Integer.toString(TabFragment2.fav_indices.size()), Toast.LENGTH_SHORT).show();

                List<RowItem> new_favItems;
                new_favItems = new ArrayList<RowItem>();
                for (int i = 0; i < TabFragment2.fav_indices.size(); i++) {
                    Integer index = TabFragment2.fav_indices.get(i);
                    RowItem item = new RowItem(TabFragment1.recipe_names.get(index), TabFragment1.recipe_images.get(index), TabFragment1.recipe_time.get(index),
                            TabFragment1.recipe_stars.get(index), TabFragment1.recipe_summaries.get(index), TabFragment1.recipe_dollars.get(index), TabFragment1.ingredients.get(index),
                            TabFragment1.instructions.get(index), TabFragment1.nutrition.get(index));
                    new_favItems.add(item);
                }
                ListView listview2 = TabFragment2.listview2;
                ListAdapterFav new_adapter = new ListAdapterFav(getActivity(), new_favItems);// adapter with new data
                listview2.setAdapter(new_adapter);
                new_adapter.notifyDataSetChanged();
            }
        });

        dialog.show();
    }



}
