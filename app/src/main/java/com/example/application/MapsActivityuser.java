package com.example.application;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.location.Location;
import android.os.Build;

import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class MapsActivityuser extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private GoogleMap mMap;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    LocationRequest mLocationRequest;
    FirebaseFirestore db;
    String userid;
    double la;
    double lo;
    public class marks{
        double la ;
        double lo;
        String title;
        String workerid;
        public marks(double la, double lo, String this_is_title, String workerid) {
            this.title=this_is_title;
            this.la=la;
            this.lo=lo;
            this.workerid=workerid;
        }
        double return_la(){
            return la;
        }
        double return_lo(){
            return lo;
        }
        String return_title(){
            return title;
        }
        String return_workerid(){return workerid;}
    }
    List<marks> list = new ArrayList<marks>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_activityuser);
        db = FirebaseFirestore.getInstance();

        try {
            FileInputStream fileIn = openFileInput("mytextfile.txt");
            InputStreamReader InputRead = new InputStreamReader(fileIn);
            char[] inputBuffer = new char[100];
            String s = "";
            int charRead;
            while ((charRead = InputRead.read(inputBuffer)) > 0) {
                String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                s += readstring;
                userid = s;
            }
            InputRead.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Intent myIntent = getIntent();
        String id = myIntent.getStringExtra("field");
        final ArrayList<Double> logitude = new ArrayList<Double>();
        final ArrayList<Double> latitude = new ArrayList<Double>();
        final ArrayList<String> name = new ArrayList<String>();
        if (id.equals("electriction")) {
            db = FirebaseFirestore.getInstance();
            db.collection("worker").whereEqualTo("field", "electrition")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    String lo = (String) document.get("logitude");
                                    String la = (String) document.get("latitude");
                                    String names = (String) document.get("name");
                                    String workerid=document.getId();
                                    double lod = Double.parseDouble(lo);
                                    double lad = Double.parseDouble(la);
                                    logitude.add(lod);
                                    latitude.add(lad);
                                    name.add(names);
                                    marks a = new marks(lod, lad, names,workerid);
                                    list.add(a);
                                }
                            } else {
                                Log.w(TAG, "Error getting documents.", task.getException());
                                Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_LONG).show();
                            }

                        }


                    });

        }else if (id.equals("work")){
            db = FirebaseFirestore.getInstance();
            db.collection("worker").whereEqualTo("field", "work")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    String lo = (String) document.get("logitude");
                                    String la = (String) document.get("latitude");
                                    String names = (String) document.get("name");
                                    String workerid=document.getId();
                                    double lod = Double.parseDouble(lo);
                                    double lad = Double.parseDouble(la);
                                    logitude.add(lod);
                                    latitude.add(lad);
                                    name.add(names);
                                    marks a = new marks(lod, lad, names,workerid);
                                    list.add(a);
                                }
                            } else {
                                Log.w(TAG, "Error getting documents.", task.getException());
                                Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_LONG).show();
                            }

                        }


                    });

        }else if (id.equals("paint")){
            db = FirebaseFirestore.getInstance();
            db.collection("worker").whereEqualTo("field", "paint")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    String lo = (String) document.get("logitude");
                                    String la = (String) document.get("latitude");
                                    String names = (String) document.get("name");
                                    String workerid=document.getId();
                                    double lod = Double.parseDouble(lo);
                                    double lad = Double.parseDouble(la);
                                    logitude.add(lod);
                                    latitude.add(lad);
                                    name.add(names);
                                    marks a = new marks(lod, lad, names,workerid);
                                    list.add(a);
                                }
                            } else {
                                Log.w(TAG, "Error getting documents.", task.getException());
                                Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_LONG).show();
                            }

                        }


                    });

        }else if (id.equals("carpanter")){
            db = FirebaseFirestore.getInstance();
            db.collection("worker").whereEqualTo("field", "carpentar")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    String lo = (String) document.get("logitude");
                                    String la = (String) document.get("latitude");
                                    String names = (String) document.get("name");
                                    String workerid=document.getId();
                                    double lod = Double.parseDouble(lo);
                                    double lad = Double.parseDouble(la);
                                    logitude.add(lod);
                                    latitude.add(lad);
                                    name.add(names);
                                    marks a = new marks(lod, lad, names,workerid);
                                    list.add(a);
                                }
                            } else {
                                Log.w(TAG, "Error getting documents.", task.getException());
                                Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_LONG).show();
                            }

                        }


                    });

        }else if (id.equals("plumber")){
            db = FirebaseFirestore.getInstance();
            db.collection("worker").whereEqualTo("field", "plumber")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    String lo = (String) document.get("logitude");
                                    String la = (String) document.get("latitude");
                                    String names = (String) document.get("name");
                                    String workerid=document.getId();
                                    double lod = Double.parseDouble(lo);
                                    double lad = Double.parseDouble(la);
                                    logitude.add(lod);
                                    latitude.add(lad);
                                    name.add(names);
                                    marks a = new marks(lod, lad, names,workerid);
                                    list.add(a);
                                }
                            } else {
                                Log.w(TAG, "Error getting documents.", task.getException());
                                Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_LONG).show();
                            }

                        }


                    });

        }else if (id.equals("services")){
            db = FirebaseFirestore.getInstance();
            db.collection("worker").whereEqualTo("field", "services")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    String lo = (String) document.get("logitude");
                                    String la = (String) document.get("latitude");
                                    String names = (String) document.get("name");
                                    String workerid=document.getId();
                                    double lod = Double.parseDouble(lo);
                                    double lad = Double.parseDouble(la);
                                    logitude.add(lod);
                                    latitude.add(lad);
                                    name.add(names);
                                    marks a = new marks(lod, lad, names,workerid);
                                    list.add(a);
                                }
                            } else {
                                Log.w(TAG, "Error getting documents.", task.getException());
                                Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_LONG).show();
                            }

                        }


                    });

        }
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);

            }
        }
        else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {

        mLastLocation = location;
        String log= String.valueOf(mLastLocation.getAltitude());
        String let= String.valueOf(mLastLocation.getLongitude());
        db.collection("users")
                .document(userid)
                .update(
                        "clatitude", log
                );
        db.collection("users")
                .document(userid)
                .update(
                        "clongitude",let
                );
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }

        //Place current location marker


        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        mCurrLocationMarker = mMap.addMarker(markerOptions);


        for (int i=0 ;i<list.size();i++) {
            double las  = list.get(i).return_la(); ;
            double los = list.get(i).return_lo();
            String titles=list.get(i).return_title();
            String workerid=list.get(i).return_workerid();



            MarkerOptions markerOptionss = new MarkerOptions();
            LatLng latLngs = new LatLng(las, los);
            markerOptionss.position(latLngs);
            markerOptionss.title(titles);
            markerOptionss.snippet(workerid);
            markerOptionss.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
            mCurrLocationMarker = mMap.addMarker(markerOptionss);
        }
       mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                String venueID = marker.getId();
                String workerid = marker.getSnippet();
                marker.setSnippet("");
                if(!(venueID.equals("m0"))) {
                    Intent intent = new Intent(MapsActivityuser.this, userdashboard.class);
                    intent.putExtra("workerid", workerid);
                    startActivity(intent);
                }
                return false;
            }
        });




        //move map camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(14));

        //stop location updates
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    public boolean checkLocationPermission(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Asking user if explanation is needed
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                //Prompt the user once explanation has been shown
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted. Do the
                    // contacts-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }

                } else {

                    // Permission denied, Disable the functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }


        }
    }
}
