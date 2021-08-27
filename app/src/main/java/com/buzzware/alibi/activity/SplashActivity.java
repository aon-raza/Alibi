package com.buzzware.alibi.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.buzzware.alibi.R;
import com.buzzware.alibi.classes.Constants;
import com.buzzware.alibi.databinding.ActivitySplashBinding;
import com.buzzware.alibi.models.User;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SplashActivity extends BaseActivity {

    private static final int SPLASH_DISPLAY_LENGTH = 2000;
    ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//        setHandler();
        setUser();
    }

    private void setUser() {
        FirebaseUser user1 = mAuth.getCurrentUser();

        final DatabaseReference users_table = FirebaseDatabase.getInstance().getReference("users");

        if(isOnline()) {
            if(user1 != null){
                if(user1.getUid() != null){
                    users_table.child(user1.getUid()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            User USER = dataSnapshot.getValue(User.class);
                            USER.setId(user1.getUid());
                            Constants.currentUser = USER;
                            users_table.removeEventListener(this);
                            if (Constants.currentUser != null){
                                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                                finish();
                            }
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            System.out.println("The read failed: " + databaseError.getCode());
                        }
                    });
                }
            }
            else{
                setHandler();
            }
        }
    }

    private void setHandler() {
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent mainIntent = new Intent(SplashActivity.this,SelectSignUpActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

}