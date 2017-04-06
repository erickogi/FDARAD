package com.erickogi14gmail.fdarad.hotels;

import android.util.Log;

import com.erickogi14gmail.fdarad.MainActivity;
import com.erickogi14gmail.fdarad.dishes.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by kimani kogi on 3/17/2017.
 */

public class HotelJsonParser {
    static ArrayList<HotelModel> hotel_list;

    public static ArrayList<HotelModel> parseData(String content) {

        JSONArray dish_arry = null;
        HotelModel model = null;
        try {


            hotel_list = new ArrayList<>();
            JSONObject jObj = new JSONObject(content);
            dish_arry = jObj.getJSONArray("data");

            for (int i = 0; i < dish_arry.length(); i++) {

                JSONObject obj = dish_arry.getJSONObject(i);
                model = new HotelModel();

                model.setHotel_id(obj.getInt("hotel_id"));
                model.setHotel_name(obj.getString("hotel_name"));
                model.setHotel_description(obj.getString("hotel_description"));
                model.setHotel_location(obj.getString("hotel_location"));
                model.setHotel_hours(obj.getString("hotel_work_hours"));
                model.setHotel_image(obj.getString("hotel_image"));
                model.setHotel_delivery_options(obj.getString("hotel_delivery_options"));
                model.setHotel_ratings(obj.getInt("hotel_ratings"));

                hotel_list.add(model);
            }
            return hotel_list;

        }
        catch (JSONException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
