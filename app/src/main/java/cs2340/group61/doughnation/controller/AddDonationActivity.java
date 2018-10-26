package cs2340.group61.doughnation.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.ArrayAdapter;

import cs2340.group61.doughnation.R;
import cs2340.group61.doughnation.model.DonationCategory;

import static cs2340.group61.doughnation.R.layout.activity_add_donation;

public class AddDonationActivity extends AppCompatActivity {

    private static final String TAG = "ViewDonationsActivity";

    private Spinner donationCategorySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_add_donation);

        //Button to go back to locationDetailActivity page
        Button backbutton = (Button) findViewById(R.id.back_button);

        //Button to logout
        Button logoutButton = (Button) findViewById(R.id.return_login_Button);

        //Button to go to AddDonationActivity
        Button addDonationButton = (Button) findViewById(R.id.done_button);

        //Spinner for donation category types
        donationCategorySpinner = (Spinner) findViewById(R.id.donation_type_spinner);

        //populate donation category spinner
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, DonationCategory.values());
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        donationCategorySpinner.setAdapter(categoryAdapter);

        //Method to go back to LocationDetailActivity
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddDonationActivity.this,
                        ViewDonationsActivity.class));
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

        //Method to logout
        addDonationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add method here to add new donation to recyclerview
            }
        });
    }
}
