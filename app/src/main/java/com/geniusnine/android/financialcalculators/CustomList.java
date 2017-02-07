package com.geniusnine.android.financialcalculators;

/**
 * Created by Dev on 06-02-2017.
 */

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomList extends ArrayAdapter<String>{

    private final Activity context;
    private final String[] listview_names;
    private final Integer[] listview_images;
    public CustomList(Activity context,
                      String[] web, Integer[] imageId) {
        super(context, R.layout.recycler_list_item, web);
        this.context = context;
        this.listview_names = web;
        this.listview_images = imageId;

    }
    @Override
    public int getCount() {
        return listview_images.length;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.recycler_list_item, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.textViewCategory);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.imageViewCategory);
        txtTitle.setText(listview_names[position]);

       imageView.setImageResource(listview_images[position]);
        return rowView;
    }
}