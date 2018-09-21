package cs2340.group61.doughnation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button toLoginScreen_button = (Button) findViewById(R.id.login_launch_button);
    }

    //Method to move from welcome screen into login screen
    public void goToLogin(View view) {
        setContentView(R.layout.activity_login);
    }
}
