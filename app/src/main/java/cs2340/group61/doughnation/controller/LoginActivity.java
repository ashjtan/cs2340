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
import cs2340.group61.doughnation.model.AppData;
import cs2340.group61.doughnation.model.domain.accounts.User;

/**
 * This is a class that displays the login page to the user.
 */
@SuppressWarnings("ChainedMethodCall")
public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    private Button registerButton;
    private EditText userField;
    private EditText passField;
    private String userText = "";
    private String passText = "";    //prevents null pointer


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Instantiating xml elements
        loginButton = findViewById(R.id.login_button);
        registerButton = findViewById(R.id.register_Button);
        userField = findViewById(R.id.email);
        passField = findViewById(R.id.password);


        //Setting some actionbar characteristics
        //Changes color and text in the actionbar
        ActionBar actionbar = getSupportActionBar();
        actionbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF4A4A")));
        actionbar.setTitle("DoughNation");
    }


    /**
     * This is a method to allow users to login to the app.
     * @param v The page.
     */
    //EVENT HANDLERS
    public void login(View v) {
        //logs in if valid credentials
//        if (validLogin()) {
//            //TODO: needs to custom load for specific acct
//            startActivity(new Intent(LoginActivity.this, HomePageActivity.class));
//        }
//        else {
//            Utils.showDialog("Wrong username or password.", "Invalid Login", this);
//        }
        startActivity(new Intent(LoginActivity.this, HomePageActivity.class));

    }

    /**
     * This allows users to go to the registration page.
     * @param v The page.
     */
    public void switchToRegister(View v) {      //switches to register page
        startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
    }




    //HELPER METHODS
    private boolean validLogin() {
        userText = userField.getText().toString();
        passText = passField.getText().toString();

        User user = AppData.findUserByEmail(userText);

        return ((user != null) && passText.equals(user.getPassword()));
    }
}
