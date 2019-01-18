package com.example.buran.archery_competition.db;

import android.provider.BaseColumns;

public class ParticipantContract {

    /**
     * the empty constructor, to prevent someone from
     * accidentally instantiating the contract class
     */
    public ParticipantContract() {
    }


    /**
     * Inner class that defines constant values for the participant's database table.
     */
    public static final class ParticipantEntry implements BaseColumns {


        /**
         * name of database table for participants
         */
        public static final String TABLE_NAME = "participants";

        /**
         * Unique ID number for a participant
         * Integer
         */
        public static final String _ID = BaseColumns._ID;

        /**
         * Name of a participant
         * TEXT
         */
        public static final String COLUMN_NAME = "name";

        /**
         * Last name of a participant
         * TEXT
         */
        public static final String COLUMN_LAST_NAME = "lastname";

        /**
         * Date of Birth of a participant
         * TEXT
         */
        public final static String COLUMN_DOB = "dob";


        /**
         * Gender of a participant
         * Integer
         */
        public static final String COLUMN_GENDER = "gender";

        /**
         * Country of a participant
         * TEXT
         */
        public static final String COLUMN_COUNTRY = "country";

        /**
         * City of a participant
         * TEXT
         */
        public static final String COLUMN_CITY = "city";

        /**
         * Club of a participant
         * TEXT
         */
        public static final String COLUMN_CLUB = "club";

        /**
         * Class of a participant
         * TEXT
         */
        public static final String COLUMN_CLASS = "class";


        public static final int GENDER_FEMALE = 0;
        public static final int GENDER_MALE = 1;
        public static final int GENDER_UNKNOWN = 2;


        //create a table
        public static final String CREATE_PARTICIPANT_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT NOT NULL, " +
                COLUMN_LAST_NAME + " TEXT NOT NULL, " +
                COLUMN_DOB + " TEXT NOT NULL, " +
                COLUMN_GENDER + " INTEGER NOT NULL DEFAULT 3, " +
                COLUMN_COUNTRY + " TEXT NOT NULL, " +
                COLUMN_CITY + " TEXT NOT NULL, " +
                COLUMN_CLUB + " TEXT NOT NULL, " +
                COLUMN_CLASS + " TEXT NOT NULL);";
    }
}
