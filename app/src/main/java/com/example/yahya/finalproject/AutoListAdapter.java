package com.example.yahya.finalproject;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jason on 04/01/2018.
 */

/* Reference for ListAdapter Class:
    https://www.youtube.com/watch?v=8K-6gdTlGEA
 */
public class AutoListAdapter extends ArrayAdapter<Automobile> {

    private LayoutInflater mInflater;
    private ArrayList<Automobile> Automobiles;
    private int mViewResourceId;

    // Constructor
    public AutoListAdapter(Context context, int textViewResourceId, ArrayList<Automobile> Automobiles) {
        super(context, textViewResourceId, Automobiles);
        this.Automobiles = Automobiles;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(mViewResourceId, null);

        Automobile auto = Automobiles.get(position);

        // When working with values, convert to string and check if length > 0
        if (auto != null) {
            TextView price = convertView.findViewById(R.id.textPrice);
            TextView litres = convertView.findViewById(R.id.textLitres);
            TextView kilometers = convertView.findViewById(R.id.textKilometers);
            if (price != null) {
                price.setText(auto.getPrice());
            }
            if (litres != null) {
                litres.setText((auto.getLitres()));
            }
            if (kilometers != null) {
                kilometers.setText((auto.getKilometers()));
            }
        }
        return convertView;
    }
}