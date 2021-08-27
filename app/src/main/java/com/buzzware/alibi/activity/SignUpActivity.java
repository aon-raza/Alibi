package com.buzzware.alibi.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.buzzware.alibi.R;
import com.buzzware.alibi.databinding.ActivitySelectSignUpBinding;
import com.buzzware.alibi.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends BaseActivity {

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

        binding.signUpBT.setOnClickListener(view -> {
            mDialog.show();
            performValidation();
        });
    }

    private void setView() {
        String text = "<font color='#000000'>Already have an account?</font><font color='#35d187'> Sign in</font>";
        binding.signInTV.setText(Html.fromHtml(text), TextView.BufferType.SPANNABLE);
    }

    private void performValidation() {
        if(TextUtils.isEmpty(binding.nameET.getText().toString())){
            binding.nameET.setError("Field Required");
            return;
        }
        if(TextUtils.isEmpty(binding.emailET.getText().toString())){
            binding.emailET.setError("Field Required");
            return;
        }
        if(TextUtils.isEmpty(binding.passwordET.getText().toString())){
            binding.passwordET.setError("Field Required");
            return;
        }

        performRegistration();
    }

    private void performRegistration() {
        if(isOnline()) {

            mAuth.createUserWithEmailAndPassword(binding.emailET.getText().toString(), binding.passwordET.getText().toString())
                    .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                addUserdetails(user);
                            } else {
                                mDialog.dismiss();
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(SignUpActivity.this, "Authentication failed. " +task.getException().getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    private void addUserdetails(final FirebaseUser user1){

        Map<String, String> userData= new HashMap<>();
        userData.put("name", binding.nameET.getText().toString());
        userData.put("email", binding.emailET.getText().toString());

        mDatabase.child("users").child(user1.getUid()).setValue(userData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                mDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Sign up Success!", Toast.LENGTH_SHORT).show();
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
    }
}