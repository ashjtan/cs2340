package cs2340.group61.doughnation.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import cs2340.group61.doughnation.R;

public class ViewDonationsActivity extends AppCompatActivity {

    private static final String TAG = "ViewDonationsActivity";

    //Temp arrays that hold example titles and descriptions
    //to go in donation recycler view at each location. These
    //should be replaced with database
    private ArrayList<String> donationDesc = new ArrayList<>();
    private ArrayList<String> donationTitles = new ArrayList<>();
    private String locationName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_donations);

        //Button to go back to locationDetailActivity page
        Button backbutton = (Button) findViewById(R.id.back_view_location);

        TextView title = (TextView) findViewById(R.id.donation_location_textView);

        //Button to logout
        Button logoutButton = (Button) findViewById(R.id.return_login_Button);

        //Button to go to AddDonationActivity
        Button addDonationButton = (Button) findViewById(R.id.add_donation_button);

        //Method to go back to LocationDetailActivity
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(ViewDonationsActivity.this,
                        //LocationDetailsActivity.class));
                Intent intent = new Intent(ViewDonationsActivity.this, LocationDetailsActivity.class);
                intent.putExtra("location_name", locationName);
                ViewDonationsActivity.this.startActivity(intent);
            }
        });

        //Method to logout
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewDonationsActivity.this,
                        LoginActivity.class));
            }
        });

        //Method to logout
        addDonationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewDonationsActivity.this,
                        AddDonationActivity.class));
            }
        });

        getIncomingIntent();
        title.setText(locationName);
        initDonationDescriptions();
    }

    //Get information passed in from RecyclerViewAdapter
    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: Checking for incoming intent.");
        if(getIntent().hasExtra("location_name")) {
            Log.d(TAG, "getIncomingIntent: Found intent extras");

            String locationName = getIntent().getStringExtra("location_name");

            this.locationName= locationName;
        }
    }


    //Using this method to fill up my temporary array that holds Donation Descriptions
    //These faux descriptions should be replaced with actual data
    private void initDonationDescriptions() {
        //Method to fill recycler view with details



            initRecyclerView();
        }


    //Method to set up RecyclerView
    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.donations_View);
        DonationViewAdapter adapter = new DonationViewAdapter(this, donationDesc,
                donationTitles);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
