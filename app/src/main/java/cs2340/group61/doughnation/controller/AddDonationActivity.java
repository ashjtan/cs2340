package cs2340.group61.doughnation.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import cs2340.group61.doughnation.model.domain.Donation;

import cs2340.group61.doughnation.R;

public class AddDonationActivity extends AppCompatActivity {

    private static final String TAG = "ViewDonationsActivity";
    public DatabaseReference databaseDonations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_donation);

        databaseDonations = FirebaseDatabase.getInstance().getReference("donations");

        //Button to go back to locationDetailActivity page
        Button backbutton = (Button) findViewById(R.id.back_button);

        //Button to logout
        Button logoutButton = (Button) findViewById(R.id.return_login_Button);

        //Button to go to AddDonationActivity
        Button addDonationButton = (Button) findViewById(R.id.done_button);

        final EditText name = (EditText) findViewById(R.id.add_name);

        final EditText description = (EditText) findViewById(R.id.add_description);




        //Method to go back to LocationDetailActivity
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddDonationActivity.this,
                        HomePageActivity.class));
            }
        });

        //Method to logout
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddDonationActivity.this,
                        LoginActivity.class));
            }
        });

        //Method to add donation
        addDonationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDonation();
                //logic here to set credentials and add info to database
                startActivity(new Intent(AddDonationActivity.this,
                        HomePageActivity.class));
            }
        });
    }

    private void addDonation() {
        String id = databaseDonations.push().getKey();

        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String time = sdf.format(timestamp);

        Spinner location = (Spinner) findViewById(R.id.location_selection_spinner);
        EditText name = (EditText) findViewById(R.id.add_name);
        EditText value = (EditText) findViewById(R.id.add_value);
        Spinner type = (Spinner) findViewById(R.id.donation_type_spinner);
        EditText description = (EditText) findViewById(R.id.add_description);

        Donation donation = new Donation();
        donation.setTitle(name.getText().toString());
        donation.setLocation(location.getSelectedItem().toString());
        donation.setCategory(type.getSelectedItem().toString());
        donation.setFulldescription(description.getText().toString());
        donation.setShortdescription(description.getText().toString());
        donation.setValue(value.getText().toString());
        donation.setTimestamp(time);
        donation.setId(id);

        databaseDonations.child(id).setValue(donation);
    }
}
