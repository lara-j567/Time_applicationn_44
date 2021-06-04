package com.example.timeapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    private Button btn_add;
    private ListView lv;
    ArrayList<Time> timeList = new ArrayList<Time>();
    //DatabaseHelper db;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        populateData();

        setUpList(timeList);


    }

    private void insertData(String name, String time) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL2,name);
        contentValues.put(DatabaseHelper.COL3,time);
        long id=db.insert(DatabaseHelper.TABLE_NAME,null , contentValues);


        if(id == -1){

            Toast.makeText(this, "data write failed", Toast.LENGTH_SHORT).show();

        }

        else{


            Toast.makeText(this, "data write successful", Toast.LENGTH_SHORT).show();
        }




    }

    private void setUpList(ArrayList<Time> list) {


    }


    private void populateData() {


        Date now = new Date();
        String time;
        String name;
        name = "Karachi";
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Karachi"));
        time = now.toString();
        insertData(name , time);
        Time t1 = new Time("KHI", time, name);

        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Tokyo"));
        name = "Tokyo";
        time = now.toString();
        insertData(name , time);
        Time t2 = new Time("TOKYO", time, name);

        TimeZone.setDefault(TimeZone.getTimeZone("Canada/Pacific"));
        name = "Canada";
        time = now.toString();
        insertData(name , time);
        Time t3 = new Time("CANADA", time, name);

        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Amsterdam"));
        name = "Amsterdam";
        time = now.toString();
        insertData(name , time);
        Time t4 = new Time("AMSTERDAM", time, name);

        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Oslo"));
        name = "Oslo";
        time = now.toString();
        insertData(name , time);
        Time t5 = new Time("OSLO", time, name);

        timeList.add(t1);
        timeList.add(t2);
        timeList.add(t3);
        timeList.add(t4);
        timeList.add(t5);

    }


}