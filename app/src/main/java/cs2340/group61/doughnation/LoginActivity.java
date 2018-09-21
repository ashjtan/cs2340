package cs2340.group61.doughnation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button cancelButton = (Button) findViewById(R.id.cancel_login_button);
    }

    public void goToWelcome(View view) {
        //add method here to return back to home screen (welcome page)
        //Thought that this would work, I was wrong
        //This shit just close the app lol
        //delete this comment ):<
        setContentView(R.layout.activity_main);
    }
}
