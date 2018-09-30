package cs2340.group61.doughnation.controller;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import cs2340.group61.doughnation.R;
import cs2340.group61.doughnation.domain.AccountType;
import cs2340.group61.doughnation.domain.Utils;

import static cs2340.group61.doughnation.domain.Utils.isNotEmpty;

public class RegistrationActivity extends AppCompatActivity {

    private ActionBar actionbar;
    private Button toLogin;
    private EditText firstName, lastName, email, pw, confirmPw;

    String firstText, lastText, emailText , pwText, confPwText; //populated w/ form values

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        //Setting some actionbar characteristics
        //Changes color and text in the actionbar
        actionbar = getSupportActionBar();
        actionbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF4A4A")));
        actionbar.setTitle("DoughNation");


        //Populates spinner w/ account types
        ArrayAdapter<String> acctTypeAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, AccountType.values());
        acctTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        acctType.setAdapter(acctTypeAdapter);
    }


    //EVENT HANDLERS
    public void switchToLogin(View v) {      //switches to register page
        startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
    }

}
