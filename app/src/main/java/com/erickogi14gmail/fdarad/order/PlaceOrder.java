package com.erickogi14gmail.fdarad.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.erickogi14gmail.fdarad.R;
import com.erickogi14gmail.fdarad.mPicasso.PicassoClient;

public class PlaceOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_order);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Intent intent=getIntent();
        toolbar.setTitle( intent.getStringExtra("dish_name"));
        toolbar.setTitleTextColor(0xFFFFFFFF);
        setSupportActionBar(toolbar);


        CollapsingToolbarLayout collapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.toolbar_layout) ;






        intent.getStringExtra("dish_image");
        intent.getDoubleExtra("dish_price",0.0);




        ImageView img=new ImageView(this);
        PicassoClient.LoadImage (this,intent.getStringExtra("dish_image"),img);
        //ct.setBackground(img.getDrawable());
        collapsingToolbarLayout.setBackground(img.getDrawable());

        TextView textView_description=(TextView) findViewById(R.id.dish_description);
        textView_description.setText(intent.getStringExtra("dish_description"));


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
