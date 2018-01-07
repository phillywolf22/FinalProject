package com.example.yahya.finalproject;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * created by Philip Tharakan
 */


// this activity is to have a screen for the user to delete rows from listview.

    //Reference
        //https://www.youtube.com/watch?annotation_id=annotation_3065812653&feature=iv&src_vid=SK98ayjhk1E&v=aQAIMY-HzL8


public class EditDataActivity extends Activity {

    private static final String TAG = "EditDataActivity";

    private Button btnSave, btnDelete, btnList, btnEntryScreen;
    private EditText editable_item;

    DatabaseHelperPhil mDatabaseHelper;

    private String selectedDate;
    private int selectedID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_data_layout);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
      //  btnList = (Button) findViewById(R.id.btnlistViewBack);
        btnEntryScreen = (Button) findViewById(R.id.btnEntryScreen);
        editable_item = (EditText) findViewById(R.id.editable);

        mDatabaseHelper = new DatabaseHelperPhil(this);

        //
        Intent receivedIntent = getIntent();

        //
        selectedID = receivedIntent.getIntExtra("id", -1);

        //
        selectedDate = receivedIntent.getStringExtra("date");



        btnSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String item = editable_item.getText().toString();
                if(!item.equals("")){
                    mDatabaseHelper.updateDate(item, selectedID,selectedDate);

                }else{
                    toastMessage("You must enter a date!");
                }
            }
        });

        //Here the USER can input the id of the column that they want to delete and it will be
        //removed from the listview.

            btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // mDatabaseHelper.deleteDate(selectedID,selectedDate);

                        String value = editable_item.getText().toString();
                selectedID=Integer.parseInt(value);
                mDatabaseHelper.delete_byID(selectedID);
                editable_item.setText("");
                toastMessage("Item was removed from database");
            }
        });



        btnEntryScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editScreenIntent = new Intent(EditDataActivity.this, ProjectToolbar.class);
                startActivity(editScreenIntent);
            }
        });

    }

    public void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

}
