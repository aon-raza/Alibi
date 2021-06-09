package com.buzzware.alibi.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.buzzware.alibi.R;
import com.buzzware.alibi.databinding.ActivitySignInBinding;

public class SignInActivity extends AppCompatActivity {

    ActivitySignInBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setView();
        setListener();
    }
    private void setListener() {
        binding.closeIV.setOnClickListener(v-> {
            finish();
        });
    }
    private void setView() {
        String text = "<font color='#000000'>Don't have an account?</font><font color='#35d187'> Sign up</font>";
        binding.signUpTV.setText(Html.fromHtml(text), TextView.BufferType.SPANNABLE);
    }
}