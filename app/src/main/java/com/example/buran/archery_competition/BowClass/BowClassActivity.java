package com.example.buran.archery_competition.BowClass;


import android.content.ContentValues;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
;

import com.example.buran.archery_competition.R;
import com.example.buran.archery_competition.db.CompetitionDbHelper;

import java.util.ArrayList;

import java.util.List;

import com.example.buran.archery_competition.db.BowClassContract.BowClassEntry;

public class BowClassActivity extends AppCompatActivity {

    private EditText bowClassName;

    private Button addButton;

    private CompetitionDbHelper mDbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bow_class);

        bowClassName = (EditText) findViewById(R.id.bow_class_edit_text);

        addButton = (Button) findViewById(R.id.add_btn);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nameClass = bowClassName.getText().toString();

                if (nameClass.trim().length() > 0) {
                    mDbHelper = new CompetitionDbHelper(getApplicationContext());
                    saveBowClassName(nameClass);
                    bowClassName.setText("");

                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(bowClassName.getWindowToken(), 0);
                } else {

                    Toast.makeText(getApplicationContext(), "Please enter name", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    public void saveBowClassName(String bowClassName) {

        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(BowClassEntry.COLUMN_NAME, bowClassName);

        db.insert(BowClassEntry.TABLE_NAME, null, values);
        db.close();
    }


}








