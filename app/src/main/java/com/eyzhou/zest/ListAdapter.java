package com.eyzhou.zest;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by eyzhou on 1/6/16.
 */
public class ListAdapter extends BaseAdapter{
    Context context;
    List<RowItem> rowItem;
//    private static LayoutInflater inflater=null;

    ListAdapter(Context context, List<RowItem> rowItem) {
        this.context = context;
        this.rowItem = rowItem;
    }

    static class ViewHolder {
        public TextView txtTitle;
        public ImageView imgIcon;
        public TextView totalTime;
        public ImageView rating;
        public ImageView dollars;
    }

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
            convertView = mInflater.inflate(R.layout.recipe_list, null);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.txtTitle = (TextView) convertView.findViewById(R.id.Itemname);
            viewHolder.imgIcon = (ImageView) convertView.findViewById(R.id.Itemimage);
            viewHolder.totalTime = (TextView) convertView.findViewById(R.id.Itemtime);
            viewHolder.rating = (ImageView) convertView.findViewById(R.id.Itemrating);
            viewHolder.dollars = (ImageView) convertView.findViewById(R.id.Itemdollars);
            convertView.setTag(viewHolder);
        }



//        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.Itemimage);
//        TextView txtTitle = (TextView) convertView.findViewById(R.id.Itemname);
//        ImageView rating = (ImageView) convertView.findViewById(R.id.Itemrating);
//        TextView totalTime = (TextView) convertView.findViewById(R.id.Itemtime);
//        ImageView dollars = (ImageView) convertView.findViewById(R.id.Itemdollars);
        ViewHolder holder = (ViewHolder) convertView.getTag();
        RowItem row_pos = rowItem.get(position);
        // setting the image resource and title
//        imgIcon.setImageResource(row_pos.getIcon());
        new DownloadImageTask(holder.imgIcon).execute(row_pos.getIcon());

        holder.txtTitle.setText(row_pos.getTitle());

        int num_stars = row_pos.getStars();
        if (num_stars == 1) {
            holder.rating.setImageResource(R.drawable.one_star);
        } else if (num_stars == 2) {
            holder.rating.setImageResource(R.drawable.two_stars);
        } else if (num_stars == 3) {
            holder.rating.setImageResource(R.drawable.three_stars);
        } else if (num_stars == 4) {
            holder.rating.setImageResource(R.drawable.four_stars);
        } else if (num_stars == 5) {
            holder.rating.setImageResource(R.drawable.five_stars);
        } else {
            // unrated?
        }


        int num_dollars = row_pos.getDollars();
        if (num_dollars == 1) {
            holder.dollars.setImageResource(R.drawable.one_dollar);
        } else {
            holder.dollars.setImageResource(R.drawable.two_dollars);
        }

        holder.totalTime.setText(row_pos.getTime());

        return convertView;

    }
}
