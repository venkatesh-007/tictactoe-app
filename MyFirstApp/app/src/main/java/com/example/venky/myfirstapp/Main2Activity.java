package com.example.venky.myfirstapp;


import android.*;
import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.Manifest;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;


import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static android.location.LocationManager.GPS_PROVIDER;
import static android.location.LocationManager.NETWORK_PROVIDER;


public class Main2Activity extends AppCompatActivity {
    int MY_PERMISSIONS_REQUEST_LOCATION = 10;
    private TextView textview;
    private LocationListener mylistener;
    private LocationManager mymanager;
    private Button button;

    public String Number_of_players_selected = " ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Number_of_players, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        // Acquire a reference to the system Location Manager
        textview = (TextView) findViewById(R.id.answer);
        button = (Button) findViewById(R.id.button11);
        mymanager = (LocationManager) getSystemService(LOCATION_SERVICE);
        mylistener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                try {

                    Geocoder geo = new Geocoder(getApplicationContext(), Locale.getDefault());
                    List<Address> addresses = geo.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                    if (addresses.isEmpty()) {
                        textview.setText("Waiting for Location");
                    }
                    else {
                        if (addresses.size() > 0) {
                            String address = addresses.get(0).getAddressLine(0);
                            String city = addresses.get(0).getLocality();
                            String state = addresses.get(0).getAdminArea();
                            String country = addresses.get(0).getCountryName();
                            String postalCode = addresses.get(0).getPostalCode();
                            String knownName = addresses.get(0).getFeatureName();

                            textview.setText(address + "," + city + "," + state + "," +postalCode+ "," +country);
                            //Toast.makeText(getApplicationContext(), "Address:- " + addresses.get(0).getFeatureName() + addresses.get(0).getAdminArea() + addresses.get(0).getLocality(), Toast.LENGTH_LONG).show();
                        }
                    }
                }
                catch (Exception e) {
                    e.printStackTrace(); // getFromLocation() may sometimes fail
                }

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent);
            }
        };
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.INTERNET

                }, 10);
            }
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        else
        {
            configurebutton();

        }


// Register the listener with the Location Manager to receive location updates

    }



    public void onRequestPermissionsResult(int requestcode,String[] permissions, int grantResults[]){
       switch(requestcode){
           case 10:
               if (grantResults.length > 0
                       && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                   configurebutton();
               }
       }
   }

    private void configurebutton() {
        button.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){

                mymanager.requestLocationUpdates(GPS_PROVIDER, 5000, 0, mylistener);

            }
        });
    }
    public void gameon(View view){
        Intent intent1 = new Intent(this,MainActivity.class);
        Intent intent2 = new Intent(this,Main3Activity.class);
        //MainActivity.getInstance().playagain();
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
      //  spinner.setOnItemSelectedListener(this);
        //spinner.getOnItemSelectedListener();
       Number_of_players_selected = spinner.getSelectedItem().toString();
        if(Number_of_players_selected.equals("One Player"))
            startActivity(intent1);
        else if(Number_of_players_selected.equals("Two Player"))
            startActivity(intent2);
    }
}
