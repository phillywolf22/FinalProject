package com.example.yahya.finalproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AutomobileActivity extends Activity {

    AutoDatabaseHelper dbObject;

    EditText    etPrice, etLitres, etKilometers , etID;
    TextView    tvPrice, tvLitres, tvKilometers, tvEntry;
    Button      btnAddGasData, btnDeleteGasData, btnViewData, btnUpdateData, btnViewList;

    public static final String LOG_TAG = "AutomobileActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_automobile);
        dbObject = new AutoDatabaseHelper(this);
        Log.i(LOG_TAG, "in onCreate() DB created ");

        etPrice = findViewById(R.id.price_field);
        etLitres = findViewById(R.id.litres_field);
        etKilometers = findViewById(R.id.km_field);
        etID = findViewById(R.id.id_field);

        btnAddGasData = findViewById(R.id.add_button);
        btnDeleteGasData = findViewById(R.id.delete_button);
        btnViewData = findViewById(R.id.view_button);
        btnUpdateData = findViewById(R.id.update_button);
        btnViewList = findViewById(R.id.view_list_button);

        // French localization
        tvPrice = findViewById(R.id.price_label);
        tvPrice.setText(R.string.price_label);
        tvLitres = findViewById(R.id.litres_label);
        tvLitres.setText(R.string.litres_label);
        tvKilometers = findViewById(R.id.kilometers_label);
        tvKilometers.setText(R.string.kilometers_label);
        tvEntry = findViewById(R.id.entry_label);
        tvEntry.setText(R.string.entry_label);
        btnAddGasData.setText(R.string.add_button);
        btnDeleteGasData.setText(R.string.delete_button);
        btnViewData.setText(R.string.view_button);
        btnUpdateData.setText(R.string.update_button);
        btnViewList.setText(R.string.view_list_button);
        // End of localization

        // Launch new intent - AutoViewListContents
        btnViewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AutomobileActivity.this, AutoViewListContents.class);
                startActivity(intent);
            }
        });

        /* Reference for methods:
        http://www.codebind.com/android-tutorials-and-examples/android-sqlite-tutorial-example/
         */
        addGasData();
        deleteGasData();
        viewData();
        updateGasData();
    }

    public void addGasData() {

        // Insert new row into the table
        // Check if the row is inserted and execute toast message
        Log.i(LOG_TAG, "In addGasData()");
        btnAddGasData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = dbObject.insertGasData(
                                etPrice.getText().toString(),
                                etLitres.getText().toString(),
                                etKilometers.getText().toString()
                        );
                        if(isInserted)
                            Toast.makeText(AutomobileActivity.this,
                                    "Data was added successfully.",
                                    Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(AutomobileActivity.this,
                                    "Data could not be added.",
                                    Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void deleteGasData() {
        // Delete specified row into the table
        // Check if the row is deleted and execute toast message
        Log.i(LOG_TAG, "in deleteGasData()");
        btnDeleteGasData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer rowToDelete = dbObject.deleteGasData(etID.getText().toString());
                        if(rowToDelete > 0)
                            Toast.makeText(AutomobileActivity.this,
                                    "Data deleted successfully.",
                                    Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(AutomobileActivity.this,
                                    "There is no data to delete.",
                                    Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void updateGasData() {
        // Insert new row into the table
        // Check if the row is inserted and execute toast message
        Log.i(LOG_TAG, "In updateGasData()");
        btnUpdateData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = dbObject.updateGasData(
                                etID.getText().toString(),
                                etPrice.getText().toString(),
                                etLitres.getText().toString(),
                                etKilometers.getText().toString());
                        if(isUpdate)
                            Toast.makeText(AutomobileActivity.this,
                                    "Data updated successfully.", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(AutomobileActivity.this,
                                    "Data could not be updated.",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void viewData() {
        Log.i(LOG_TAG, "In viewData()");
        btnViewData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor c = dbObject.getGasData();
                        if(c.getCount() == 0) {
                            showMessage("Error","Nothing found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (c.moveToNext()) {
                            buffer.append("Id: " + c.getString(0)+"\n");
                            buffer.append("Price: " + c.getString(1)+"\n");
                            buffer.append("Litres: " + c.getString(2)+"\n");
                            buffer.append("Kilometers: "+ c.getString(3)+"\n");
                            buffer.append("Date: " + c.getString(4)+"\n\n");
                        }
                        // Show all data in alert dialog box
                        showMessage("Gas Data",buffer.toString());
                    }
                }
        );
    }

    // Launch alert dialog box with specified param
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

} /* End of Class AutomobileActivity */