package com.erickogi14gmail.fdarad;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.erickogi14gmail.fdarad.dishes.Model;
import com.erickogi14gmail.fdarad.dishes.ModelAdapter;

import java.util.ArrayList;

/**
 * Created by kimani kogi on 3/1/2017.
 */

public class fragment_deals extends Fragment {
    ImageView img;
    ListView listView;
    ModelAdapter adapter;
    ArrayList<Model> modelArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        /**
         * Inflate the layout for this fragment
         */
        View view=inflater.inflate(R.layout.fragment_dishes,container,false);
       /* listView =(ListView)view.findViewById(R.id.list_view);
        modelArrayList = new ArrayList<Model>();




        //  ArrayList<Model> modelList = modellist1;
        ModelAdapter adapter=new ModelAdapter(modelArrayList,getApplicationContext());
        listView.setAdapter(adapter);
        getListView();*/
        return  view;
    }

    public Context getApplicationContext() {
        Context applicationContext=this.getContext();
        return applicationContext;
    }
    public void getListView(){

    }
}