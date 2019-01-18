package com.example.buran.archery_competition.Participants;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.buran.archery_competition.R;

import java.util.Calendar;

import com.example.buran.archery_competition.db.ParticipantContract.ParticipantEntry;
import com.example.buran.archery_competition.db.ParticipantsDbHelper;

public class ParticipantEditorActivity extends AppCompatActivity {

    private static final String TAG = ParticipantEditorActivity.class.getSimpleName();

    private EditText pName, pLastName, pDOB, pCountry,
            pCity, pClub, pBowClass;

    private Spinner pGenderSpinner;

    private Calendar calendar;
    private DatePickerDialog datePickerDialog;

    //0 gor female, 1 for male, 2 for unknown
    private int pGender = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participant_editor);
        initializeView();
        setupDatePicker();
        setupSpinner();
    }

    // Find all relevant views that we will need to read user input from
    private void initializeView() {
        pName = (EditText) findViewById(R.id.name_edit_text);
        pLastName = (EditText) findViewById(R.id.last_name_edit_text);
        pDOB = (EditText) findViewById(R.id.dob_edit_text);
        pGenderSpinner = (Spinner) findViewById(R.id.gender_spinner);
        pCountry = (EditText) findViewById(R.id.country_edit_text);
        pCity = (EditText) findViewById(R.id.city_edit_text);
        pClub = (EditText) findViewById(R.id.club_edit_text);
        pBowClass = (EditText) findViewById(R.id.bow_class_edit_text);
    }

    private void setupDatePicker() {
        pDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(ParticipantEditorActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                String editTextDateParam = dayOfMonth + "." + (month + 1) + "." + year;
                                pDOB.setText(editTextDateParam);
                            }
                        }, year, month, day);

                datePickerDialog.show();
            }
        });
    }

    private void setupSpinner() {

        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_gender_options, android.R.layout.simple_spinner_item);
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        pGenderSpinner.setAdapter(genderSpinnerAdapter);
        pGenderSpinner.setSelection(2);

        pGenderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty((selection))) {

                    if (selection.equals(getString(R.string.gender_female))) {
                        pGender = ParticipantEntry.GENDER_FEMALE;
                    } else if (selection.equals((getString(R.string.gender_male)))) {
                        pGender = ParticipantEntry.GENDER_MALE;
                    } else {
                        pGender = ParticipantEntry.GENDER_UNKNOWN;
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                pGender = 2;
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_participants_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                saveToDb();
                finish();
                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.action_delete:
                // Do nothing for now
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // Navigate back to parent activity (CatalogActivity)
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Get user input from editor and save/update product into database.
     */

    private void saveToDb() {

        //Read Values from text field
        String name = pName.getText().toString().trim();
        String lastName = pLastName.getText().toString().trim();
        String dob = pDOB.getText().toString().trim();
        String country = pCountry.getText().toString().trim();
        String city = pCity.getText().toString().trim();
        String club = pClub.getText().toString().trim();
        String bowClass = pBowClass.getText().toString().trim();


        //Check if is new or if an update
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(lastName)
                || TextUtils.isEmpty(dob) || TextUtils.isEmpty(country)
                || TextUtils.isEmpty(city) || TextUtils.isEmpty(club)
                || TextUtils.isEmpty(bowClass)) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_LONG).show();
            // No change has been made so we can return
            return;
        }

        ParticipantsDbHelper mDbHelper = new ParticipantsDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        //we set values for insert update
        ContentValues values = new ContentValues();
        values.put(ParticipantEntry.COLUMN_NAME, name);
        values.put(ParticipantEntry.COLUMN_LAST_NAME, lastName);
        values.put(ParticipantEntry.COLUMN_DOB, dob);
        values.put(ParticipantEntry.COLUMN_GENDER, pGender);
        values.put(ParticipantEntry.COLUMN_COUNTRY, country);
        values.put(ParticipantEntry.COLUMN_CITY, city);
        values.put(ParticipantEntry.COLUMN_CLUB, club);
        values.put(ParticipantEntry.COLUMN_CLASS, bowClass);

        long newRowId = db.insert(ParticipantEntry.TABLE_NAME, null, values);
        if (newRowId == -1) {
            Toast.makeText(this, "You have an error", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "You have a new participant", Toast.LENGTH_SHORT).show();
        }

    }
}