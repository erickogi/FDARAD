package com.erickogi14gmail.fdarad.dishes;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.erickogi14gmail.fdarad.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by kimani kogi on 3/17/2017.
 */

public class DishJsonParser {
    static ArrayList<Model> dish_list;

    public static ArrayList<Model> parseData(String content) {
    Log.d("json",content+""+ MainActivity.typeOfDishSelected);
       // Toast.makeText(, "", Toast.LENGTH_SHORT).show();
        JSONArray dish_arry = null;
        Model model = null;
        try {


            dish_list = new ArrayList<>();
            JSONObject jObj = new JSONObject(content);
            dish_arry = jObj.getJSONArray("data");
            if(dish_arry.length()<1){
                Log.d("jsonem",content+""+ MainActivity.typeOfDishSelected);
            //   Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
            }
            for (int i = 0; i < dish_arry.length(); i++) {

                JSONObject obj = dish_arry.getJSONObject(i);
                model = new Model();

                model.setDish_id(obj.getInt("dish_id"));
                model.setDish_name(obj.getString("dish_name"));
                model.setDish_description(obj.getString("dish_description"));
                model.setDish_price(obj.getDouble("dish_price"));
                model.setDish_image(obj.getString("dish_image"));
                model.setDish_accompaniments(obj.getInt("dish_accompaniments_id"));
                model.setHotel_id(obj.getInt("hotel_id"));
                model.setDish_ratings((float) obj.getDouble("dish_ratings"));


                dish_list.add(model);
            }
            return dish_list;

        }
        catch (JSONException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
