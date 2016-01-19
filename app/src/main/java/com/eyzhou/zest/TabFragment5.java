package com.eyzhou.zest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eyzhou on 1/6/16.
 */
public class TabFragment5 extends android.support.v4.app.ListFragment implements AdapterView.OnItemClickListener{
    public View rootView;
    public static ListAdapterGrocery adapter;
    public static ListView listview5;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.tab_fragment_5, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        List<RowItemGrocery> groceryItems;
        groceryItems = new ArrayList<RowItemGrocery>();
        for (int i = 0; i < TabFragment1.recipe_names.size(); i++) {
//            Integer index = TabFragment3.recipe_indices.get(i);
            RowItemGrocery item = new RowItemGrocery("name", "amount", 0);
            groceryItems.add(item);
        }
        listview5 = getListView();
        adapter = new ListAdapterGrocery(getActivity(), groceryItems);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
