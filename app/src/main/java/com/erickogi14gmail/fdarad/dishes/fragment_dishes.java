package com.erickogi14gmail.fdarad.dishes;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.erickogi14gmail.fdarad.MainActivity;
import com.erickogi14gmail.fdarad.R;
import com.erickogi14gmail.fdarad.order.PlaceOrder;
import com.erickogi14gmail.fdarad.utils.HidingScrollListener;
import com.erickogi14gmail.fdarad.utils.RecyclerTouchListener;

import java.util.ArrayList;

/**
 * Created by kimani kogi on 3/1/2017.
 */

public class fragment_dishes extends Fragment {
    ArrayList<Model> dish_model;
    RecyclerView lv;
    String uri = "http://erickogi.co.ke/fdarad/api/?action=get_dish_by_type&dish_type=";
    ModelAdapter adapter;
    String [] dishType={"","BreakFast","Lunch","Dinner","Supper","vegetarian"};
    static Context context;
    static View view;
   static RequestQueue queue ;
    static RecyclerView.LayoutManager mLayoutManager;
    SwipeRefreshLayout swipe_refresh_layout;
    BottomNavigationView bottomNavigationView;
    public void start(){
       //
      //  dish_model.clear();
       // view=inflater.inflate(R.layout.fragment_dishes,container,false);

        swipe_refresh_layout= (SwipeRefreshLayout)view.findViewById(R.id.swipeContainer);
        swipe_refresh_layout.setRefreshing(true);
       requestData1(uri+dishType[MainActivity.typeOfDishSelected]);
       // this.notifyDataSetChanged();

    }
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        /**
         * Inflate the layout for this fragment
         */
      //  context=getApplicationContext();
       // MainActivity.setDishType(0);

       // swipe_refresh_layout.setRefreshing(true);
        view=inflater.inflate(R.layout.fragment_dishes,container,false);

        swipe_refresh_layout= (SwipeRefreshLayout)view.findViewById(R.id.swipeContainer);
        swipe_refresh_layout.setProgressBackgroundColorSchemeResource(R.color.colorAccent);
        swipe_refresh_layout.setBackgroundResource(android.R.color.white);
        swipe_refresh_layout.setColorSchemeResources(android.R.color.white, android.R.color.holo_purple, android.R.color.white);

        swipe_refresh_layout.setRefreshing(true);

        requestData(uri+dishType[MainActivity.typeOfDishSelected]);
        swipe_refresh_layout.setRefreshing(false);
        lv =(RecyclerView) view.findViewById(R.id.recycle_view);



        getListView();


        swipe_refresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe_refresh_layout.setRefreshing(true);
                requestData(uri+dishType[MainActivity.typeOfDishSelected]);
               // swipe_refresh_layout.setRefreshing(false);
            }
        });

        lv.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), lv, new RecyclerTouchListener.ClickListener() {

            @Override
            public void onClick(View view, int position) {
                Model model = dish_model.get(position);

                Intent intent=new Intent(getActivity(), PlaceOrder.class);

                intent.putExtra("dish_name",model.getDish_name());
                intent.putExtra("dish_description",model.getDish_description());
                intent.putExtra("dish_image",model.getDish_image());
                intent.putExtra("dish_price",model.getDish_price());
                intent.putExtra("dish_id",model.getDish_id());
                intent.putExtra("dish_rating",model.getDish_ratings());

                startActivity(intent);





                Toast.makeText(getApplicationContext(), model.getDish_name() + " is selected!", Toast.LENGTH_SHORT).show();





            }

            @Override
            public void onLongClick(View view, int position) {
               // Model model = dish_model.get(position);
              //  Toast.makeText(getApplicationContext(), model.getDish_name() + " is long selected!", Toast.LENGTH_SHORT).show();
            }
        }));

      lv.setOnScrollListener(new HidingScrollListener() {
            @Override
            public void onHide() {
                hideViews();
            }
            @Override
            public void onShow() {
                showViews();
            }
        });
       // View view2 = getActivity().findViewById(R.id.viewid);

       bottomNavigationView = (BottomNavigationView)

                getActivity().findViewById(R.id.bottom_navigation);


       return  view;
    }

    private void hideViews() {
        bottomNavigationView.animate().translationY(bottomNavigationView.getHeight()).setInterpolator(new AccelerateInterpolator(2));

       // FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mFabButton.getLayoutParams();
      //  int fabBottomMargin = lp.bottomMargin;
       // mFabButton.animate().translationY(mFabButton.getHeight()+fabBottomMargin).setInterpolator(new AccelerateInterpolator(2)).start();
    }

    private void showViews() {
        bottomNavigationView.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
      //  mFabButton.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
    }





  //  RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
  public void requestData(String uri) {

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
                              ModelAdapter adapter = new ModelAdapter(dish_model, getContext());
                              adapter.notifyDataSetChanged();


                              mLayoutManager = new LinearLayoutManager(getContext());
                              lv.setLayoutManager(mLayoutManager);
                              lv.setItemAnimator(new DefaultItemAnimator());


                              lv.setAdapter(adapter);
                              swipe_refresh_layout.setRefreshing(false);
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
                          Toast.makeText(getActivity(),"Network Error", Toast.LENGTH_SHORT).show();
                      } catch (Exception v) {

                      }
                  }
              });

      queue = Volley.newRequestQueue(getContext());
      queue.add(stringRequest);
      context = getContext();
  }
    public  Context getApplicationContext() {
        Context applicationContext=getContext();
        context=applicationContext;       return applicationContext;
    }
    public void getListView(){

    }




    public void requestData1(String uri) {

        // StringRequest request = new StringRequest(uri,
        StringRequest stringRequest1 = new StringRequest(Request.Method.GET, uri,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        //  Toast.makeText(getApplicationContext(), "dfghjkljhgfdddfgh"+response, Toast.LENGTH_LONG).show();
                        if(response!=null||!response.isEmpty()) {

                            //  adapter.notifyDataSetChanged();
                            dish_model = DishJsonParser.parseData(response);
                             adapter = new ModelAdapter( dish_model,getContext());
                            if(dish_model.isEmpty()){
                                Toast.makeText(context, "No such dishes", Toast.LENGTH_SHORT).show();
                            }
                           // else {
                                adapter.notifyDataSetChanged();


                                mLayoutManager = new LinearLayoutManager(getContext());
                                lv = (RecyclerView) view.findViewById(R.id.recycle_view);
                                lv.setLayoutManager(mLayoutManager);
                                lv.setItemAnimator(new DefaultItemAnimator());


                                lv.setAdapter(adapter);
                            swipe_refresh_layout.setRefreshing(true);
                           // }
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "nul", Toast.LENGTH_LONG).show();
                            swipe_refresh_layout.setRefreshing(false);
                        }

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
try{
    Toast.makeText(getActivity(), error.getMessage().toString(), Toast.LENGTH_SHORT).show();
    swipe_refresh_layout.setRefreshing(false);
}
catch (Exception n){
    swipe_refresh_layout.setRefreshing(false);
}

                    }
                });

        queue.add(stringRequest1);
    }
}