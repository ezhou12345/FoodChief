package com.eyzhou.zest;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.List;

/**
 * Created by eyzhou on 1/15/16.
 */
public class ListAdapterSmall extends BaseAdapter{
    Context context;
    List<RowItem> rowItem;
//    private static LayoutInflater inflater=null;

    ListAdapterSmall(Context context, List<RowItem> rowItem) {
        this.context = context;
        this.rowItem = rowItem;
    }

//    /*private view holder class*/
//    private class ViewHolder {
//        ImageView imageView;
//        TextView txtTitle;
////        TextView txtDesc;
//    }

    @Override
    public int getCount() {

        return rowItem.size();
    }

    @Override
    public Object getItem(int position) {

        return rowItem.get(position);
    }

    @Override
    public long getItemId(int position) {

        return rowItem.indexOf(getItem(position));
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
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.recipe_list_small, null);
        }

        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.Itemimage_small);
        TextView txtTitle = (TextView) convertView.findViewById(R.id.Itemname_small);
        ImageView rating = (ImageView) convertView.findViewById(R.id.Itemrating_small);
        TextView totalTime = (TextView) convertView.findViewById(R.id.Itemtime_small);

        RowItem row_pos = rowItem.get(position);
        // setting the image resource and title
        new DownloadImageTask(imgIcon).execute(row_pos.getIcon());

        txtTitle.setText(row_pos.getTitle());

        int num_stars = row_pos.getStars();
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

        ImageView dollars = (ImageView) convertView.findViewById(R.id.Itemdollars_small);
        int num_dollars = row_pos.getDollars();
        if (num_dollars == 1) {
            dollars.setImageResource(R.drawable.one_dollar);
        } else {
            dollars.setImageResource(R.drawable.two_dollars);
        }

        totalTime.setText(row_pos.getTime());

//        TextView ind = (TextView) convertView.findViewById(R.id.recipe_index_small);
//        ind.setText(Integer.toString(position));

//        Button deleteButton = (Button) convertView.findViewById(R.id.MenuDeleteButton);
//        deleteButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Toast.makeText(context, "-", Toast.LENGTH_SHORT).show();
//                View parentRow = (View) v.getParent();
//                ListView listView = (ListView) parentRow.getParent();
//                final int position = listView.getPositionForView(parentRow);
//                TabFragment3.recipe_indices.remove(position);
//                TabFragment3.adapter.notifyDataSetChanged();
//
//
//            }
//        });

        return convertView;

    }
}
