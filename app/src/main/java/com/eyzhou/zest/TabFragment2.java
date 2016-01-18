package com.eyzhou.zest;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eyzhou on 12/29/15.
 */
public class TabFragment2 extends android.support.v4.app.ListFragment implements AdapterView.OnItemClickListener{
    ListAdapterFav adapter;
    public static ListView listview2;

//    private List<RowItem> favItems;
    public static ArrayList<Integer> fav_indices = new ArrayList<Integer>();
    int current_ind = 0;

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
            RowItem item = new RowItem(TabFragment1.recipe_names.get(index), TabFragment1.recipe_images.get(index), TabFragment1.recipe_time.get(index),
                    TabFragment1.recipe_stars.get(index), TabFragment1.recipe_summaries.get(index), TabFragment1.recipe_dollars.get(index), TabFragment1.ingredients.get(index),
                    TabFragment1.instructions.get(index), TabFragment1.nutrition.get(index));
            favItems.add(item);
        }
        listview2 = getListView();
        adapter = new ListAdapterFav(getActivity(), favItems);
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
        // custom dialog
        int index = fav_indices.get(position);
        current_ind = index;
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_menu);
        dialog.setTitle(TabFragment1.recipe_names.get(index));

        // set the custom dialog components - text, image and button
        TextView text = (TextView) dialog.findViewById(R.id.text);
        text.setText(TabFragment1.recipe_names.get(index));
        ImageView image = (ImageView) dialog.findViewById(R.id.image);
        new DownloadImageTask(image).execute(TabFragment1.recipe_images.get(index));

        TextView total_time = (TextView) dialog.findViewById(R.id.total_time);
        total_time.setText("Total time: " + TabFragment1.recipe_time.get(index) + " minutes");
        ImageView rating = (ImageView) dialog.findViewById(R.id.rating);
        int num_stars = TabFragment1.recipe_stars.get(index);
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
        int num_dollars = TabFragment1.recipe_dollars.get(index);
        if (num_dollars == 1) {
            dollars.setImageResource(R.drawable.one_dollar);
        } else {
            dollars.setImageResource(R.drawable.two_dollars);
        }

        TextView summary = (TextView) dialog.findViewById(R.id.ingredients_summary);
        summary.setText(TabFragment1.recipe_summaries.get(index));

        TextView ingred = (TextView) dialog.findViewById(R.id.ingredients);
        ingred.setText(TabFragment1.ingredients.get(index));

        TextView instruct = (TextView) dialog.findViewById(R.id.instructions);
        instruct.setText(TabFragment1.instructions.get(index));

        TextView nutrit = (TextView) dialog.findViewById(R.id.nutrition);
        nutrit.setText(TabFragment1.nutrition.get(index));

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

    public void deleteRecipe(View view) {

    }
}
