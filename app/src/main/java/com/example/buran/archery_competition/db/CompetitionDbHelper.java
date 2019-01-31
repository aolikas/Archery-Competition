package com.example.buran.archery_competition.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.buran.archery_competition.db.ParticipantContract.ParticipantEntry;
import com.example.buran.archery_competition.db.BowClassContract.BowClassEntry;

import java.util.ArrayList;
import java.util.List;

public class CompetitionDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = CompetitionDbHelper.class.getSimpleName();

    /**
     * name of the database file
     */
    private static final String DATABASE_NAME = "participantsVerOne.db";

    /**
     * Database version. If you change a schema, version will increment
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Create a new CompetitionDbHelper object
     *
     * @param context is context of an app
     */
    public CompetitionDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is created for the first time
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create and execute the SQL statement
        db.execSQL(ParticipantEntry.CREATE_PARTICIPANT_TABLE);
        db.execSQL(BowClassEntry.CREATE_BOW_CLASS_TABLE);

    }

    /**
     * This is called when the database will update
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + ParticipantEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + BowClassEntry.TABLE_NAME);

        //create new tables
        onCreate(db);
    }

    public List<String> getAllBowClasses() {

        List<String> list = new ArrayList<String>();

        String selectQuery = "SELECT * FROM " + BowClassEntry.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return list;
    }
}
