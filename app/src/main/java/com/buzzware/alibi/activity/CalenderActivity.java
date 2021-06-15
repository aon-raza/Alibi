package com.buzzware.alibi.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.buzzware.alibi.databinding.ActivityCalenderBinding;
import com.buzzware.alibi.databinding.ActivityMainBinding;

public class CalenderActivity extends AppCompatActivity {

    ActivityCalenderBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityCalenderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}