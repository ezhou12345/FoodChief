package com.eyzhou.zest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eyzhou on 12/29/15.
 */
public class TabFragment2 extends android.support.v4.app.ListFragment implements AdapterView.OnItemClickListener{
    ListAdapterFav adapter;
//    private List<RowItem> favItems;
    public static ArrayList<Integer> fav_indices = new ArrayList<Integer>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_fragment_2, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        List<RowItem> favItems;
        favItems = new ArrayList<RowItem>();
        for (int i = 0; i < fav_indices.size(); i++) {
            Integer index = fav_indices.get(i);
            RowItem item = new RowItem(TabFragment1.recipe_names[index], TabFragment1.recipe_images[index], TabFragment1.recipe_time[index],
                    TabFragment1.recipe_stars[index], TabFragment1.recipe_summaries[index], TabFragment1.recipe_prep[index],
                    TabFragment1.recipe_cook[index], TabFragment1.recipe_dollars[index], TabFragment1.ingredients[index],
                    TabFragment1.instructions[index], TabFragment1.nutrition[index]);
            favItems.add(item);
        }

        adapter = new ListAdapterFav(getActivity(), favItems);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // custom dialog
//        final Dialog dialog = new Dialog(getActivity());
//        dialog.setContentView(R.layout.custom_dialog_box);
//        dialog.setTitle(recipe_names[position]);
//
//        // set the custom dialog components - text, image and button
//        TextView text = (TextView) dialog.findViewById(R.id.text);
//        text.setText(recipe_names[position]);
//        ImageView image = (ImageView) dialog.findViewById(R.id.image);
//        image.setImageResource(recipe_images[position]);
//        TextView total_time = (TextView) dialog.findViewById(R.id.total_time);
//        total_time.setText("Total time: " + recipe_time[position] + " minutes");
//        ImageView rating = (ImageView) dialog.findViewById(R.id.rating);
//        int num_stars = recipe_stars[position];
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
//        int num_dollars = recipe_dollars[position];
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
//        summary.setText(recipe_summaries[position]);
//
//        TextView ingred = (TextView) dialog.findViewById(R.id.ingredients);
//        ingred.setText(ingredients[position]);
//
//        TextView instruct = (TextView) dialog.findViewById(R.id.instructions);
//        instruct.setText(instructions[position]);
//
//        TextView nutrit = (TextView) dialog.findViewById(R.id.nutrition);
//        nutrit.setText(nutrition[position]);
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
