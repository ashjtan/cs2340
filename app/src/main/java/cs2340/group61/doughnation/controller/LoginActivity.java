package cs2340.group61.doughnation.controller;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cs2340.group61.doughnation.R;
import cs2340.group61.doughnation.model.Utils;

public class LoginActivity extends AppCompatActivity {

    private ActionBar actionbar;
    Button loginButton, registerButton;
    EditText userField, passField;
    String userText = "", passText = "";    //prevents null pointer


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Instantiating xml elements
        loginButton = (Button)findViewById(R.id.login_button);
        registerButton = (Button) findViewById(R.id.register_Button);
        userField = (EditText) findViewById(R.id.username);
        passField = (EditText) findViewById(R.id.password);


        //Setting some actionbar characteristics
        //Changes color and text in the actionbar
        actionbar = getSupportActionBar();
        actionbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF4A4A")));
        actionbar.setTitle("DoughNation");
    }




    //EVENT HANDLERS
    public void login(View v) {     //logs in if valid credentials
        if (validLogin()) {
            startActivity(new Intent(LoginActivity.this, HomePageActivity.class));
        }
        else {
            Utils.showDialog("Wrong username or password.", "Invalid Login", this);
        }
    }

    public void switchToRegister(View v) {      //switches to register page
        startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
    }




    //HELPER METHODS
    private boolean validLogin() {
        userText = userField.getText().toString();
        passText = passField.getText().toString();

        return userText.equals("admin") && passText.equals("pw");   //currently checks for admin:pw credentials
    }
}
