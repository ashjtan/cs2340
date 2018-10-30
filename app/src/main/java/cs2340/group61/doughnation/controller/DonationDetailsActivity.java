package cs2340.group61.doughnation.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cs2340.group61.doughnation.R;

public class DonationDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_details);

        //Button to go back to donation recyclerview page
        Button backbutton = (Button) findViewById(R.id.back_donation_view_button);

        //Button to logout
        Button logoutButton = (Button) findViewById(R.id.return_login_Button);

        //Method to go back to LocationDetailActivity
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DonationDetailsActivity.this,
                        ViewDonationsActivity.class));
            }
        });

        //Method to logout
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DonationDetailsActivity.this,
                        LoginActivity.class));
            }
        });

        getIncomingIntent();
    }

    //Get information passed in from RecyclerViewAdapter
    private void getIncomingIntent(){
        //Log.d(TAG, "getIncomingIntent: Checking for incoming intent.");
        if(getIntent().hasExtra("donation_title")) {
            //Log.d(TAG, "getIncomingIntent: Found intent extras");

            String donationtitle = getIntent().getStringExtra("donation_title");

            setDonationDetails(donationtitle);
        }
    }

    //Set details from information passed in from RecyclerViewAdapter
    private void setDonationDetails(String donationTitle) {
        //Log.d(TAG, "setDonationDetails: Setting Donation name and details");




    }
}
