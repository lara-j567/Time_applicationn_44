/*
package com.example.timeapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class AllTime extends AppCompatActivity {

    ArrayList<Time> timesList = new ArrayList<>();
    ArrayList<Time> added_time = new ArrayList<>();
    ListView lv;
    Button btn;
    SearchView search_view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_time);

        initSearchWidget();
        populateData();
        setUpList();


        setTitle("Time List");
        lv = findViewById(R.id.listView2);
        btn = findViewById(R.id.btnAddCountry);
        search_view = findViewById(R.id.SearchList);

    }

    private void setUpList() {
        lv = findViewById(R.id.listView2);
        AdapterTwo adapter = new AdapterTwo(this, 0, timesList);
        lv.setAdapter(adapter);

    }

    private void populateData() {


        Date now = new Date();
        TimeZone.setDefault(TimeZone.getTimeZone("US/Hawaii"));
        Time t1 = new Time("us/Hawaii", now.toString(), "Hawaii", R.drawable.hawaii);

        TimeZone.setDefault(TimeZone.getTimeZone("Brazil/West"));
        Time t2 = new Time("brazil", now.toString(), "Brazil", R.drawable.brazil);

        TimeZone.setDefault(TimeZone.getTimeZone("America/Mexico_City"));
        Time t3 = new Time("mexico", now.toString(), "Mexico", R.drawable.mexico);

        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Bahrain"));
        Time t4 = new Time("bahrain", now.toString(), " Bahrain ", R.drawable.bahrain);

        TimeZone.setDefault(TimeZone.getTimeZone("Africa/Ceuta"));
        Time t5 = new Time("ceuta", now.toString(), "Ceuta", R.drawable.ceuta);

        TimeZone.setDefault(TimeZone.getTimeZone("Cuba"));
        Time t6 = new Time("cuba", now.toString(), "Cuba", R.drawable.cuba);

        TimeZone.setDefault(TimeZone.getTimeZone("Egypt"));
        Time t7 = new Time("egypt", now.toString(), "Egypt", R.drawable.egypt);

        TimeZone.setDefault(TimeZone.getTimeZone("Iran"));
        Time t8 = new Time("iran", now.toString(), "Iran", R.drawable.iran);

        TimeZone.setDefault(TimeZone.getTimeZone("Indian/Mauritius"));
        Time t9 = new Time("mauritius", now.toString(), "Mauritius", R.drawable.mauritius);

        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Macao"));
        Time t10 = new Time("macao", now.toString(), "Macao", R.drawable.macao);

        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Brunei"));
        Time t11 = new Time("brunei", now.toString(), "Brunei", R.drawable.brunei);

        TimeZone.setDefault(TimeZone.getTimeZone("Singapore"));
        Time t12 = new Time("singapore", now.toString(), "Singapore", R.drawable.singapore);

        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Hong_Kong"));
        Time t13 = new Time("hong_kong", now.toString(), "Honk Kong", R.drawable.hong_kong);


        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Isle_of_Man"));
        Time t14 = new Time("isle_of_man", now.toString(), "Isle of Man", R.drawable.isle_of_man);

        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Riyadh"));
        Time t15 = new Time("riyadh", now.toString(), "Riyadh", R.drawable.saudi_arabia);

        timesList.add(t1);
        timesList.add(t2);
        timesList.add(t3);
        timesList.add(t4);
        timesList.add(t5);
        timesList.add(t6);
        timesList.add(t7);
        timesList.add(t8);
        timesList.add(t9);
        timesList.add(t10);
        timesList.add(t11);
        timesList.add(t12);
        timesList.add(t13);
        timesList.add(t14);
        timesList.add(t15);

    }

    private void initSearchWidget() {


        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                ArrayList<Time> filtered_time = new ArrayList<Time>();

                for (Time t : timesList) {

                    if (t.getCountry_name().toLowerCase().contains(newText.toLowerCase())) {

                        filtered_time.add(t);
                    }

                    AdapterTwo adapter_two = new AdapterTwo(getApplicationContext(), 0, filtered_time);
                    lv.setAdapter(adapter_two);


                }
                return false;
            }
        });
    }
}*/
package com.example.timeapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class AllTime extends AppCompatActivity {

    ArrayList<Time> timesList = new ArrayList<Time>();
    ListView lv;
    ArrayList<Time> added_time = new ArrayList<Time>();
    CheckBox checkBox;
    CustomAdapter adapter;
    int index=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_time);

        initSearchWidget();

        setTitle("Time List");

        populateData();

        setUpList();
        lv=findViewById(R.id.listView2);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Time t = timesList.get(position);

                if(t.isSelected())
                    t.setSelected(false);
                else
                    t.setSelected(true);

               // timesList.set(position  , t );
                added_time.add(t);


               Toast.makeText(AllTime.this, added_time.get(index).getCountry_name(), Toast.LENGTH_SHORT).show();
               index++;
               // String ti = String.valueOf(position);
              //  Toast.makeText(AllTime.this, ti, Toast.LENGTH_SHORT).show();
                adapter.updateList(timesList);
              //  Toast.makeText(AllTime.this, t.getCountry_name(), Toast.LENGTH_SHORT).show();

            }
        });




        Button btn = findViewById(R.id.btnAddCountry);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent result_intent = new Intent(AllTime.this , MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("Modified List" ,added_time);
                result_intent.putExtras(bundle);

                //startActivityForResult(result_intent , RESULT_OK);

                setResult(RESULT_OK , result_intent);
                finish();

            }
        });


    }

    private void parseList() {

        for(Time d:added_time){

           Toast.makeText(this, "data "+d.getCountry_name(), Toast.LENGTH_SHORT).show();
        }
    }


    private void initSearchWidget() {

        SearchView search_view = findViewById(R.id.SearchList);

        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                ArrayList<Time> filtered_time = new ArrayList<Time>();

                for (Time t : timesList) {

                    if (t.getCountry_name().toLowerCase().contains(newText.toLowerCase())) {

                        filtered_time.add(t);
                    }

                    AdapterTwo adapter_two = new AdapterTwo(getApplicationContext(), 0, filtered_time);
                    lv.setAdapter(adapter_two);




                }
                return false;
            }
        });
    }

    private void populateData() {


        Date now = new Date();
        TimeZone.setDefault(TimeZone.getTimeZone("US/Hawaii"));
        Time t1 = new Time("US/Hawaii", now.toString(), "Hawaii", R.drawable.hawaii , false);

        TimeZone.setDefault(TimeZone.getTimeZone("Brazil/West"));
        Time t2 = new Time("Brazil/West", now.toString(), "Brazil", R.drawable.brazil , false);

        TimeZone.setDefault(TimeZone.getTimeZone("America/Mexico_City"));
        Time t3 = new Time("America/Mexico_City", now.toString(), "Mexico", R.drawable.mexico,false);

        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Bahrain"));
        Time t4 = new Time("Asia/Bahrain", now.toString(), " Bahrain ", R.drawable.bahrain,false);

        TimeZone.setDefault(TimeZone.getTimeZone("Africa/Ceuta"));
        Time t5 = new Time("Africa/Ceuta", now.toString(), "Ceuta", R.drawable.ceuta,false);

        TimeZone.setDefault(TimeZone.getTimeZone("Cuba"));
        Time t6 = new Time("Cuba", now.toString(), "Cuba", R.drawable.cuba,false);

        TimeZone.setDefault(TimeZone.getTimeZone("Egypt"));
        Time t7 = new Time("Egypt", now.toString(), "Egypt", R.drawable.egypt,false);

        TimeZone.setDefault(TimeZone.getTimeZone("Iran"));
        Time t8 = new Time("Iran", now.toString(), "Iran", R.drawable.iran,false);

        TimeZone.setDefault(TimeZone.getTimeZone("Indian/Mauritius"));
        Time t9 = new Time("Indian/Mauritius", now.toString(), "Mauritius", R.drawable.mauritius,false);

        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Macao"));
        Time t10 = new Time("Asia/Macao", now.toString(), "Macao", R.drawable.macao,false);

        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Brunei"));
        Time t11 = new Time("Asia/Brunei", now.toString(), "Brunei", R.drawable.brunei,false);

        TimeZone.setDefault(TimeZone.getTimeZone("Singapore"));
        Time t12 = new Time("Singapore", now.toString(), "Singapore", R.drawable.singapore,false);

        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Hong_Kong"));
        Time t13 = new Time("Asia/Hong_Kong", now.toString(), "Honk Kong", R.drawable.hong_kong,false);


        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Isle_of_Man"));
        Time t14 = new Time("Europe/Isle_of_Man", now.toString(), "Isle of Man", R.drawable.isle_of_man,false);

        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Riyadh"));
        Time t15 = new Time("Asia/Riyadh", now.toString(), "Riyadh", R.drawable.saudi_arabia,false);

        timesList.add(t1);
        timesList.add(t2);
        timesList.add(t3);
        timesList.add(t4);
        timesList.add(t5);
        timesList.add(t6);
        timesList.add(t7);
        timesList.add(t8);
        timesList.add(t9);
        timesList.add(t10);
        timesList.add(t11);
        timesList.add(t12);
        timesList.add(t13);
        timesList.add(t14);
        timesList.add(t15);


    }


    private void setUpList() {


        //setting data in list here
        lv = findViewById(R.id.listView2);
        /*AdapterTwo adapter = new AdapterTwo(this, 0, timesList);
        lv.setAdapter(adapter);
        */
         adapter = new CustomAdapter(this, timesList);
        lv.setAdapter(adapter);

    }
}
