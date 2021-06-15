package com.buzzware.alibi.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;

import com.buzzware.alibi.adapters.RecyclerViewAdapterEvents;
import com.buzzware.alibi.adapters.RecyclerViewAdapterRosters;
import com.buzzware.alibi.databinding.ActivityActiveEventsBinding;
import com.buzzware.alibi.databinding.ActivityRostersBinding;

public class RostersActivity extends AppCompatActivity {

    ActivityRostersBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRostersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setView();
        initRecyclerViewRosters();
        setListener();
    }

    private void setView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        binding.includeView.titleTV.setText("Rosters");
    }

    private void setListener() {
        binding.createRosterBtn.setOnClickListener(view -> {

        });

        binding.includeView.backIV.setOnClickListener(view -> {
            finish();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });

        binding.includeView.otherTV.setOnClickListener(view -> {
            startActivity(new Intent(RostersActivity.this, SignInActivity.class));
            finish();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });
    }

    private void initRecyclerViewRosters() {
        LinearLayoutManager layoutManager5 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.rostersRV.setLayoutManager(layoutManager5);

        RecyclerViewAdapterRosters adapter = new RecyclerViewAdapterRosters(this, null);
        binding.rostersRV.setItemAnimator(new DefaultItemAnimator());
        binding.rostersRV.setAdapter(adapter);
    }
}