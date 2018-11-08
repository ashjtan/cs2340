//This class is to display the details of the donation when it's clicked.
package cs2340.group61.doughnation.controller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import cs2340.group61.doughnation.controller.ViewDonationsActivity;

import cs2340.group61.doughnation.R;
import cs2340.group61.doughnation.model.domain.Donation;
import java.util.ArrayList;

public class DonationDetailsActivity extends AppCompatActivity {

    private ArrayList<Donation> donationList = new ArrayList<>();
    public DatabaseReference databaseDonations;

    /**
     * This method is to create the page of the donation details.
     * @param savedInstanceState The object's previously saved state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_details);

        databaseDonations = FirebaseDatabase.getInstance().getReference("donations");

        //Button to go back to donation recyclerview page
        Button backbutton = (Button) findViewById(R.id.back_donation_view_button);

        //Button to logout
        Button logoutButton = (Button) findViewById(R.id.return_login_Button);

        //Method to go back to LocationDetailActivity
        backbutton.setOnClickListener(new View.OnClickListener() {
            /**
             * Method to go back to LocationDetailActivity
             * @param v The page
             */
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

            String donationtitle = getIntent().getStringExtra("donation_title");

            setDonationDetails(donationtitle);
        }
    }

    /**
     * This method is to set details from information passed in from RecyclerViewAdapter
     * @param donationTitle The name of the donation.
     */
    private void setDonationDetails(String donationTitle) {
        //Log.d(TAG, "setDonationDetails: Setting Donation name and details");
        int index = 0;
        for (Donation donation: donationList) {
            if (donation.title.equals(donationTitle)) {
                index = donationList.indexOf(donation);
            }
        }
        Donation selected = donationList.get(index);

        TextView name =(TextView) findViewById(R.id.location_name);

        TextView time =(TextView) findViewById(R.id.time_title);

        TextView location =(TextView) findViewById(R.id.location_title);

        TextView value =(TextView) findViewById(R.id.value_title);

        TextView type =(TextView) findViewById(R.id.type_title);

        TextView description =(TextView) findViewById(R.id.address_description);

        name.setText(selected.title);
        time.setText("TIME: " + selected.timestamp);
        location.setText("LOCATION: " + selected.location);
        value.setText("VALUE: $" + selected.value);
        type.setText("TYPE: " + selected.category);
        description.setText(selected.fulldescription);
    }
}
