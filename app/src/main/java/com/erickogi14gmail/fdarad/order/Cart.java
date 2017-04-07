package com.erickogi14gmail.fdarad.order;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.erickogi14gmail.fdarad.R;

import java.util.ArrayList;
import java.util.List;

public class Cart extends AppCompatActivity {
    static RecyclerView.LayoutManager mLayoutManager;
    RecyclerView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);











        lv=(RecyclerView)findViewById(R.id.recycle_view_cart);


        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
            }
        });*/


        addItems();
        ModelAdapterCart adapter = new ModelAdapterCart(moList, getApplicationContext());
        adapter.notifyDataSetChanged();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
       // mLayoutManager = new LinearLayoutManager(getApplicationContext());
        lv.setLayoutManager(mLayoutManager);
        lv.setItemAnimator(new DefaultItemAnimator());


        lv.setAdapter(adapter);




    }
    private List<ModelCart> moList = new ArrayList<>();
    public  void addItems(){
        ModelCart items = new ModelCart(1,"Chapo Beef","Quality,tasty food","",600.0,2);
        moList.add(items);
        items = new ModelCart(2,"Pizza","Delicious pizza","",600.0,1);
        moList.add(items);
        items = new ModelCart(3,"Ommelette","Super delicious tasty ommellete","",200.0,2);
        moList.add(items);

        items = new ModelCart(1,"Hambuger","Delicious humberger","",300.0,1);
        moList.add(items);




    }
}
