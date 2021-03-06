package com.buzzware.alibi.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;

import com.buzzware.alibi.databinding.ActivityMainBinding;
import com.buzzware.alibi.databinding.ActivityNewEventBinding;

public class NewEventActivity extends AppCompatActivity {

    ActivityNewEventBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewEventBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setView();
        setListener();
    }

    private void setView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        binding.includeView.titleTV.setText("New Event");
    }

    private void setListener() {
        binding.nextBT.setOnClickListener(view -> {
            startActivity(new Intent(NewEventActivity.this, AddLocationActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });

        binding.includeView.backIV.setOnClickListener(view -> {
            finish();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });

        binding.includeView.otherTV.setOnClickListener(view -> {
            startActivity(new Intent(NewEventActivity.this, SignInActivity.class));
            finish();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });
    }
}