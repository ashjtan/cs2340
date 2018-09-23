package cs2340.group61.doughnation;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegistrationActivity extends AppCompatActivity {

    private ActionBar actionbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //Setting some actionbar characteristics
        //Changes color and text in the actionbar
        actionbar = getSupportActionBar();
        actionbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF4A4A")));
        actionbar.setTitle("DoughNation");

        //Instantiating toLogin button to return to login from Reg. page
        Button toLogin = (Button) findViewById(R.id.return_login_Button);

        toLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
            }
        });
    }
}
