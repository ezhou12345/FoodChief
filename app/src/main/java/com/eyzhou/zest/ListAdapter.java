package com.eyzhou.zest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.recipe_list, null);
        }

        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.Itemimage);
        TextView txtTitle = (TextView) convertView.findViewById(R.id.Itemname);
        ImageView rating = (ImageView) convertView.findViewById(R.id.Itemrating);
        TextView totalTime = (TextView) convertView.findViewById(R.id.Itemtime);

        RowItem row_pos = rowItem.get(position);
        // setting the image resource and title
        imgIcon.setImageResource(row_pos.getIcon());
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

        ImageView dollars = (ImageView) convertView.findViewById(R.id.Itemdollars);
        int num_dollars = row_pos.getDollars();
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

        totalTime.setText(row_pos.getTime());

        return convertView;

    }
}
