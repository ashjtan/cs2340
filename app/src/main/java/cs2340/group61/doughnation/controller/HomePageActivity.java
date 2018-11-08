package cs2340.group61.doughnation.controller;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import cs2340.group61.doughnation.R;

public class HomePageActivity extends AppCompatActivity {

    private ActionBar actionbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        //Setting some actionbar characteristics
        //Changes color and text in the actionbar
        actionbar = getSupportActionBar();
        actionbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF4A4A")));
        actionbar.setTitle("DoughNation");

        //Instantiating toLogin button to return to login from Reg. page
        Button toLogin = (Button) findViewById(R.id.return_login_Button);

        //Instantiating myLocations button to go to Locations RecyclerView page
        Button myLocations = (Button) findViewById(R.id.locationsButton);

        //Instantiating toAddDonation button to go to add donations page
        Button toAddDonations = (Button) findViewById(R.id.add_donation_button);

        //Instantiating toSearchDonations button to go to view donations page
        Button toSearchDonations = (Button) findViewById(R.id.search_donations_button);

        //Instantiating toMapView button to go to MapViewActivity
        Button toMapView = (Button) findViewById(R.id.map_view_button);

        //Method to return to main login screen
        toLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePageActivity.this, LoginActivity.class));
            }
        });

        //Method to go to Recycler View page containing list of locations
        myLocations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePageActivity.this,
                        ViewLocationsActivity.class));
            }
        });

        //Method to go to Add Donation Activity
        toAddDonations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePageActivity.this,
                        AddDonationActivity.class));
            }
        });

        //Method to go to Search Donations Activity
        toSearchDonations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePageActivity.this,
                        ViewDonationsActivity.class));
            }
        });

        //Method to go to Map View Activity
        toMapView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePageActivity.this,
                        MapViewActivity.class));
            }
        });
    }

}
