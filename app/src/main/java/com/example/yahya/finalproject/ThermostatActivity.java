package com.example.yahya.finalproject;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ThermostatActivity extends Activity {

    String [] sourceData = { "  ","Activity Tracking", "  ", "Food Nutrition information tracker",
            "House Thermostat", "Automobile"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thermostat);

        //Creating an array adapter for the listView
        ListView lv = (ListView)findViewById(R.id.thermostat_list);
        lv.setAdapter(new ArrayAdapter<String>(this, R.layout.cell_layout, sourceData));
    }
}
