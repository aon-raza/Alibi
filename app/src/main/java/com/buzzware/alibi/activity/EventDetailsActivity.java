package com.buzzware.alibi.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;

import com.buzzware.alibi.R;
import com.buzzware.alibi.databinding.ActivityEventDetailsBinding;
import com.buzzware.alibi.databinding.ActivityNewEventBinding;

public class EventDetailsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    ArrayAdapter spinnerAdapter;
    ArrayAdapter reminderSpinnerAdapter;
    ArrayAdapter uniformSpinnerAdapter;
    String[] shareWith = {"Lt. Keesler", "Bravo 2", "Lt. Keesler", "Bravo 2"};
    String[] reminders = {"45 min before", "30 min before", "15 min before", "10 min before"};
    String[] uniforms = {"Uniform of the day", "Civilian Attire"};
    ActivityEventDetailsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEventDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setView();
        setSpinner();
        setListener();
    }

    private void setView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        binding.includeView.titleTV.setText("Event Details");
    }

    private void setSpinner() {
        binding.shareSpinner.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        spinnerAdapter = new ArrayAdapter(this, R.layout.spinner_text_color, shareWith);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        binding.shareSpinner.setAdapter(spinnerAdapter);

        binding.reminderSpinner.setOnItemSelectedListener(this);

        reminderSpinnerAdapter = new ArrayAdapter(this, R.layout.spinner_text_black, reminders);
        reminderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.reminderSpinner.setAdapter(reminderSpinnerAdapter);

        binding.uniformSpinner.setOnItemSelectedListener(this);

        uniformSpinnerAdapter = new ArrayAdapter(this, R.layout.spinner_text_black, uniforms);
        uniformSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.uniformSpinner.setAdapter(uniformSpinnerAdapter);
    }

    private void setListener() {
        binding.saveBT.setOnClickListener(view -> {
//            startActivity(new Intent(EventDetailsActivity.this, AddLocationActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });

        binding.includeView.backIV.setOnClickListener(view -> {
            finish();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });

        binding.includeView.otherTV.setOnClickListener(view -> {
            startActivity(new Intent(EventDetailsActivity.this, SignInActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        });

        binding.eventTypeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    binding.geoTrackedIV.setVisibility(View.VISIBLE);
                    binding.geoTrackedTV.setVisibility(View.VISIBLE);
                    binding.eventTypeTV.setText("Public");
                    binding.eventTypeTV.setTextColor(getResources().getColor(R.color.green));
                    binding.descriptionTV.setText("All users can see this event.");
                } else {
                    binding.geoTrackedIV.setVisibility(View.GONE);
                    binding.geoTrackedTV.setVisibility(View.GONE);
                    binding.eventTypeTV.setText("Private");
                    binding.eventTypeTV.setTextColor(getResources().getColor(R.color.red));
                    binding.descriptionTV.setText("No users can see this event unless you share it with them.");
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}