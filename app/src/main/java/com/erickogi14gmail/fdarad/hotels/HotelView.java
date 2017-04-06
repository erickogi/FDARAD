package com.erickogi14gmail.fdarad.hotels;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.erickogi14gmail.fdarad.R;
import com.erickogi14gmail.fdarad.dishes.DishJsonParser;
import com.erickogi14gmail.fdarad.dishes.Model;
import com.erickogi14gmail.fdarad.dishes.ModelAdapter;
import com.erickogi14gmail.fdarad.mPicasso.PicassoClient;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HotelView extends AppCompatActivity {
TextView _hotel_description ,_hotel_working_hours,_hotel_location,_hotel_pickup_mode;
    RatingBar _hotel_rating;
    String urlDishTypes="http://erickogi.co.ke/fdarad/api/?action=get_dish_type_by_hotel_id&hotel_id=";
    String urlDishByTypeByHotel="";


    static Context context;
    static View view;
    static RequestQueue queue ;


    int     id;




    ArrayList<Model> dish_model;
    RecyclerView lv;
    String uri = "http://erickogi.co.ke/fdarad/api/?action=get_dish_by_hotel_id&hotel_id=";
    ModelAdapter adapter;

    static RequestQueue queued ;
    static RecyclerView.LayoutManager mLayoutManager;
    SwipeRefreshLayout swipe_refresh_layout;








    public void setDishTypesSpinner(ArrayList<String>types){



        ArrayAdapter<String> arrayAdapterc = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, types);
        MaterialBetterSpinner materialDesignSpinnerc = (MaterialBetterSpinner)
                findViewById(R.id.android_material_design_spinner_dishtype);
        materialDesignSpinnerc.setAdapter(arrayAdapterc);

    }






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent=getIntent();
        toolbar.setTitle( intent.getStringExtra("name"));
        toolbar.setTitleTextColor(0xFFFFFFFF);
        //setSupportActionBar(toolbar);

        CollapsingToolbarLayout collapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.toolbar_layout) ;

        _hotel_description=(TextView)findViewById(R.id.hotel_description) ;
        _hotel_working_hours=(TextView)findViewById(R.id.hotel_hours) ;
        _hotel_location=(TextView)findViewById(R.id.hotel_location);
        _hotel_pickup_mode=(TextView)findViewById(R.id.hotel_pickup_mode) ;
        _hotel_rating=(RatingBar)findViewById(R.id.hotel_ratingbar) ;








        String  description=intent.getStringExtra("description");
        String  delivery=intent.getStringExtra("delivery");
        String  hours=intent.getStringExtra("hours");
        String  location=intent.getStringExtra("location");
        id=intent.getIntExtra("id",0);


        _hotel_description.setText(description);
        _hotel_working_hours.setText(hours);
        _hotel_location.setText(location);
        _hotel_rating.setMax(5);
        _hotel_rating.setProgress(3);
        _hotel_pickup_mode.setText(delivery);









        ImageView img=new ImageView(this);

        PicassoClient.LoadImage (this,intent.getStringExtra("image"),img);
        //ct.setBackground(img.getDrawable());
        collapsingToolbarLayout.setBackground(img.getDrawable());

        requestData(urlDishByTypeByHotel,1);



        lv=(RecyclerView)findViewById(R.id.recycle_view_hoteldish);

 }






    public void requestData(final String uri , final int a) {

        // StringRequest request = new StringRequest(uri,
        StringRequest stringRequest = new StringRequest(Request.Method.GET, uri+""+String.valueOf(id),

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("tjson",response+""+uri);

                        if(response!=null||!response.isEmpty()) {

                            JSONArray _arry = null;
                            JSONObject jObj = null;
                            ArrayList<String> types=new ArrayList<>();
                            try {
                                jObj = new JSONObject(response);

                                _arry = jObj.getJSONArray("data");

                                for (int i = 0; i < _arry.length(); i++) {
                                    JSONObject obj = _arry.getJSONObject(i);
                                    String gType=obj.getString("dish_type");
                                    if(types.contains(gType)){

                                    }
                                    else{
                                        types.add(gType);
                                    }


                                }
                                setDishTypesSpinner(types);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                            else
                        {
                            Toast.makeText(getApplicationContext(), "nul", Toast.LENGTH_LONG).show();
                        }

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        //  Toast.makeText(getActivity(), error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });

        queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);
        context=getApplicationContext();
    }






    public void requestDishData(String uri) {

        // StringRequest request = new StringRequest(uri,
        StringRequest stringRequest = new StringRequest(Request.Method.GET, uri,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        //  Toast.makeText(getApplicationContext(), "dfghjkljhgfdddfgh"+response, Toast.LENGTH_LONG).show();
                        if (response != null || !response.isEmpty()) {
                            try {


                                //  adapter.notifyDataSetChanged();
                                dish_model = DishJsonParser.parseData(response);
                                ModelAdapter adapter = new ModelAdapter(dish_model, getApplicationContext());
                                adapter.notifyDataSetChanged();


                                mLayoutManager = new LinearLayoutManager(getApplication());
                                lv.setLayoutManager(mLayoutManager);
                                lv.setItemAnimator(new DefaultItemAnimator());


                                lv.setAdapter(adapter);
                                //swipe_refresh_layout.setRefreshing(false);
                            } catch (Exception nul){

                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "nul", Toast.LENGTH_LONG).show();
                        }

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        try {
                            Toast.makeText(HotelView.this,"Network Error", Toast.LENGTH_SHORT).show();
                        } catch (Exception v) {

                        }
                    }
                });

        queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(stringRequest);
        context = getApplicationContext();
    }







}
