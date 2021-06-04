package com.example.timeapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "time_application.db";
    public static final String TABLE_NAME = "time.db";
    public static final String COL1 = "ID";
    public static final String COL2 = "COUNTRY_NAME";
    public static final String COL3 = "COUNTRY_TIME";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME,null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE "+TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT , COUNTRY_NAME TEXT , COUNTRY_TIME TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME); //drop table if exists

        onCreate(db);

    }
}
