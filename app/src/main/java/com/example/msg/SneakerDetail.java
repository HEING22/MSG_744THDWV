package com.example.msg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class SneakerDetail extends AppCompatActivity implements OnMapReadyCallback {

    ImageView imageView;
    TextView nameTextView, detailTextView, priceTextView, brandTextView, yearTextView;
    Sneaker sneaker;
    double lat, lng;
    LatLng latLng;
    GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sneaker_detail);
        sneaker = (Sneaker) getIntent().getSerializableExtra("Sneaker");
        nameTextView = findViewById(R.id.detailSName);
        detailTextView = findViewById(R.id.detailSDetail);
        priceTextView = findViewById(R.id.detailSPrice);
        brandTextView = findViewById(R.id.detailSBrand);
        yearTextView = findViewById(R.id.detailSYear);
        imageView = findViewById(R.id.detailSImageView);
        nameTextView.setText(sneaker.name);
        detailTextView.setText(sneaker.detail);
        priceTextView.setText(sneaker.R_price);
        yearTextView.setText(sneaker.R_year);
        brandTextView.setText(sneaker.brand);
        new ImageDownload(imageView).execute(sneaker.image);
        lat = Double.parseDouble(sneaker.location.split(",")[0]);
        lng = Double.parseDouble(sneaker.location.split(",")[1]);
        latLng = new LatLng(lat, lng);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapView);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;
        googleMap.addMarker(new MarkerOptions()
                .position(latLng));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
    }
}