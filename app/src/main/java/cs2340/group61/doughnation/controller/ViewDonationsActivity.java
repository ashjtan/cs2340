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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_donations);

        //Button to go back to locationDetailActivity page
        Button backbutton = (Button) findViewById(R.id.back_view_location);

        //Button to logout
        Button logoutButton = (Button) findViewById(R.id.return_login_Button);

        //Method to go back to LocationDetailActivity
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewDonationsActivity.this,
                        HomePageActivity.class));
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

        donationTitles.add("Teddy Bear 12");
        donationTitles.add("Soup Can 78");
        donationTitles.add("Mom's left shoe 7");
        donationTitles.add("My old cat");
        donationTitles.add("My new cat");
        donationTitles.add("Hairbrush");
        donationTitles.add("Ugly doll");
        donationTitles.add("Possessed doll");
        donationTitles.add("A sock");
        donationTitles.add("Space heater");
        donationTitles.add("Tire iron");
        donationTitles.add("Suspicious briefcase");
        donationTitles.add("Business-y briefcase");
        donationTitles.add("Raisin");

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
