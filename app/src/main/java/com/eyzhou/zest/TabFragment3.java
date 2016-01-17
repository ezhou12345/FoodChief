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
 * Created by eyzhou on 12/29/15.
 */
public class TabFragment3 extends android.support.v4.app.ListFragment implements AdapterView.OnItemClickListener {
    public static ListAdapterSmall adapter;
    private List<RowItem> menuItems;
    public static ArrayList<Integer> recipe_indices = new ArrayList<Integer>();

    public View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.tab_fragment_3, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        List<RowItem> menuItems;
        menuItems = new ArrayList<RowItem>();
        for (int i = 0; i < recipe_indices.size(); i++) {
            Integer index = recipe_indices.get(i);
            RowItem item = new RowItem(TabFragment1.recipe_names[index], TabFragment1.recipe_images[index], TabFragment1.recipe_time[index],
                    TabFragment1.recipe_stars[index], TabFragment1.recipe_summaries[index], TabFragment1.recipe_prep[index],
                    TabFragment1.recipe_cook[index], TabFragment1.recipe_dollars[index], TabFragment1.ingredients[index],
                    TabFragment1.instructions[index], TabFragment1.nutrition[index]);
            menuItems.add(item);
        }

        adapter = new ListAdapterSmall(getActivity(), menuItems);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);


    }

    public void refreshListView() {
        List<RowItem> menuItems;
        menuItems = new ArrayList<RowItem>();
        for (int i = 0; i < recipe_indices.size(); i++) {
            Integer index = recipe_indices.get(i);
            RowItem item = new RowItem(TabFragment1.recipe_names[index], TabFragment1.recipe_images[index], TabFragment1.recipe_time[index],
                    TabFragment1.recipe_stars[index], TabFragment1.recipe_summaries[index], TabFragment1.recipe_prep[index],
                    TabFragment1.recipe_cook[index], TabFragment1.recipe_dollars[index], TabFragment1.ingredients[index],
                    TabFragment1.instructions[index], TabFragment1.nutrition[index]);
            menuItems.add(item);
        }

        adapter = new ListAdapterSmall(getActivity(), menuItems);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // custom dialog
        int index = recipe_indices.get(position);
//        Toast.makeText(getActivity(), "hi", Toast.LENGTH_SHORT).show();
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_menu);
        dialog.setTitle(TabFragment1.recipe_names[index]);

        // set the custom dialog components - text, image and button
        TextView text = (TextView) dialog.findViewById(R.id.text);
        text.setText(TabFragment1.recipe_names[index]);
        ImageView image = (ImageView) dialog.findViewById(R.id.image);
        image.setImageResource(TabFragment1.recipe_images[index]);
        TextView total_time = (TextView) dialog.findViewById(R.id.total_time);
        total_time.setText("Total time: " + TabFragment1.recipe_time[index] + " minutes");
        ImageView rating = (ImageView) dialog.findViewById(R.id.rating);
        int num_stars = TabFragment1.recipe_stars[index];
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
        int num_dollars = TabFragment1.recipe_dollars[index];
        if (num_dollars == 1) {
            dollars.setImageResource(R.drawable.one_dollar);
        } else if (num_dollars == 2) {
            dollars.setImageResource(R.drawable.two_dollars);
        } else if (num_dollars == 3) {
            dollars.setImageResource(R.drawable.three_dollars);
        } else if (num_dollars == 4) {
            dollars.setImageResource(R.drawable.four_dollars);
        } else {
            // unrated?
        }

        TextView summary = (TextView) dialog.findViewById(R.id.ingredients_summary);
        summary.setText(TabFragment1.recipe_summaries[index]);

        TextView ingred = (TextView) dialog.findViewById(R.id.ingredients);
        ingred.setText(TabFragment1.ingredients[index]);

        TextView instruct = (TextView) dialog.findViewById(R.id.instructions);
        instruct.setText(TabFragment1.instructions[index]);

        TextView nutrit = (TextView) dialog.findViewById(R.id.nutrition);
        nutrit.setText(TabFragment1.nutrition[index]);

//        Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
//        // if button is clicked, close the custom dialog
//        dialogButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });

        dialog.show();
    }




}
