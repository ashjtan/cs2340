package cs2340.group61.doughnation.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import cs2340.group61.doughnation.R;

public class ViewDonationsActivity extends AppCompatActivity {

    private static final String TAG = "ViewDonationsActivity";

    private ArrayList<String> donationDesc = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_donations);

        //Button to go back to locationDetailActivity page
        Button backbutton = (Button) findViewById(R.id.back_view_location);

        //Button to logout
        Button logoutButton = (Button) findViewById(R.id.return_login_Button);

        //Button to go to AddDonationActivity
        Button addDonationButton = (Button) findViewById(R.id.add_donation_button);

        //Method to go back to LocationDetailActivity
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewDonationsActivity.this,
                        LocationDetailsActivity.class));
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

        initDonationDescriptions();
    }

    //Using this method to fill up my temporary array that holds Donation Descriptions
    //These faux descriptions should be replaced with actual data
    private void initDonationDescriptions() {
        donationDesc.add("18:00 - Here is a short excerpt of a description of...");
        donationDesc.add("15:00 - Here is a short excerpt of a description of...");
        donationDesc.add("14:00 - Here is a short excerpt of a description of...");
        donationDesc.add("13:00 - Here is a short excerpt of a description of...");
        donationDesc.add("12:00 - Here is a short excerpt of a description of...");
        donationDesc.add("20:00 - Here is a short excerpt of a description of...");
        donationDesc.add("08:00 - Here is a short excerpt of a description of...");
        donationDesc.add("04:00 - Here is a short excerpt of a description of...");
        donationDesc.add("03:00 - Here is a short excerpt of a description of...");
        donationDesc.add("18:00 - Here is a short excerpt of a description of...");
        donationDesc.add("15:00 - Here is a short excerpt of a description of...");
        donationDesc.add("14:00 - Here is a short excerpt of a description of...");
        donationDesc.add("13:00 - Here is a short excerpt of a description of...");
        donationDesc.add("12:00 - Here is a short excerpt of a description of...");

        initRecyclerView();
    }

    //Method to set up RecyclerView
    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.donations_View);
        DonationViewAdapter adapter = new DonationViewAdapter(this, donationDesc);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
