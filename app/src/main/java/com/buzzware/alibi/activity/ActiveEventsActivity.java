package com.buzzware.alibi.activity;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.buzzware.alibi.R;
import com.buzzware.alibi.adapters.RecyclerViewAdapterEvents;
import com.buzzware.alibi.databinding.ActivityActiveEventsBinding;
import com.buzzware.alibi.databinding.ActivityAddLocationBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ActiveEventsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    ActivityActiveEventsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityActiveEventsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setView();
        setListener();
        initRecyclerViewEvents();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    private void setView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    private void setListener() {
        binding.backIV.setOnClickListener(view -> {
            finish();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });

        binding.createEventBT.setOnClickListener(v -> {
            startActivity(new Intent(ActiveEventsActivity.this, AddLocationActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });
    }

    private void initRecyclerViewEvents() {
        LinearLayoutManager layoutManager5 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.eventsRV.setLayoutManager(layoutManager5);

        RecyclerViewAdapterEvents adapter = new RecyclerViewAdapterEvents(this, null);
        binding.eventsRV.setItemAnimator(new DefaultItemAnimator());
        binding.eventsRV.setAdapter(adapter);
    }
}