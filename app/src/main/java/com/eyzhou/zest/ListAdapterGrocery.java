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
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.List;

/**
 * Created by eyzhou on 1/6/16.
 */
public class ListAdapterGrocery extends BaseAdapter{
    Context context;
    List<RowItemGrocery> rowItem;
//    private static LayoutInflater inflater=null;

    ListAdapterGrocery(Context context, List<RowItemGrocery> rowItem) {
        this.context = context;
        this.rowItem = rowItem;
    }

    static class ViewHolder {
        public TextView name;
        public TextView amount;
        public TextView price;
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
            convertView = mInflater.inflate(R.layout.grocery_list, null);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.name = (TextView) convertView.findViewById(R.id.GroceryName);
            viewHolder.amount = (TextView) convertView.findViewById(R.id.GroceryAmount);
            viewHolder.price = (TextView) convertView.findViewById(R.id.GroceryPrice);
            convertView.setTag(viewHolder);
        }



//        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.Itemimage);
//        TextView txtTitle = (TextView) convertView.findViewById(R.id.Itemname);
//        ImageView rating = (ImageView) convertView.findViewById(R.id.Itemrating);
//        TextView totalTime = (TextView) convertView.findViewById(R.id.Itemtime);
//        ImageView dollars = (ImageView) convertView.findViewById(R.id.Itemdollars);
        ViewHolder holder = (ViewHolder) convertView.getTag();
        RowItemGrocery row_pos = rowItem.get(position);

        // setting the image resource and title
//        new DownloadImageTask(holder.imgIcon).execute(row_pos.getIcon());

        holder.name.setText(row_pos.getName());
        holder.amount.setText(row_pos.getAmount());
        holder.price.setText(row_pos.getPrice());

        return convertView;

    }
}
