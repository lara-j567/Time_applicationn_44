package com.example.timeapplication;

import java.io.Serializable;

public class Time implements Serializable {

    private String id;
    private String country_time;
    private String country_name;
    private int Image;

    public Time(String id, String country_time, String country_name, int image) {
        this.id = id;
        this.country_time = country_time;
        this.country_name = country_name;
        Image = image;
    }

    public Time(String id, String country_time, String country_name) {
        this.id = id;
        this.country_time = country_time;
        this.country_name = country_name;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCountry_time() {
        return country_time;
    }

    public void setCountry_time(String country_time) {
        this.country_time = country_time;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }
}
