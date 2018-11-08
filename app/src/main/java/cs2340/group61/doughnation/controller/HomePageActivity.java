package cs2340.group61.doughnation.controller;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import cs2340.group61.doughnation.R;

public class HomePageActivity extends AppCompatActivity {

    private ActionBar actionbar;
    private Button toLogin;
    private Button myLocations;
    private Button toAddDonations;
    private Button toSearchDonations;
    private Button toMapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        //Setting some actionbar characteristics
        //Changes color and text in the actionbar
        actionbar = getSupportActionBar();
        actionbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF4A4A")));
        actionbar.setTitle("DoughNation");

        toLogin = findViewById(R.id.btnLogout);
        myLocations = findViewById(R.id.btnLocations);
        toAddDonations = findViewById(R.id.btnAddDonations);
        toSearchDonations = findViewById(R.id.btnSearchDonations);
        toMapView = findViewById(R.id.btnLocationsMap);
    }

    //EVENT HANDLERS
    public void switchToLogin(View v) {             //switches to login page
        startActivity(new Intent(HomePageActivity.this, LoginActivity.class));
    }

    public void switchToMyLocations(View v) {      //switches to my locations page
        startActivity(new Intent(HomePageActivity.this, ViewLocationsActivity.class));
    }

    public void switchToAddDonation(View v) {      //switches to add donation page
        startActivity(new Intent(HomePageActivity.this, AddDonationActivity.class));
    }

    public void switchToSearchDonations(View v) {  //switches to search donations page
        startActivity(new Intent(HomePageActivity.this, ViewDonationsActivity.class));
    }

    public void switchToLocationsMap(View v) {      //switches to locations map
        startActivity(new Intent(HomePageActivity.this, MapViewActivity.class));
    }
}
