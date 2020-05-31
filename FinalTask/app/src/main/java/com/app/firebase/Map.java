package com.app.firebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;

import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;

import android.util.Log;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.app.firebase.DirectionHelper.FetchURL;
import com.app.firebase.DirectionHelper.TaskLoadedCallback;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.GeoPoint;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.PendingResult;
import com.google.maps.android.SphericalUtil;
import com.google.maps.model.DirectionsResult;


import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static com.app.firebase.Constants.ERROR_DIALOG_REQUEST;
import static com.app.firebase.Constants.MAPVIEW_BUNDLE_KEY;
import static com.app.firebase.Constants.PERMISSION_REQUEST_ACCESS_FINE_LOCATION;
import static com.app.firebase.Constants.PERMISSION_REQUEST_ENABLE_GPS;


public class Map extends AppCompatActivity implements OnMapReadyCallback, TaskLoadedCallback {
    public static final String TAG = "MapActivity";
    private boolean mLocationPermissionGranted = false;
    Spinner locationOne, locationTwo;

    private MapView mapView;
    //google map object
    private GoogleMap map;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private LatLng bank = new LatLng(22.245398, 84.858725);
    private LatLng currentLatLng;
    private Double distance;
    DecimalFormat df = new DecimalFormat("#.##");

    private TextView distanceBetween,durationBetween;

    private MarkerOptions place1,place2;
    private Polyline currentPolyline;
    List<MarkerOptions> markerOptionsList=new ArrayList<>();


    private GeoApiContext geoApiContext=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        locationOne = (Spinner) findViewById(R.id.locationOneSpinner);
        locationTwo = (Spinner) findViewById(R.id.locationTwoSpinner);
        distanceBetween=(TextView)findViewById(R.id.distancetV);
        durationBetween=(TextView)findViewById(R.id.durationtV);
        mapView = (MapView) findViewById(R.id.mapView);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        initGoogleMap(savedInstanceState);

    }

    private void initGoogleMap(Bundle savedInstanceState) {
        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
        }

        mapView.onCreate(mapViewBundle);
        mapView.getMapAsync(this);
        if(geoApiContext==null){
            geoApiContext=new GeoApiContext.Builder().apiKey(getString(R.string.google_maps_api_key)).build();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        map.setMyLocationEnabled(true);
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                if (task.isSuccessful()) {
                    Location location = task.getResult();
                    currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());

                    map.addMarker(new MarkerOptions().position(currentLatLng).title("Current Location"));
                    map.addMarker(new MarkerOptions().position(bank).title("SBI Bank"));
                    //Calculate Distance between the two locations
                    distance= (SphericalUtil.computeDistanceBetween(currentLatLng,bank))/1000;
                    double distanced=distance.doubleValue();
                    String db=df.format(distanced);
                    distanceBetween.setText(db+" km");
//                    calculateDistanceBetween(currentLatLng,bank);
                }
            }
        });



    }

//    private void calculateDistanceBetween(LatLng origin, LatLng destination) {
//        DirectionsApiRequest directions=new DirectionsApiRequest(geoApiContext);
//        directions.alternatives(true);
//        directions.origin(String.valueOf(origin));
//        Log.d(TAG,"calculateDirections: destination:"+destination.toString());
//        directions.destination(String.valueOf(destination))
//                .setCallback(new PendingResult.Callback<DirectionsResult>() {
//                    @Override
//                    public void onResult(DirectionsResult result) {
//                        Log.d(TAG,"calculateDirections: destination:"+result.routes[0].toString());
//                        Log.d(TAG,"calculateDirections: destination:"+result.routes[0].legs[0].duration);
//                        Log.d(TAG,"calculateDirections: destination:"+result.routes[0].legs[0].distance);
//                        Log.d(TAG,"calculateDirections: geoCodedWayPoints:"+result.geocodedWaypoints[0].toString());
//
//                    }
//
//                    @Override
//                    public void onFailure(Throwable e) {
//                        Log.d(TAG,"calculateDirections: Failed to get directions:"+e.getMessage());
//                    }
//                });
//
//    }


    private String getUrl(LatLng origin, LatLng destination, String directionMode) {
        String strOrg="Origin="+origin.latitude+","+origin.longitude;
        String strDest="Destination"+destination.latitude+","+destination.longitude;
        String mode="mode="+directionMode;
        String param=strOrg+"&"+strDest+"&"+mode;
        String format="json";
        String url="https://maps.googleapis.com/maps/api/directions/"+format+"?"+param+"&key="+getString(R.string.google_maps_api_key);
        return url;
    }



    private void getLastKnownLocation() {
        Log.d(TAG, "getLastKnownLocation:called");
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                if (task.isSuccessful()) {
                    Location location = task.getResult();
                    GeoPoint geoPoint = new GeoPoint(location.getLatitude(), location.getLongitude());
                    //Camera at current position
                    LatLng currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());
                    CameraUpdate update = CameraUpdateFactory.newLatLngZoom(currentLatLng, 18f);
                    map.moveCamera(update);
                }
            }
        });
    }


    public boolean isMapsEnabled() {
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();
            return false;
        }
        return true;
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("This application requires GPS to work properly, do you want to enable it?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent enableGpsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(enableGpsIntent, PERMISSION_REQUEST_ENABLE_GPS);
            }
        });
        final AlertDialog alert = builder.create();
        alert.show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch ((requestCode)) {
            case PERMISSION_REQUEST_ENABLE_GPS: {
                if (!mLocationPermissionGranted) {
                    getLocationPermission();
                } else {

                }
            }
        }
    }

    //Get permission to use Location
    private void getLocationPermission() {
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
            getLastKnownLocation();


        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    //Check for services
    public boolean isServicesOK() {
        Log.d(TAG, "isServicesOK: checking google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(Map.this);

        if (available == ConnectionResult.SUCCESS) {
            //everything is fine and the user can make map requests
            Log.d(TAG, "isServicesOK: Google Play Services is working");
            return true;
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            //an error occured but we can resolve it
            Log.d(TAG, "isServicesOK: an error occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(Map.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        } else {
            Toast.makeText(this, "You can't make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSION_REQUEST_ACCESS_FINE_LOCATION: {
                //If request is cancelled, the result array is empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                    getLastKnownLocation();

                }
            }
        }
    }

    public boolean checkMapServices() {
        if (isMapsEnabled()) {
            return true;
        }
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (checkMapServices()) {
            if (!mLocationPermissionGranted) {
                getLocationPermission();
            } else {

            }
        }
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Bundle mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle);
        }
        mapView.onSaveInstanceState(mapViewBundle);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        mapView.onResume();
    }



    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onTaskDone(Object... values) {
        if(currentPolyline!=null)
            currentPolyline.remove();
        currentPolyline=map.addPolyline((PolylineOptions) values[0]);
    }

}

