package cs2340.group61.doughnation.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.LinkedList;

import cs2340.group61.doughnation.R;

public class ViewLocationsActivity extends AppCompatActivity {

    //This is a temporary LinkedList to fill the
    private final LinkedList<String> locationDataEx = new LinkedList<>();
    private int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_locations);

        for (int i = 0; i < 20; i++) {
            locationDataEx.addLast("Word " + counter++);
            Log.d("WordList", locationDataEx.getLast());
        }
    }
}
