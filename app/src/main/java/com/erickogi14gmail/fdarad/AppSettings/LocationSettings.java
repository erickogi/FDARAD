package com.erickogi14gmail.fdarad.AppSettings;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
//import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
//import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.erickogi14gmail.fdarad.MainActivity;
import com.erickogi14gmail.fdarad.R;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;
import android.widget.AdapterView.OnItemSelectedListener;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LocationSettings extends AppCompatActivity implements AdapterView.OnItemSelectedListener {



    String [] uri={
            "http://erickogi.co.ke/fdarad/api/?action=get_country_locations_by_id",
            "http://erickogi.co.ke/fdarad/api/?action=get_state_locations_by_country_name&country_name=",
            "http://erickogi.co.ke/fdarad/api/?action=get_town_locations_by_state_name&state_name="
    };


    static Context context;
    static View view;
    static RequestQueue queue ;
    ArrayAdapter<String> arrayAdapterc;
    MaterialBetterSpinner materialDesignSpinnerc;
    boolean success=false;
    int b=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ArrayList<String> B= new ArrayList<>();
       B.add("Kenya");
        B.add("UGANDA");
        B.add("TANZANIA");

        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_location_settings);





        arrayAdapterc = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, B );

        MaterialBetterSpinner materialDesignSpinner = (MaterialBetterSpinner)
                findViewById(R.id.android_material_design_spinner_country);
        materialDesignSpinner.setAdapter(arrayAdapterc);





        arrayAdapterc  = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,requestData(uri[1] , "state_name"));
        MaterialBetterSpinner materialDesignSpinnerc = (MaterialBetterSpinner)
                findViewById(R.id.android_material_design_spinner_county);
        materialDesignSpinnerc.setAdapter(arrayAdapterc);


        arrayAdapterc = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, requestData(uri[2] , "town_name"));
        MaterialBetterSpinner materialDesignSpinnert = (MaterialBetterSpinner)
                findViewById(R.id.android_material_design_spinner_town);
        materialDesignSpinnert.setAdapter(arrayAdapterc);







        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);







        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Saved", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }




    public ArrayList<String>  requestData(final String uri , final String w) {
final ArrayList<String> items=new ArrayList<>();
        // StringRequest request = new StringRequest(uri,
        StringRequest stringRequest = new StringRequest(Request.Method.GET, uri,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONArray dish_arry = null;
                        Log.d("json",response+""+uri);
                      //  Toast.makeText(getApplicationContext(), "dfghjkljhgfdddfgh"+response, Toast.LENGTH_LONG).show();
                        if(response!=null||!response.isEmpty()) {
                            JSONObject jObj = null;
                            try {
                                jObj = new JSONObject(response);
                                dish_arry = jObj.getJSONArray("data");

                                for (int i = 0; i < dish_arry.length(); i++) {

                                    JSONObject obj = dish_arry.getJSONObject(i);
                                    Log.d("aa0",String.valueOf(b));


                                        items.add(obj.getString(w)) ;


                                }
                               /* arrayAdapterc = new ArrayAdapter<String>(getApplicationContext(),
                                        android.R.layout.simple_dropdown_item_1line, CountryLIST);

                                if (CountryLIST.isEmpty()) {
                                    Log.d("empty", "is em");
                                }
                                MaterialBetterSpinner materialDesignSpinner = (MaterialBetterSpinner)
                                        findViewById(R.id.android_material_design_spinner_country);
                                materialDesignSpinner.setAdapter(arrayAdapterc);*/
                              success=true;


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            //  adapter.notifyDataSetChanged();
                            //hotel_model = HotelJsonParser.parseData(response);



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

        return items;
    }
String c="";

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
 /*       edtCountry=(EditText)findViewById(R.id.input_country);
edtCountry.setText(adapterView.getItemAtPosition(i).toString());*/
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public class CustomOnItemSelectedListener implements OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
          c=parent.getItemAtPosition(pos).toString();
           // edtCountry=(EditText)findViewById(R.id.input_country);
           // edtCountry.setText(parent.getItemAtPosition(pos).toString());
                          Toast.makeText(getApplicationContext(),
                    "OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
                    Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub
        }
    }
}
