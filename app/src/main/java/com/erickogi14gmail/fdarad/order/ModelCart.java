package com.erickogi14gmail.fdarad.order;

/**
 * Created by kimani kogi on 2/15/2017.
 */

public class ModelCart {
//Our model Variables


    private int dish_id;
    // private int hotel_id;
    private String dish_name;
    private String dish_description;
    private String dish_image;
    //  private int dish_accompaniments;
    private Double dish_price;
    private int dish_quantity;

    public ModelCart() {

    }

    public ModelCart(int dish_id,
                     // int hotel_id,
                     String dish_name,
                     String dish_description,
                     String dish_image,
                     // int dish_accompaniments,
                     Double dish_price,
                     int dish_quantity) {
        this.dish_id = dish_id;
        // this.hotel_id = hotel_id;
        this.dish_name = dish_name;
        this.dish_description = dish_description;
        this.dish_image = dish_image;
        // this.dish_accompaniments = dish_accompaniments;
        this.dish_price = dish_price;
        this.dish_quantity = dish_quantity;
    }

    public int getDish_id() {
        return dish_id;
    }

    public void setDish_id(int dish_id) {
        this.dish_id = dish_id;
    }

    public String getDish_name() {
        return dish_name;
    }

    public void setDish_name(String dish_name) {
        this.dish_name = dish_name;
    }

    public String getDish_description() {
        return dish_description;
    }

    public void setDish_description(String dish_description) {
        this.dish_description = dish_description;
    }

    public String getDish_image() {
        return dish_image;
    }

    public void setDish_image(String dish_image) {
        this.dish_image = dish_image;
    }

    public Double getDish_price() {
        return dish_price;
    }

    public void setDish_price(Double dish_price) {
        this.dish_price = dish_price;
    }

    public int getDish_quantity() {
        return dish_quantity;
    }

    public void setDish_quantity(int dish_quantity) {
        this.dish_quantity = dish_quantity;
    }
}

