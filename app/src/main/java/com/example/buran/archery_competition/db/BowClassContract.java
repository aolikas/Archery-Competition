package com.example.buran.archery_competition.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class BowClassContract {

    /**
     * the empty constructor, to prevent someone from
     * accidentally instantiating the contract class
     */
    public BowClassContract() {
    }

    /**
     * Inner class that defines constant values for the bow class database table.
     */
    public static final class BowClassEntry implements BaseColumns {


        /**
         * name of database table for bow class
         */
        public static final String TABLE_NAME = "bowClasses";

        /**
         * Unique ID number for a bow class
         * Integer
         */
        public static final String _ID = BaseColumns._ID;

        /**
         * Name/label of a bow class
         * TEXT
         */
        public static final String COLUMN_NAME = "label";


        //create a table
        public static final String CREATE_BOW_CLASS_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT NOT NULL)";
    }




}

