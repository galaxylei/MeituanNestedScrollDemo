package com.example.ruofan.meituan.bean;

import android.graphics.drawable.Drawable;

/**
 * Created by ruofan on 2019/2/14.
 */
public class HotelConfig {

    private String hotel_name;
    private String hotel_image;
    private Drawable hotel_drawable;

    private int hotel_color;

    public HotelConfig(String hotel_name, String hotel_image, Drawable hotel_drawable) {
        this.hotel_name = hotel_name;
        this.hotel_image = hotel_image;
        this.hotel_drawable = hotel_drawable;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String getHotel_image() {
        return hotel_image;
    }

    public void setHotel_image(String hotel_image) {
        this.hotel_image = hotel_image;
    }

    public Drawable getHotel_drawable() {
        return hotel_drawable;
    }

    public void setHotel_drawable(Drawable hotel_drawable) {
        this.hotel_drawable = hotel_drawable;
    }


    public int getHotel_color() {
        return hotel_color;
    }

    public void setHotel_color(int hotel_color) {
        this.hotel_color = hotel_color;
    }
}
