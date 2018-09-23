package cs2340.group61.doughnation;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private ActionBar actionbar;
    Button loginButton, registerButton;
    EditText userField, passField;


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
            //TODO: add error message
        }
    }

    public void switchToRegister(View v) {      //switches to register page
        startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
    }
}
