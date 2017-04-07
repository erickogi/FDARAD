package com.erickogi14gmail.fdarad;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.erickogi14gmail.fdarad.AppSettings.LocationSettings;
import com.erickogi14gmail.fdarad.LoginRegister.Config;
import com.erickogi14gmail.fdarad.LoginRegister.Login;
import com.erickogi14gmail.fdarad.LoginRegister.Register;
import com.erickogi14gmail.fdarad.dishes.Model;
import com.erickogi14gmail.fdarad.dishes.ModelAdapter;
import com.erickogi14gmail.fdarad.dishes.fragment_dishes;
import com.erickogi14gmail.fdarad.hotels.fragment_hotels;
import com.erickogi14gmail.fdarad.order.Cart;

import java.util.ArrayList;
import java.util.List;
import android.support.v7.widget.SearchView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
ImageView img;
    ListView listView;
    ModelAdapter adapter;
    ArrayList<Model> modelArrayList;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
  int []  imageId={
          R.drawable.ic_restaurant_menu_black_24dp,
          R.drawable.ic_hotel_black_24dp,
          R.drawable.ic_local_offer_black_24dp
  };
SearchView searchView;
    static RequestQueue queue ;

    String JsonURL = "https://raw.githubusercontent.com/ianbar20/JSON-Volley-Tutorial/master/Example-JSON-Files/Example-Object.JSON";
    // This string will hold the results
    String data = "";
    // Defining the Volley request queue that handles the URL request concurrently
    RequestQueue requestQueue;


    private boolean loggedIn = false;
    public  static  int typeOfDishSelected;



    public static void setDishType(int selection){
        typeOfDishSelected=selection;
    }


public  Context getc() {
//    Context applicationContext=this.getApplicationContext();
    return getApplicationContext();
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME,Context.MODE_PRIVATE);

        //Fetching the boolean value form sharedpreferences
        loggedIn = sharedPreferences.getBoolean(Config.LOGGEDIN_SHARED_PREF, false);
        Log.d("loginStatus",String.valueOf(loggedIn));
        //If we will get true
        if(!loggedIn){
            //We will start the Profile Activity
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
            finish();
        }



        setContentView(R.layout.activity_main);
        setDishType(0);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(viewPager);

        setupTabIcons();



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        BottomNavigationView bottomNavigationView = (BottomNavigationView)

                findViewById(R.id.bottom_navigation);



        bottomNavigationView.setOnNavigationItemSelectedListener(
//bottomNavigationView.setBehaviorTranslationEnabled(true);

                new BottomNavigationView.OnNavigationItemSelectedListener() {

                    @Override

                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        switch (item.getItemId()) {

                            case R.id.action_menus:
                                //final int a=bottomNavigationView.getHeight();

                                Toast.makeText(MainActivity.this, "menu", Toast.LENGTH_SHORT).show();
                            break;

                            case R.id.action_cart:

                                startActivity(new Intent(MainActivity.this,Cart.class));
                            break;

                            case R.id.action_track:

                                Toast.makeText(MainActivity.this, "track not yet implemented", Toast.LENGTH_SHORT).show();break;

                            case R.id.action_history:

                                Toast.makeText(MainActivity.this, "history not yet implemented", Toast.LENGTH_SHORT).show();
                                break;

                        }




                        return true;

                    }

                });


         searchView = (SearchView) findViewById(R.id.search_bar);
       // searchAutoComplete.set
       searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Toast like print
                Toast.makeText(MainActivity.this, ""+query, Toast.LENGTH_SHORT).show();
                //  UserFeedback.show( "SearchOnQueryTextSubmit: " + query);

                if( ! searchView.isIconified()) {
                    searchView.setIconified(true);
                }
              //  myActionMenuItem.collapseActionView();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                Toast.makeText(MainActivity.this, ""+s, Toast.LENGTH_SHORT).show();
                // UserFeedback.show( "SearchOnQueryTextChanged: " + s);
                return false;
            }
        });
    }
    private void setupTabIcons() {

        tabLayout.getTabAt(0).setText("Dishes");
        tabLayout.getTabAt(1).setText("Restaurants");
        tabLayout.getTabAt(2).setText("Deals");

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new fragment_dishes(), "dishes");
        adapter.addFragment(new fragment_hotels(), "TWO");
       adapter.addFragment(new fragment_deals(), "THREE");
viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(adapter);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.main, menu);

      //  final MenuItem myActionMenuItem = menu.findItem( R.id.action_search);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {


         Intent intent=new Intent(MainActivity.this, LocationSettings.class);
           // intent.putStringArrayListExtra("A",requestData("http://erickogi.co.ke/fdarad/api/?action=get_country_locations_by_id","country_name"));
            startActivity(intent);


            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        fragment_dishes fragment_ddishes=new fragment_dishes();
        if (id == R.id.nav_all) {
            setDishType(0);
            fragment_ddishes.start();
            //fragment_dishes g=new
        } else if (id == R.id.nav_breakFast) {
            setDishType(1);

          //  fragment_dishes fragment_ddishes=(fragment_dishes)getSupportFragmentManager().findFragmentById(R.id.);
            fragment_ddishes.start();
        } else if (id == R.id.nav_lunch) {
            setDishType(2);
            fragment_ddishes.start();
        } else if (id == R.id.nav_Super) {
            setDishType(3);
            fragment_ddishes.start();
        } else if (id == R.id.nav_dinner) {
            setDishType(4);
            fragment_ddishes.start();

        } else if (id == R.id.nav_drinks) {

        }else if (id == R.id.nav_vegetarian) {

        }else if (id == R.id.nav_login) {
            startActivity(new Intent(MainActivity.this, Login.class));
        }else if (id == R.id.nav_logout) {
            startActivity(new Intent(MainActivity.this, Register.class));
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }
    }


}
