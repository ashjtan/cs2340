package cs2340.group61.doughnation.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cs2340.group61.doughnation.R;

public class AddDonationActivity extends AppCompatActivity {

    private static final String TAG = "ViewDonationsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_donation);

        //Button to go back to locationDetailActivity page
        Button backbutton = (Button) findViewById(R.id.back_button);

        //Button to logout
        Button logoutButton = (Button) findViewById(R.id.return_login_Button);

        //Button to go to AddDonationActivity
        Button addDonationButton = (Button) findViewById(R.id.done_button);

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

        //Method to add donation
        addDonationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //logic here to set credentials and add info to database
                startActivity(new Intent(AddDonationActivity.this,
                        ViewDonationsActivity.class));
            }
        });
    }
}
