package com.buzzware.alibi.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.buzzware.alibi.R;
import com.buzzware.alibi.databinding.ActivitySelectSignUpBinding;

public class SelectSignUpActivity extends AppCompatActivity {

    ActivitySelectSignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySelectSignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setView();
        setListener();
    }

    private void setListener() {
        binding.signInBT.setOnClickListener(v->{
            startActivity(new Intent(SelectSignUpActivity.this,SignInActivity.class));
        });
    }

    private void setView() {
        String text = "<font color='#35d187'>Term of Service</font><font color='#c3c3cc'> and </font><font color='#35d187'>Privacy Policy</font>";
        binding.privacyTV.setText(Html.fromHtml(text), TextView.BufferType.SPANNABLE);
    }
}