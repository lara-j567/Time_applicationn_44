package com.example.timeapplication;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    Activity activity;
    ArrayList<Time> timeList;
    LayoutInflater inflater;

    public CustomAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return timeList.size();
    }

    public CustomAdapter(Activity activity, ArrayList<Time> timeList) {
        this.activity = activity;
        this.timeList = timeList;
        inflater = activity.getLayoutInflater();
    }


    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder = null;
        if (view == null) {

            view = inflater.inflate(R.layout.time_list, viewGroup, false);

            holder = new ViewHolder();

            holder.countryName = (TextView) view.findViewById(R.id.countryNameList);
            holder.flag_img = (ImageView) view.findViewById(R.id.listImgFlag);
            holder.checkBox = (ImageView) view.findViewById(R.id.iv_check_box);
            view.setTag(holder);

        } else {

            holder = (ViewHolder) view.getTag();
        }
            Time time = timeList.get(i);

            if(time.isSelected())
            {
                holder.checkBox.setBackgroundResource(R.drawable.checked);
            }

            else
            {
                holder.checkBox.setBackgroundResource(R.drawable.check);
            }





            holder.countryName.setText(time.getCountry_name());

            holder.flag_img.setImageResource(time.getImage());



            return view;




    }
    public  void updateList(ArrayList<Time> timeList){

        this.timeList = timeList;

        notifyDataSetChanged();
    }

    class ViewHolder {

        ImageView checkBox;
        TextView countryName;
        ImageView flag_img;
    }
}
