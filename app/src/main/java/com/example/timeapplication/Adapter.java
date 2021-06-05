package com.example.timeapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Adapter extends ArrayAdapter<Time> {


    public Adapter(Context context, int resource, List<Time> timeList) {
        super(context, resource, timeList);
    }

    public View getView(int position, View convertview, ViewGroup parent) {

        Time time_obj = getItem(position);

        if (convertview == null) {

            convertview = LayoutInflater.from(getContext()).inflate(R.layout.time_cell, parent, false);

        }

        TextView tv1;
        TextView tv2;

        tv1 = convertview.findViewById(R.id.country_name);

        tv2 = convertview.findViewById(R.id.country_time);

        tv1.setText(time_obj.getCountry_name());

        tv2.setText(time_obj.getCountry_time());

        return convertview;


    }
}
