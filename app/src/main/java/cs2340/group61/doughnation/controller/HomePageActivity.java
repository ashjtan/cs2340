//This is a class to create and display the home page of the app.
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

    /**
     * This is a method to create the page.
     * @param savedInstanceState The previously saved state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        //Setting some actionbar characteristics
        //Changes color and text in the actionbar
        ActionBar actionbar = getSupportActionBar();
        actionbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF4A4A")));
        actionbar.setTitle("DoughNation");

        //Instantiating toLogin button to return to login from Reg. page
        Button toLogin = findViewById(R.id.return_login_Button);

        //Instantiating myLocations button to go to Locations RecyclerView page
        Button myLocations = findViewById(R.id.locationsButton);

        //Instantiating toAddDonation button to go to add donations page
        Button toAddDonations = findViewById(R.id.add_donation_button);

        //Instantiating toSearchDonations button to go to view donations page
        Button toSearchDonations = findViewById(R.id.search_donations_button);

        //Instantiating toMapView button to go to MapViewActivity
        Button toMapView = findViewById(R.id.map_view_button);

        //Method to return to main login screen
        toLogin.setOnClickListener(new View.OnClickListener() {
            /**
             * This is a method to return to main login screen.
             * @param v The page.
             */
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePageActivity.this, LoginActivity.class));
            }
        });

        //Method to go to Recycler View page containing list of locations
        myLocations.setOnClickListener(new View.OnClickListener() {
            /**
             * This is a method to go to Recycler View page containing list of locations.
             * @param v The page.
             */
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePageActivity.this,
                        ViewLocationsActivity.class));
            }
        });

        //Method to go to Add Donation Activity
        toAddDonations.setOnClickListener(new View.OnClickListener() {
            /**
             * The method to go to add donation activity.
             * @param v The page.
             */
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePageActivity.this,
                        AddDonationActivity.class));
            }
        });

        //Method to go to Search Donations Activity
        toSearchDonations.setOnClickListener(new View.OnClickListener() {
            /**
             * This method is to go to Search Donations Activity.
             * @param v The page.
             */
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePageActivity.this,
                        ViewDonationsActivity.class));
            }
        });

        //Method to go to Map View Activity
        toMapView.setOnClickListener(new View.OnClickListener() {
            /**
             * This is a method to go to Map View Activity.
             * @param v The page.
             */
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePageActivity.this,
                        MapViewActivity.class));
            }
        });
    }

}
