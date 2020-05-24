package com.example.viktorina;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "recordsDb";
    public static final String TABLE_USERS = "users";
    public static final String TABLE_RECORDS = "records";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ATTEMPT = "attempt";
    public static final String COLUMN_LVL = "lvl";
    public static final String COLUMN_TIME = "time";
    public static final String COLUMN_US_ID = "user_id";


    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_USERS + " ("  + COLUMN_ID + " INTEGER PRIMARY KEY NOT NULL, " +  COLUMN_NAME + " TEXT NOT NULL UNIQUE)");

        db.execSQL("CREATE TABLE " + TABLE_RECORDS + " ("  + COLUMN_ATTEMPT + " INTEGER PRIMARY KEY NOT NULL, " + COLUMN_LVL +" INTEGER NOT NULL, " +
              COLUMN_TIME +  " TEXT NOT NULL, " + COLUMN_US_ID + " INTEGER NOT NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + "users");
        db.execSQL("DROP TABLE IF EXISTS " + "records");
        onCreate(db);
    }
}
