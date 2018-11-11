package cs2340.group61.doughnation.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


import cs2340.group61.doughnation.R;

/**
 * Allows users to view the map.
 */
@SuppressWarnings("ChainedMethodCall")
public class MapViewActivity extends AppCompatActivity implements OnMapReadyCallback {

    private MapView mapView;

    private static final LatLng goodWillLoc = new LatLng(33.777924, -84.382988);
    private static final LatLng redCrossLoc = new LatLng(33.773678, -84.402611);
    private static final LatLng carDonationLoc = new LatLng(33.784673, -84.382671);

    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view);

        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }

        mapView = findViewById(R.id.map_view);
        mapView.onCreate(mapViewBundle);
        mapView.getMapAsync(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAP_VIEW_BUNDLE_KEY, mapViewBundle);
        }

        mapView.onSaveInstanceState(mapViewBundle);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        GoogleMap googleMap1 = googleMap;
        googleMap1.getUiSettings().setZoomControlsEnabled(true);
        googleMap1.getUiSettings().setRotateGesturesEnabled(true);
        int zoomPref = 12;
        double lat = 33.7490;
        double lon = -84.3880;
        googleMap1.setMinZoomPreference(zoomPref);
        LatLng atl = new LatLng(lat, lon);
        googleMap1.moveCamera(CameraUpdateFactory.newLatLng(atl));

        // Add some markers to the map, and add a data object to each marker.
        Marker goodWill = googleMap1.addMarker(new MarkerOptions()
                .position(goodWillLoc)
                .title("Good Will")
                .snippet("Location Type: DROP"));
        goodWill.setTag(0);

        Marker redCross = googleMap1.addMarker(new MarkerOptions()
                .position(redCrossLoc)
                .title("Red Cross")
                .snippet("Location Type: PICK UP"));
        redCross.setTag(0);

        Marker carDonation = googleMap1.addMarker(new MarkerOptions()
                .position(carDonationLoc)
                .title("Car Donation Center")
                .snippet("Location Type: BLAH BLAH"));
        carDonation.setTag(0);

        // Set a listener for marker click.
        //googleMap1.setOnMarkerClickListener(this);

    }
}
