package com.example.buran.archery_competition.Participants;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.buran.archery_competition.R;
import com.example.buran.archery_competition.db.ParticipantContract.ParticipantEntry;
import com.example.buran.archery_competition.db.ParticipantsDbHelper;

public class ParticipantsActivity extends AppCompatActivity {

    private FloatingActionButton fab;

    private ParticipantsDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participants);
        initializeFab();

        dbHelper = new ParticipantsDbHelper(this);
    }

    private void initializeFab(){
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ParticipantsActivity.this,
                                           ParticipantEditorActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    private void displayDatabaseInfo() {

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                ParticipantEntry._ID,
                ParticipantEntry.COLUMN_NAME,
                ParticipantEntry.COLUMN_LAST_NAME,
                ParticipantEntry.COLUMN_DOB,
                ParticipantEntry.COLUMN_GENDER,
                ParticipantEntry.COLUMN_COUNTRY,
                ParticipantEntry.COLUMN_CITY,
                ParticipantEntry.COLUMN_CLUB,
                ParticipantEntry.COLUMN_CLASS};


        Cursor cursor = db.query(
                ParticipantEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null,
                null);

        TextView displayTextView = (TextView) findViewById(R.id.text_view);

        try {
            displayTextView.setText("This table contains " + cursor.getCount() + " guests.\n\n");
            displayTextView.append(ParticipantEntry._ID + " - " +
                    ParticipantEntry.COLUMN_NAME + " - " +
                    ParticipantEntry.COLUMN_LAST_NAME + " - " +
                    ParticipantEntry.COLUMN_DOB + " - " +
                    ParticipantEntry.COLUMN_GENDER + " - " +
                    ParticipantEntry.COLUMN_COUNTRY + " - " +
                    ParticipantEntry.COLUMN_CITY + " - " +
                    ParticipantEntry.COLUMN_CLUB + " - " +
                    ParticipantEntry.COLUMN_CLASS + "\n");

            //to know index pf every column
            int idColumnIndex = cursor.getColumnIndex(ParticipantEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(ParticipantEntry.COLUMN_NAME);
            int lastNameColumnIndex = cursor.getColumnIndex(ParticipantEntry.COLUMN_LAST_NAME);
            int dobColumnIndex = cursor.getColumnIndex(ParticipantEntry.COLUMN_DOB);
            int genderColumnIndex = cursor.getColumnIndex(ParticipantEntry.COLUMN_GENDER);
            int countryColumnIndex = cursor.getColumnIndex(ParticipantEntry.COLUMN_COUNTRY);
            int cityColumnIndex = cursor.getColumnIndex(ParticipantEntry.COLUMN_CITY);
            int clubColumnIndex = cursor.getColumnIndex(ParticipantEntry.COLUMN_CLUB);
            int classColumnIndex = cursor.getColumnIndex(ParticipantEntry.COLUMN_CLASS);

            //go throw
            while (cursor.moveToNext()) {
                int currentId = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentLastName = cursor.getString(lastNameColumnIndex);
                String currentDOB = cursor.getString(dobColumnIndex);
                int currentGender = cursor.getInt(genderColumnIndex);
                String currentCountry = cursor.getString(countryColumnIndex);
                String currentCity = cursor.getString(cityColumnIndex);
                String currentClub = cursor.getString(clubColumnIndex);
                String currentClass = cursor.getString(classColumnIndex);


                displayTextView.append(("\n" + currentId + " - " +
                        currentName + " - " +
                        currentLastName + " - " +
                        currentDOB + " - " +
                        currentGender + " - " +
                        currentCountry + " - " +
                        currentCity + " - " +
                        currentClub + " - " +
                        currentClass));
            }
        } finally {
            cursor.close();
        }
    }
}
