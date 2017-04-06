package com.erickogi14gmail.fdarad.hotels;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.erickogi14gmail.fdarad.R;
import com.erickogi14gmail.fdarad.dishes.Model;
import com.erickogi14gmail.fdarad.dishes.ModelAdapter;
import com.erickogi14gmail.fdarad.order.PlaceOrder;
import com.erickogi14gmail.fdarad.utils.RecyclerTouchListener;

import java.util.ArrayList;

/**
 * Created by kimani kogi on 3/1/2017.
 */

public class fragment_hotels extends Fragment {
    ArrayList<HotelModel> hotel_model;
    RecyclerView lv;
    String uri = "http://erickogi.co.ke/fdarad/api/?action=get_hotel_by_location_by_name&hotel_location=";
    ModelAdapter adapter;
    String [] dishType={"","Eldoret Town","Moi University","Chepkoilel","Moi West Campus","vegetarian"};
    static Context context;
    static View view;
    static RequestQueue queue ;
    static RecyclerView.LayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        /**
         * Inflate the layout for this fragment
         */


         view=inflater.inflate(R.layout.fragment_dishes,container,false);
        requestData(uri);

        lv =(RecyclerView) view.findViewById(R.id.recycle_view);




        lv.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), lv, new RecyclerTouchListener.ClickListener() {

            @Override
            public void onClick(View view, int position) {


    HotelModel model = hotel_model.get(position);


    Intent intent=new Intent(getActivity(),HotelView.class) ;
    intent.putExtra("delivery",model.getHotel_delivery_options());
    intent.putExtra("description",model.getHotel_description());
    intent.putExtra("hours",model.getHotel_hours());
    intent.putExtra("id",model.getHotel_id());
    intent.putExtra("image",model.getHotel_image());
    intent.putExtra("location",model.getHotel_location());
    intent.putExtra("name",model.getHotel_name());









    startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

 return  view;
    }

    public Context getApplicationContext() {
        Context applicationContext=this.getContext();
        return applicationContext;
    }
    public void getListView(){

    }

    public void requestData(String uri) {

        // StringRequest request = new StringRequest(uri,
        StringRequest stringRequest = new StringRequest(Request.Method.GET, uri,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        //  Toast.makeText(getApplicationContext(), "dfghjkljhgfdddfgh"+response, Toast.LENGTH_LONG).show();
                        if(response!=null||!response.isEmpty()) {

                            //  adapter.notifyDataSetChanged();


                            hotel_model = HotelJsonParser.parseData(response);
                            HotelModelAdapter adapter = new HotelModelAdapter(hotel_model,getContext());
                            adapter.notifyDataSetChanged();



                            mLayoutManager =new LinearLayoutManager(getContext());
                            lv.setLayoutManager(mLayoutManager);
                            lv.setItemAnimator(new DefaultItemAnimator());





                            lv.setAdapter(adapter);
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

//                        Toast.makeText(getActivity(), error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });

        queue = Volley.newRequestQueue(getContext());
        queue.add(stringRequest);
        context=getContext();
    }

}