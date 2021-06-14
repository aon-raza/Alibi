package com.buzzware.alibi.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.WindowManager;
import android.widget.TextView;

import com.buzzware.alibi.databinding.ActivityNotificationsBinding;
import com.buzzware.alibi.databinding.ActivitySplashBinding;

public class NotificationsActivity extends AppCompatActivity {

    ActivityNotificationsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding= ActivityNotificationsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setView();
        setListener();
    }

    private void setView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        binding.includeView.titleTV.setText("New Invite");

        String eventName = "“Target Shopping Day”";
        String text = "<font color='#000000'>Cindy has invited you to the </font><br>" +
                "<font color='#35d187'>" + eventName + "</font><br>" +
                "<font color='#000000'>event.</font>";
        binding.inviteTV.setText(Html.fromHtml(text), TextView.BufferType.SPANNABLE);
    }

    private void setListener() {

        binding.includeView.backIV.setOnClickListener(view -> {
            finish();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });

        binding.includeView.otherTV.setOnClickListener(view -> {
            startActivity(new Intent(NotificationsActivity.this, SignInActivity.class));
            finish();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });
    }
}