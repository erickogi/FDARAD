package com.erickogi14gmail.fdarad.hotels;

/**
 * Created by kimani kogi on 2/15/2017.
 */

public class HotelModel {
//Our model Variables


    // private int dish_id;
    private int hotel_id;
    private String hotel_name;
    private String hotel_description;
    private String hotel_image;
    private String hotel_location;
    private String hotel_delivery_options;
    private String hotel_hours;
    private int hotel_ratings;

    public HotelModel() {

    }

    public HotelModel(int hotel_id, String hotel_name, String hotel_description,
                      String hotel_image, String hotel_location, String hotel_delivery_options,String hotel_hours, int hotel_ratings) {
        this.hotel_id = hotel_id;
        this.hotel_name = hotel_name;
        this.hotel_description = hotel_description;
        this.hotel_image = hotel_image;
        this.hotel_location = hotel_location;
        this.hotel_delivery_options = hotel_delivery_options;
        this.hotel_ratings = hotel_ratings;
        this.hotel_hours = hotel_hours;
    }

    public String getHotel_hours() {
        return hotel_hours;
    }

    public void setHotel_hours(String hotel_hours) {
        this.hotel_hours = hotel_hours;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String getHotel_description() {
        return hotel_description;
    }

    public void setHotel_description(String hotel_description) {
        this.hotel_description = hotel_description;
    }

    public String getHotel_image() {
        return hotel_image;
    }

    public void setHotel_image(String hotel_image) {
        this.hotel_image = hotel_image;
    }

    public String getHotel_location() {
        return hotel_location;
    }

    public void setHotel_location(String hotel_location) {
        this.hotel_location = hotel_location;
    }

    public String getHotel_delivery_options() {
        return hotel_delivery_options;
    }

    public void setHotel_delivery_options(String hotel_delivery_options) {
        this.hotel_delivery_options = hotel_delivery_options;
    }

    public int getHotel_ratings() {
        return hotel_ratings;
    }

    public void setHotel_ratings(int hotel_ratings) {
        this.hotel_ratings = hotel_ratings;
    }
}