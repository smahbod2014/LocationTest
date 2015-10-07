package com.example.koda.locationtest;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Handler;
import android.os.Parcel;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class LocationActivity extends ActionBarActivity {

    LocationManager m_LocManager;
    Button m_BtnSubmit;
    TextView m_TxtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        m_LocManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);

        m_BtnSubmit = (Button) findViewById(R.id.send_button);
        m_TxtView = (TextView) findViewById(R.id.display_textview);
    }

    public void sendClicked(View v)
    {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                m_LocManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, m_LocListener, null);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_location, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private LocationListener m_LocListener = new LocationListener() {

        @Override
        public void onLocationChanged(Location location) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            m_TxtView.setText("You are at " + longitude + " longitude and " + latitude + " latitude");
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };
}
