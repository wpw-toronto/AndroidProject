package com.lk.wpw_android_project;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.lk.wpw_android_project.Model.FoodTruckList;
import com.squareup.picasso.Picasso;


public class FoodTruck extends AppCompatActivity {

    ListView listView;
    FirebaseListAdapter adapter;

    Toolbar toolbar;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_truck);

        // get the list of stories titles and contents in string array
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView = findViewById(R.id.navi_food_truck);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.home){
                    Intent home = new Intent(FoodTruck.this, Home.class);
                    startActivity(home);
                } else if(id == R.id.mapEvent){
                    Intent mapEvent = new Intent(FoodTruck.this, MapEvent.class);
                    startActivity(mapEvent);
                } else if(id == R.id.mapRoute){
                    Intent mapRoute = new Intent(FoodTruck.this, MapRoute.class);
                    startActivity(mapRoute);
                } else if(id == R.id.scheduleEvent){
                    Intent scheduleEvent = new Intent(FoodTruck.this, ScheduleEvent.class);
                    startActivity(scheduleEvent);
                } else if(id == R.id.schedulePerformance){
                    Intent schedulePerformance = new Intent(FoodTruck.this, SchedulePerformance.class);
                    startActivity(schedulePerformance);
                } else if(id == R.id.foodTruck){
                    drawer.closeDrawers();
                } else if(id == R.id.donate){
                    Intent donate = new Intent(FoodTruck.this, Donate.class);
                    startActivity(donate);
                } else if(id == R.id.aboutUs){
                    Intent aboutUs = new Intent(FoodTruck.this, AboutUs.class);
                    startActivity(aboutUs);
                } else if(id == R.id.signOut){
                    logout();
                }
                return true;
            }
        });


        drawer = findViewById(R.id.drawer_food_truck);
        toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.open,R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

        listView = (ListView)findViewById(R.id.listViewFoodTruck);

        Query query = FirebaseDatabase.getInstance().getReference().child("Foodtruck");

        FirebaseListOptions<FoodTruckList> options = new FirebaseListOptions.Builder<FoodTruckList>()
                .setLayout(R.layout.list_food_truck)
                .setQuery(query,FoodTruckList.class)
                .build();

        adapter = new FirebaseListAdapter(options) {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            protected void populateView(View v, Object model, final int position) {
                final FoodTruckList foodTruckList = (FoodTruckList) model;
                ImageView imageView = v.findViewById(R.id.foodTruckImage);
                TextView title= v.findViewById(R.id.foodTruckTitle);
                TextView subTitle= v.findViewById(R.id.foodTruckSubTitle);
                TextView description= v.findViewById(R.id.foodTruckDescription);

                title.setText(foodTruckList.getTitle());
                subTitle.setText(foodTruckList.getSubTitle());
                description.setText(foodTruckList.getDescription());
                Picasso.get().load(foodTruckList.getImageUrl()).into(imageView);
            }
        };

        listView.setAdapter(adapter);

    }

    // Hide Navigation Drawer on Pause
    @Override
    protected void onPause() {
        drawer.closeDrawers();
        super.onPause();
    }


    private void logout() {
        FirebaseAuth.getInstance().signOut();

        Intent signOut = new Intent(FoodTruck.this, MainDisplay.class);
        signOut.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(signOut);
    }

    public void onStart(){
        super.onStart();
        adapter.startListening();
    }

    public void onStop(){
        super.onStop();
        adapter.stopListening();
    }

}
