package com.example.usmanahmed.innovaday6_gps;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    TextView speed,location;
    Button start;
    LocationManager locationManager;
    locationProvider object;




    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        speed=(TextView) findViewById(R.id.speedview);
        location=(TextView) findViewById(R.id.locationview);
        start=(Button) findViewById(R.id.button);
        //progress wala kam idhr krna h


        locationManager=(LocationManager) getSystemService(LOCATION_SERVICE);
        object=new locationProvider(MainActivity.this,location,speed);//is object m context bnana lazmi is lye h k agli class ko bhej sky view

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,object);

            }
        });



    }
}
