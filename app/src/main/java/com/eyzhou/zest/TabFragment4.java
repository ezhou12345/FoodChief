package com.eyzhou.zest;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eyzhou on 1/6/16.
 */
public class TabFragment4 extends android.support.v4.app.Fragment implements AdapterView.OnItemClickListener{
    public static ListAdapterFav adapter;

    private ListView lv_mon = null; private ListView lv_tues = null; private ListView lv_wed = null;
    private ListView lv_thurs = null; private ListView lv_fri = null;
    private ListView lv_sat = null; private ListView lv_sun = null;
    private ListView lv_menu = null;

    private List<RowItem> mondayItems; private List<RowItem> tuesdayItems; private List<RowItem> wednesdayItems;
    private List<RowItem> thursdayItems; private List<RowItem> fridayItems;
    private List<RowItem> saturdayItems; private List<RowItem> sundayItems;
    private List<RowItem> menuItems;

    public static ArrayList<Integer> mon_indices = new ArrayList<Integer>();
    public static ArrayList<Integer> tues_indices = new ArrayList<Integer>();
    public static ArrayList<Integer> wed_indices = new ArrayList<Integer>();
    public static ArrayList<Integer> thurs_indices = new ArrayList<Integer>();
    public static ArrayList<Integer> fri_indices = new ArrayList<Integer>();
    public static ArrayList<Integer> sat_indices = new ArrayList<Integer>();
    public static ArrayList<Integer> sun_indices = new ArrayList<Integer>();

    private TextView mon_button = null;

    public View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.tab_fragment_4, container, false);

        mondayItems = new ArrayList<RowItem>();
        for (int i = 0; i < mon_indices.size(); i++) {
            Integer index = mon_indices.get(i);
            RowItem item = new RowItem(TabFragment1.recipe_names.get(index), TabFragment1.recipe_images.get(index), TabFragment1.recipe_time.get(index),
                    TabFragment1.recipe_stars.get(index), TabFragment1.recipe_summaries.get(index),  TabFragment1.recipe_dollars.get(index), TabFragment1.ingredients.get(index),
                    TabFragment1.instructions.get(index), TabFragment1.nutrition.get(index));
            mondayItems.add(item);
        }

        lv_mon = (ListView)rootView.findViewById(R.id.monday_list);
        adapter = new ListAdapterFav(getActivity(), mondayItems);
        lv_mon.setAdapter(adapter);
        lv_mon.setOnItemClickListener(this);

        mon_button = (TextView) rootView.findViewById (R.id.monday);
        mon_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMonday(v);
            }
        });

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



    }

//    public void refreshListView() {
//        List<RowItem> menuItems;
//        menuItems = new ArrayList<RowItem>();
//        for (int i = 0; i < recipe_indices.size(); i++) {
//            Integer index = recipe_indices.get(i);
//            RowItem item = new RowItem(TabFragment1.recipe_names[index], TabFragment1.recipe_images[index], TabFragment1.recipe_time[index],
//                    TabFragment1.recipe_stars[index], TabFragment1.recipe_summaries[index], TabFragment1.recipe_prep[index],
//                    TabFragment1.recipe_cook[index], TabFragment1.recipe_dollars[index], TabFragment1.ingredients[index],
//                    TabFragment1.instructions[index], TabFragment1.nutrition[index]);
//            menuItems.add(item);
//        }
//
//        adapter = new ListAdapterFav(getActivity(), menuItems);
//        setListAdapter(adapter);
//        getListView().setOnItemClickListener(this);
//    }

    public void addMonday(View view) {
//        int index = recipe_indices.get(position);
//        Toast.makeText(getActivity(), "hi", Toast.LENGTH_SHORT).show();




        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_select_from_menu);
        dialog.setTitle("Add a recipe");

        menuItems = new ArrayList<RowItem>();
        for (int i = 0; i < TabFragment3.recipe_indices.size(); i++) {
            Integer index = TabFragment3.recipe_indices.get(i);
            RowItem item = new RowItem(TabFragment1.recipe_names.get(index), TabFragment1.recipe_images.get(index), TabFragment1.recipe_time.get(index),
                    TabFragment1.recipe_stars.get(index), TabFragment1.recipe_summaries.get(index),  TabFragment1.recipe_dollars.get(index), TabFragment1.ingredients.get(index),
                    TabFragment1.instructions.get(index), TabFragment1.nutrition.get(index));
            menuItems.add(item);
        }


        ListView lv_menu = (ListView ) dialog.findViewById(R.id.menu_list);
        ListAdapterFav menu_adapter = new ListAdapterFav(getActivity(), menuItems);
        lv_menu.setAdapter(menu_adapter);
        lv_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), Integer.toString(position), Toast.LENGTH_SHORT).show();
                mon_indices.add(TabFragment3.recipe_indices.get(position));
                dialog.dismiss();
            }
        });






