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
import com.buzzware.alibi.adapters.RecyclerViewAdapterPerson;
import com.buzzware.alibi.databinding.ActivityActiveEventsBinding;
import com.buzzware.alibi.databinding.ActivityEventDetailBinding;
import com.buzzware.alibi.databinding.ActivityEventDetailsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class EventDetailActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    ActivityEventDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEventDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setView();
        setListener();
        initRecyclerViewPerson();
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
        binding.lineView.setOnClickListener(view -> {
            binding.upperCL.setVisibility(View.GONE);
        });
    }

    private void initRecyclerViewPerson() {
        LinearLayoutManager layoutManager5 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.personRV.setLayoutManager(layoutManager5);

        RecyclerViewAdapterPerson adapter = new RecyclerViewAdapterPerson(this, null);
        binding.personRV.setItemAnimator(new DefaultItemAnimator());
        binding.personRV.setAdapter(adapter);
    }
}