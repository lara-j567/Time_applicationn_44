package com.example.timeapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    private Button btn_add;
    private ListView lv;
    ArrayList<Time> timeList = new ArrayList<Time>();
    ArrayList<Time> DBtimeList = new ArrayList<Time>();
    ArrayList<Time> modifiedtimeList = new ArrayList<Time>();
    DatabaseHelper dbObj;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Adapter time_adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openHelper = new DatabaseHelper(this);
        lv = findViewById(R.id.countryListView);
        dbObj = new DatabaseHelper(this);
        btn_add = findViewById(R.id.btnAdd);

        if (timeList.size() != 0) {

            timeList.removeAll(timeList);
        }

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, AllTime.class);
                //startActivity(intent,1);
                startActivityForResult(intent , 1);


            }
        });

        populateData();

        setUpList();


        Thread thread = new Thread() {
            @Override
            public void run() {

                while (!isInterrupted()) {


                    try {

                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                ////timeList.removeAll(timeList);
                                //DBtimeList.removeAll(DBtimeList);
                                updateData();
                               // Toast.makeText(MainActivity.this, "calling thread", Toast.LENGTH_SHORT).show();
                                time_adapter.updateList();


                            }
                        });


                    } catch (InterruptedException e) {

                        e.printStackTrace();

                    }
                }


            }
        };

        thread.start();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
/*
        if(resultCode == 1){

            modifiedtimeList = (ArrayList<Time>)data.getSerializableExtra("Modified List");
            timeList.addAll(timeList.size()-1 , modifiedtimeList);
            Toast.makeText(this, String.valueOf(timeList.size()), Toast.LENGTH_SHORT).show();
            //time_adapter.updateList();
           // setUpList();
             for (Time t:modifiedtimeList){


                    Toast.makeText(this, t.getCountry_name(), Toast.LENGTH_SHORT).show();
                }




        }

        else
        {
            Toast.makeText(this, "no data passed", Toast.LENGTH_SHORT).show();
        }*/
        if (requestCode == 1) {

            if (resultCode == RESULT_OK) {

                // Toast.makeText(this, "Suucess", Toast.LENGTH_SHORT).show();
                modifiedtimeList = (ArrayList<Time>) data.getSerializableExtra("Modified List");
/*
                for (Time t:modifiedtimeList){


                    Toast.makeText(this, t.getCountry_name(), Toast.LENGTH_SHORT).show();
                }
*/

                timeList.addAll(timeList.size()-1  , modifiedtimeList);

                for (Time t:timeList){


                    Toast.makeText(this, t.getCountry_name(), Toast.LENGTH_SHORT).show();
                }



            } else {

                Toast.makeText(this, "no data passed", Toast.LENGTH_SHORT).show();


            }


        }

    }

    private void updateData() {

        int index=0;
        Date now = new Date();
        String time;
        for (Time t:timeList)
        {
            TimeZone.setDefault(TimeZone.getTimeZone(t.getId()));
            time = now.toString();

            Time temp = new Time(t.getId() , time , t.getCountry_name());
            timeList.set(index, temp);
            index++;
        }

/*

        Date now = new Date();
        //  db = openHelper.getWritableDatabase();
        String time;
        String name;
        name = "Karachi";
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Karachi"));
        time = now.toString();
        //insertData(name, time);
        Time temp1 = new Time("KHI", time, name);
        timeList.set(0, temp1);
       // Toast.makeText(this, temp1.getCountry_time(), Toast.LENGTH_SHORT).show();


        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Tokyo"));
        name = "Tokyo";
        time = now.toString();
        Time temp2 = new Time("TOKYO", time, name);
        timeList.set(1, temp2);
     //   Toast.makeText(this, temp2.getCountry_time(), Toast.LENGTH_SHORT).show();


        TimeZone.setDefault(TimeZone.getTimeZone("Canada/Pacific"));
        name = "Canada";
        time = now.toString();
        insertData(name, time);
        Time temp3 = new Time("CANADA", time, name);
        timeList.set(2, temp3);
     //   Toast.makeText(this, temp3.getCountry_time(), Toast.LENGTH_SHORT).show();


        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Amsterdam"));
        name = "Amsterdam";
        time = now.toString();
        insertData(name, time);
        Time temp4 = new Time("AMSTERDAM", time, name);
        timeList.set(3, temp4);
      //  Toast.makeText(this, temp4.getCountry_time(), Toast.LENGTH_SHORT).show();


        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Oslo"));
        name = "Oslo";
        time = now.toString();
        insertData(name, time);
        Time temp5 = new Time("OSLO", time, name);
        timeList.set(4, temp5);
    //    Toast.makeText(this, temp5.getCountry_time(), Toast.LENGTH_SHORT).show();
*/


    }

    private void insertData(String name, String time) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL2, name);
        contentValues.put(DatabaseHelper.COL3, time);
        long id = db.insert(DatabaseHelper.TABLE_NAME, null, contentValues);


        if (id == -1) {

            // Toast.makeText(this, "data write failed", Toast.LENGTH_SHORT).show();

        } else {


            //Toast.makeText(this, "data write successful", Toast.LENGTH_SHORT).show();
        }


    }

    private void setUpList() {

        // populateData();

        Cursor data = dbObj.getTimesList();
        // if (data.getCount() != 0) {
     //   Toast.makeText(this, "from local list", Toast.LENGTH_SHORT).show();
        //  populateData();
        time_adapter = new Adapter(this, 0, timeList);
        lv.setAdapter(time_adapter);
        // time_adapter.updateList();

        //}

        /*else {

            while (data.moveToNext()) {

                String name = data.getString(1);
                String time = data.getString(2);
                // Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
                //Toast.makeText(this, time, Toast.LENGTH_SHORT).show();
                Time temp = new Time("1", time, name);

                DBtimeList.add(temp);
            }


            Adapter time_adapter = new Adapter(this, 0, DBtimeList);
            lv.setAdapter(time_adapter);

            Toast.makeText(this, "from DB times list", Toast.LENGTH_SHORT).show();
        }*/


    }


    private void populateData() {


        Date now = new Date();
        db = openHelper.getWritableDatabase();
        String time;
        String name;
        name = "Karachi";
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Karachi"));
        time = now.toString();
        insertData(name, time);
        Time t1 = new Time("Asia/Karachi", time, name);

        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Tokyo"));
        name = "Tokyo";
        time = now.toString();
        insertData(name, time);
        Time t2 = new Time("Asia/Tokyo", time, name);

        TimeZone.setDefault(TimeZone.getTimeZone("Canada/Pacific"));
        name = "Canada";
        time = now.toString();
        insertData(name, time);
        Time t3 = new Time("Canada/Pacific", time, name);

        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Amsterdam"));
        name = "Amsterdam";
        time = now.toString();
        insertData(name, time);
        Time t4 = new Time("Europe/Amsterdam", time, name);

        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Oslo"));
        name = "Oslo";
        time = now.toString();
        insertData(name, time);
        Time t5 = new Time("Europe/Oslo", time, name);

        timeList.add(t1);
        timeList.add(t2);
        timeList.add(t3);
        timeList.add(t4);
        timeList.add(t5);

    }

}