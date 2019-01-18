package com.example.buran.archery_competition.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.buran.archery_competition.db.ParticipantContract.ParticipantEntry;

public class ParticipantsDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = ParticipantsDbHelper.class.getSimpleName();

    /**
     * name of the database file
     */
    private static final String DATABASE_NAME = "participants.db";

    /**
     * Database version. If you change a schema, version will increment
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Create a new ParticipantsDbHelper object
     *
     * @param context is context of an app
     */
    public ParticipantsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is created for the first time
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create and execute the SQL statement
        db.execSQL(ParticipantEntry.CREATE_PARTICIPANT_TABLE);

    }

    /**
     * This is called when the database will update
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ParticipantEntry.TABLE_NAME);
        onCreate(db);
    }
}
