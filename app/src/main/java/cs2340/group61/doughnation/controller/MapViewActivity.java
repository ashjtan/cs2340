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

    private static final LatLng location2 = new LatLng(33.73182, -84.43971);
    private static final LatLng location1 = new LatLng(33.75416, -84.377742);
    private static final LatLng location3 = new LatLng(33.70866, -84.41853);
    private static final LatLng location4 = new LatLng(33.80129, -84.25537);
    private static final LatLng location5 = new LatLng(33.71747, -84.2521);
    private static final LatLng location6 = new LatLng(33.96921, -84.3688);

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

        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setRotateGesturesEnabled(true);
        final int zoomPref = 11;
        googleMap.setMinZoomPreference(zoomPref);
        final double lon = -84.3880;
        final double lat = 33.7490;
        LatLng atl = new LatLng(lat, lon);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(atl));

        // Add some markers to the map, and add a data object to each marker.
        Marker location22 = googleMap.addMarker(new MarkerOptions()
                .position(location2)
                .title("Boys & Girls Club W.W. Woolfolk")
                .snippet("(404) 555 -1234"));
        location22.setTag(0);

        Marker location11 = googleMap.addMarker(new MarkerOptions()
                .position(location1)
                .title("AFD Station 4")
                .snippet("(404) 555 -3456"));
        location11.setTag(0);

        Marker location33 = googleMap.addMarker(new MarkerOptions()
                .position(location3)
                .title("Pathway Upper Room Christian Ministries")
                .snippet("(404) 555 -5432"));
        location33.setTag(0);

        Marker location44 = googleMap.addMarker(new MarkerOptions()
                .position(location4)
                .title("Pavilion of Hope, INC")
                .snippet("(404) 555 -8765"));
        location44.setTag(0);

        Marker location55 = googleMap.addMarker(new MarkerOptions()
                .position(location5)
                .title("D&D Convenience Store")
                .snippet("(404) 555 -9876"));
        location55.setTag(0);

        Marker location66 = googleMap.addMarker(new MarkerOptions()
                .position(location6)
                .title("Keep North Fulton Beautiful")
                .snippet("(770) 555 -7321"));
        location66.setTag(0);

        // Set a listener for marker click.
        //googleMap1.setOnMarkerClickListener(this);

    }
}
