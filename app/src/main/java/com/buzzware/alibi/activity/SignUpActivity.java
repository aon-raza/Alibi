package com.buzzware.alibi.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.buzzware.alibi.R;
import com.buzzware.alibi.databinding.ActivitySelectSignUpBinding;
import com.buzzware.alibi.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {

    ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setView();
        setListener();
    }

    private void setListener() {
        binding.closeIV.setOnClickListener(v-> {
            finish();
        });
        binding.signInTV.setOnClickListener(view -> {
            startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
            finish();
        });
    }

    private void setView() {
        String text = "<font color='#000000'>Already have an account?</font><font color='#35d187'> Sign in</font>";
        binding.signInTV.setText(Html.fromHtml(text), TextView.BufferType.SPANNABLE);
    }
}