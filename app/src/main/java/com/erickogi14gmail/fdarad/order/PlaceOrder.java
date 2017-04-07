package com.erickogi14gmail.fdarad.order;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
import com.erickogi14gmail.fdarad.LoginRegister.Config;
import com.erickogi14gmail.fdarad.R;
import com.erickogi14gmail.fdarad.mPicasso.PicassoClient;

import java.util.HashMap;
import java.util.Map;

public class PlaceOrder extends AppCompatActivity {
TextView _txtDescription,_txtPrice,_txtQuantity,_txtTotalPrice;
    RatingBar _rating ;
    Button _addQ,_removeQ;
    Button _addToCart;
    int quantity=1;
    Double price;
    String dish_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Intent intent=getIntent();

        dish_name= intent.getStringExtra("dish_name");
        toolbar.setTitle(dish_name);

        toolbar.setTitleTextColor(0xFFFFFFFF);
        setSupportActionBar(toolbar);
_txtDescription=(TextView)findViewById(R.id.txt_dish_description) ;
        _addToCart=(Button)findViewById(R.id.btn_order) ;
        _txtPrice=(TextView)findViewById(R.id.dish_price);
        _rating=(RatingBar)findViewById(R.id.dish_rating);
        _addQ=(Button)findViewById(R.id.btn_add);
        _removeQ=(Button)findViewById(R.id.btn_remove) ;

        _txtQuantity=(TextView)findViewById(R.id.total_quantity) ;
        _txtTotalPrice=(TextView)findViewById(R.id.total_price) ;



       // intent.getStringExtra("dish_image");
      price=   intent.getDoubleExtra("dish_price",0.0);
       // intent.getFloatExtra("dish_rating",0);
        intent.getIntExtra("dish_id",1);

        _rating.setRating(intent.getFloatExtra("dish_rating",0));
       _txtPrice.setText(String.valueOf( price)+" Ksh");

        _txtTotalPrice.setText(String.valueOf( price)+" Ksh");
        _txtQuantity.setText("1 ");






         _addQ.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 add();
             }
         });
        _removeQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove();
            }
        });
        _addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // SharedPreferences sharedPreferences =PlaceOrder.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
              //  sharedPreferences.getString(Config.EMAIL_SHARED_PREF,"null");
                Snackbar.make(v, "ADD TO CART", Snackbar.LENGTH_LONG)
                       .setAction("Action", null).show();


            }
        });
//
//
//
//
//        final BottomNavigationView bottomNavigationView = (BottomNavigationView)
//
//                findViewById(R.id.bottom_navigation);
//        bottomNavigationView.getMenu().getItem(1).setChecked(true);
//        bottomNavigationView.getMenu().getItem(0).setChecked(false);
//
//        bottomNavigationView.setOnNavigationItemSelectedListener(
////bottomNavigationView.setBehaviorTranslationEnabled(true);
//
//                new BottomNavigationView.OnNavigationItemSelectedListener() {
//
//                    @Override
//
//                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//                        switch (item.getItemId()) {
//
//                            case R.id.action_menus:
//
//                                     goBack();
//
//                                //final int a=bottomNavigationView.getHeight();
//
//                                Toast.makeText(PlaceOrder.this, "menu", Toast.LENGTH_SHORT).show();
//                                break;
//
//                            case R.id.action_cart:
//
//                                Toast.makeText(PlaceOrder.this, "cart", Toast.LENGTH_SHORT).show();
//                                break;
//
//                            case R.id.action_track:
//
//                                Toast.makeText(PlaceOrder.this, "track", Toast.LENGTH_SHORT).show();break;
//
//                            case R.id.action_history:
//
//                                Toast.makeText(PlaceOrder.this, "history", Toast.LENGTH_SHORT).show();
//                                break;
//
//                        }
//
//
//
//
//                        return true;
//
//                    }
//
//                });
        CollapsingToolbarLayout collapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.toolbar_layout) ;


























        ImageView img=new ImageView(this);
        PicassoClient.LoadImage (this,intent.getStringExtra("dish_image"),img);
        //ct.setBackground(img.getDrawable());
        collapsingToolbarLayout.setBackground(img.getDrawable());

    }



    private void add() {
        quantity++;


        _txtPrice.setText(String.valueOf( price)+" Ksh");

        _txtTotalPrice.setText(String.valueOf( price*quantity)+" Ksh");
        _txtQuantity.setText(String.valueOf(quantity));




    }
    private void remove() {
if(quantity<2){

} else {
    quantity--;


    _txtPrice.setText(String.valueOf(price) + " Ksh");

    _txtTotalPrice.setText(String.valueOf(price * quantity) + " Ksh");
    _txtQuantity.setText(String.valueOf(quantity));
}
    }

    //    @Override
//    public void onBackPressed() {
//        goBack();
//    }
    void goBack(){

    }









/*    private boolean registerUser(){
        boolean success=false;






        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        if(response.equals("Successfully Registered")){
                           // startActivity(new Intent(Register.this,Login.class));
                           // finish();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(PlaceOrder.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(KEY_USERNAME,name);
                params.put(KEY_PASSWORD,password);
                params.put(KEY_EMAIL, email);
                params.put(KEY_RAND, user_key);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        return success;
    }*/







}