//        TextView text = (TextView) dialog.findViewById(R.id.text);
//        text.setText(TabFragment1.recipe_names[index]);
//        ImageView image = (ImageView) dialog.findViewById(R.id.image);
//        image.setImageResource(TabFragment1.recipe_images[index]);
//        TextView total_time = (TextView) dialog.findViewById(R.id.total_time);
//        total_time.setText("Total time: " + TabFragment1.recipe_time[index] + " minutes");
//        ImageView rating = (ImageView) dialog.findViewById(R.id.rating);
//        int num_stars = TabFragment1.recipe_stars[index];
//        if (num_stars == 1) {
//            rating.setImageResource(R.drawable.one_star);
//        } else if (num_stars == 2) {
//            rating.setImageResource(R.drawable.two_stars);
//        } else if (num_stars == 3) {
//            rating.setImageResource(R.drawable.three_stars);
//        } else if (num_stars == 4) {
//            rating.setImageResource(R.drawable.four_stars);
//        } else if (num_stars == 5) {
//            rating.setImageResource(R.drawable.five_stars);
//        } else {
//            // unrated?
//        }
//
//        ImageView dollars = (ImageView) dialog.findViewById(R.id.dollars);
//        int num_dollars = TabFragment1.recipe_dollars[index];
//        if (num_dollars == 1) {
//            dollars.setImageResource(R.drawable.one_dollar);
//        } else if (num_dollars == 2) {
//            dollars.setImageResource(R.drawable.two_dollars);
//        } else if (num_dollars == 3) {
//            dollars.setImageResource(R.drawable.three_dollars);
//        } else if (num_dollars == 4) {
//            dollars.setImageResource(R.drawable.four_dollars);
//        } else {
//            // unrated?
//        }
//
//        TextView summary = (TextView) dialog.findViewById(R.id.ingredients_summary);
//        summary.setText(TabFragment1.recipe_summaries[index]);
//
//        TextView ingred = (TextView) dialog.findViewById(R.id.ingredients);
//        ingred.setText(TabFragment1.ingredients[index]);
//
//        TextView instruct = (TextView) dialog.findViewById(R.id.instructions);
//        instruct.setText(TabFragment1.instructions[index]);
//
//        TextView nutrit = (TextView) dialog.findViewById(R.id.nutrition);
//        nutrit.setText(TabFragment1.nutrition[index]);


        dialog.show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        // custom dialog
//        int index = recipe_indices.get(position);
////        Toast.makeText(getActivity(), "hi", Toast.LENGTH_SHORT).show();
//        final Dialog dialog = new Dialog(getActivity());
//        dialog.setContentView(R.layout.dialog_menu);
//        dialog.setTitle(TabFragment1.recipe_names[index]);
//
//        // set the custom dialog components - text, image and button
//        TextView text = (TextView) dialog.findViewById(R.id.text);
//        text.setText(TabFragment1.recipe_names[index]);
//        ImageView image = (ImageView) dialog.findViewById(R.id.image);
//        image.setImageResource(TabFragment1.recipe_images[index]);
//        TextView total_time = (TextView) dialog.findViewById(R.id.total_time);
//        total_time.setText("Total time: " + TabFragment1.recipe_time[index] + " minutes");
//        ImageView rating = (ImageView) dialog.findViewById(R.id.rating);
//        int num_stars = TabFragment1.recipe_stars[index];
//        if (num_stars == 1) {
//            rating.setImageResource(R.drawable.one_star);
//        } else if (num_stars == 2) {
//            rating.setImageResource(R.drawable.two_stars);
//        } else if (num_stars == 3) {
//            rating.setImageResource(R.drawable.three_stars);
//        } else if (num_stars == 4) {
//            rating.setImageResource(R.drawable.four_stars);
//        } else if (num_stars == 5) {
//            rating.setImageResource(R.drawable.five_stars);
//        } else {
//            // unrated?
//        }
//
//        ImageView dollars = (ImageView) dialog.findViewById(R.id.dollars);
//        int num_dollars = TabFragment1.recipe_dollars[index];
//        if (num_dollars == 1) {
//            dollars.setImageResource(R.drawable.one_dollar);
//        } else if (num_dollars == 2) {
//            dollars.setImageResource(R.drawable.two_dollars);
//        } else if (num_dollars == 3) {
//            dollars.setImageResource(R.drawable.three_dollars);
//        } else if (num_dollars == 4) {
//            dollars.setImageResource(R.drawable.four_dollars);
//        } else {
//            // unrated?
//        }
//
//        TextView summary = (TextView) dialog.findViewById(R.id.ingredients_summary);
//        summary.setText(TabFragment1.recipe_summaries[index]);
//
//        TextView ingred = (TextView) dialog.findViewById(R.id.ingredients);
//        ingred.setText(TabFragment1.ingredients[index]);
//
//        TextView instruct = (TextView) dialog.findViewById(R.id.instructions);
//        instruct.setText(TabFragment1.instructions[index]);
//
//        TextView nutrit = (TextView) dialog.findViewById(R.id.nutrition);
//        nutrit.setText(TabFragment1.nutrition[index]);
//
////        Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
////        // if button is clicked, close the custom dialog
////        dialogButton.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                dialog.dismiss();
////            }
////        });
//
//        dialog.show();
    }
}
