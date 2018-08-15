package com.porject.cmu.mobileapplicationdesignandimplementation;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

public class HomeActivity extends AppCompatActivity implements OnMapReadyCallback,View.OnClickListener {

    private TextView mTextMessage;
    SupportMapFragment mapFragment;
    private IntentIntegrator qrScan;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return true;
                case R.id.navigation_dashboard:
                    qrScan = new IntentIntegrator(HomeActivity.this);
                    qrScan.initiateScan();
                    return true;
                case R.id.navigation_notifications:
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onClick(View view) {}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {
                try {
                    Intent intent = new Intent(this, ItemDataActivity.class);
                    intent.putExtra("itemName",result.getContents().toString());
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        int height = 100;
        int width = 100;

        BitmapDrawable bitmapdraw_nike=(BitmapDrawable)getResources().getDrawable(R.drawable.nike);
        Bitmap b_nike=bitmapdraw_nike.getBitmap();
        Bitmap smallMarker_nike = Bitmap.createScaledBitmap(b_nike, width, height, false);

        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(37.444016, -122.170927))
                .title("Nike Running").snippet("Visited twice before"))
                .setIcon(BitmapDescriptorFactory.fromBitmap(smallMarker_nike));

        BitmapDrawable bitmapdraw_puma=(BitmapDrawable)getResources().getDrawable(R.drawable.puma);
        Bitmap b_puma=bitmapdraw_puma.getBitmap();
        Bitmap smallMarker_puma = Bitmap.createScaledBitmap(b_puma, width, height, false);

        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(37.415873,-122.357534))
                .title("Puma")
                .snippet("Visited once before")).setIcon(BitmapDescriptorFactory.fromBitmap(smallMarker_puma));

        BitmapDrawable bitmapdraw_add=(BitmapDrawable)getResources().getDrawable(R.drawable.adidas);
        Bitmap b_add=bitmapdraw_add.getBitmap();
        Bitmap smallMarker_add = Bitmap.createScaledBitmap(b_add, width, height, false);
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(37.417645, -122.255301))
                .title("Adidas Outlet Store")).setIcon(BitmapDescriptorFactory.fromBitmap(smallMarker_add));

        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.444016, -122.170927), 10));

        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent = new Intent(HomeActivity.this, StoreDataActivity.class);
                String title = marker.getTitle();
                intent.putExtra("shopName",title);
                startActivity(intent);
            }
        });
    }

}
