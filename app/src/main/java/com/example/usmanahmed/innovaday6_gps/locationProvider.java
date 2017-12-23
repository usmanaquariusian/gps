package com.example.usmanahmed.innovaday6_gps;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by Usman Ahmed on 30/10/2017.
 */

public class locationProvider implements LocationListener {

    Context context;
    TextView locationview,speedview;

    public  locationProvider(Context context, TextView location,TextView speed)//view activity m hota h tb e yha context bnaya k yha toast lga sky
    {

        this.context=context;
        this.locationview=location;
        this.speedview=speed;
    }

    @Override
    public void onLocationChanged(Location location) {
        double latitude=location.getLatitude();
        double longitude=location.getLongitude();
        double speed=location.getSpeed()*3.6;

        locationview.setText("Latitude:"+latitude+"\\n Longitude:" +latitude);
        speedview.setText("Speed:" +speed);

        Geocoder geocoder;
        List<Address> addresses=null;
        geocoder = new Geocoder(context, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
        } catch (IOException e) {
            e.printStackTrace();
        }

        String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
        String street = addresses.get(0).getLocality();
        String city = addresses.get(0).getSubAdminArea()+""+addresses.get(0).getLocality();
        String state=addresses.get(0).getAdminArea();
        String country = addresses.get(0).getCountryName();
        String postalCode = addresses.get(0).getPostalCode();
        String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL

        String Complete=address+""+street+""+city+""+state+""+country+""+postalCode+""+knownName+"";
        locationview.setText(Complete);

        //progressDialog.dismiss();

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {
        Intent intent=new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        context.startActivity(intent);
    }
}
