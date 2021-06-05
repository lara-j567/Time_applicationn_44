package com.example.timeapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdapterTwo extends ArrayAdapter<Time> {

    public AdapterTwo(Context context, int resource, List<Time> timeList) {
        super(context, resource, timeList);
    }

    public View getView(int position , View convertview , ViewGroup parent){

        Time time_obj = getItem(position);

        if(convertview==null){

            convertview= LayoutInflater.from(getContext()).inflate(R.layout.time_list, parent , false);

        }

        TextView tv;

        ImageView iv;

        tv = convertview.findViewById(R.id.countryNameList);

        iv = convertview.findViewById(R.id.listImgFlag);

        tv.setText(time_obj.getCountry_name());


        iv.setImageResource(time_obj.getImage());

        return  convertview;


    }



}
