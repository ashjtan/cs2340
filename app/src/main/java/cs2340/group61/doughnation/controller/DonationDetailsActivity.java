package cs2340.group61.doughnation.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import cs2340.group61.doughnation.R;
import cs2340.group61.doughnation.model.domain.Donation;

@SuppressWarnings("ChainedMethodCall")
public class DonationDetailsActivity extends AppCompatActivity {

    private final List<Donation> donationList = new ArrayList<>();
    private DatabaseReference databaseDonations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_details);

        databaseDonations = FirebaseDatabase.getInstance().getReference("donations");


        //Button to go back to donation recyclerView page
        Button backButton = findViewById(R.id.back_donation_view_button);

        //Button to logout
        Button logoutButton = findViewById(R.id.return_login_Button);

        //Method to go back to LocationDetailActivity
        backButton.setOnClickListener(new View.OnClickListener() {
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


    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseDonations.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                donationList.clear();

                for(DataSnapshot donationSnapshot : dataSnapshot.getChildren()) {
                    Donation donation = donationSnapshot.getValue(Donation.class);

                    donationList.add(donation);
                }
                getIncomingIntent();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    //Get information passed in from RecyclerViewAdapter
    private void getIncomingIntent(){
        //Log.d(TAG, "getIncomingIntent: Checking for incoming intent.");
        if(getIntent().hasExtra("donation_title")) {
            //Log.d(TAG, "getIncomingIntent: Found intent extras");

            String donationTitle = getIntent().getStringExtra("donation_title");

            setDonationDetails(donationTitle);
        }
    }

    //Set details from information passed in from RecyclerViewAdapter
    @SuppressWarnings("FeatureEnvy")
    private void setDonationDetails(String donationTitle) {

        int index = 0;

        for (Donation donation: donationList) {
            if (donation.getTitle().equals(donationTitle)) {
                index = donationList.indexOf(donation);
            }
        }

        //Fetching donation items for found item from database
        Donation selected = donationList.get(index);

        TextView name = findViewById(R.id.location_name);

        TextView time = findViewById(R.id.time_title);

        TextView location = findViewById(R.id.location_title);

        TextView value = findViewById(R.id.value_title);

        TextView type = findViewById(R.id.type_title);

        TextView description = findViewById(R.id.address_description);

        name.setText(selected.getTitle());

        //Strings to setText of TextViews
        String timeString = "TIME: " + selected.getTimestamp();

        String locString = "LOCATION: " + selected.getLocation();

        String valString = "VALUE: $" + selected.getValue();

        String typeString = "TYPE: " + selected.getCategory();

        //Setting TextViews
        time.setText(timeString);

        location.setText(locString);

        value.setText(valString);

        type.setText(typeString);

        description.setText(selected.getFullDescription());



    }
}
