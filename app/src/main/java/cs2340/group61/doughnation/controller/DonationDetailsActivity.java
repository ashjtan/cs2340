//This class is to display the details of the donation when it's clicked.
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

public class DonationDetailsActivity extends AppCompatActivity {

    private final List<Donation> donationList = new ArrayList<>();
    private DatabaseReference databaseDonations;

    /**
     * This method is to create the page of the donation details.
     * @param savedInstanceState The object's previously saved state.
     */
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
            /**
             * This is a method to log out
             * @param v The page
             */
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DonationDetailsActivity.this,
                        LoginActivity.class));
            }
        });
    }

    /**
     * This method displays the page to the user.
     */
    @Override
    protected void onStart() {
        super.onStart();

        databaseDonations.addValueEventListener(new ValueEventListener() {

            /**
             * This method is to change data.
             * @param dataSnapshot The data.
             */
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                donationList.clear();

                for(DataSnapshot donationSnapshot : dataSnapshot.getChildren()) {
                    Donation donation = donationSnapshot.getValue(Donation.class);

                    donationList.add(donation);
                }
                getIncomingIntent();
            }

            /**
             * This method is required to be implemented but is unused.
             * @param databaseError error from database.
             */
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    /**
     * This method is to get information passed in from RecyclerViewAdapter
     */
    private void getIncomingIntent(){
        //Log.d(TAG, "getIncomingIntent: Checking for incoming intent.");
        if(getIntent().hasExtra("donation_title")) {
            //Log.d(TAG, "getIncomingIntent: Found intent extras");

            String donationTitle = getIntent().getStringExtra("donation_title");

            setDonationDetails(donationTitle);
        }
    }

    /**
     * This method is to set details from information passed in from RecyclerViewAdapter
     * @param donationTitle The name of the donation.
     */
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

        description.setText(selected.getFulldescription());



    }
}
