package com.buzzware.alibi.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.buzzware.alibi.R;
import com.buzzware.alibi.classes.Constants;
import com.buzzware.alibi.databinding.ActivitySignInBinding;
import com.buzzware.alibi.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

public class SignInActivity extends BaseActivity {

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
        binding.signUpTV.setOnClickListener(view -> {
            startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
            finish();
        });

        binding.signInBT.setOnClickListener(view -> {
            Dexter.withContext(this).withPermissions(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
                    .withListener(new MultiplePermissionsListener() {
                @Override
                public void onPermissionsChecked(MultiplePermissionsReport report) {
                    if(report.areAllPermissionsGranted()) {
                        performValidation();
                    }else{

                    }
                }

                @Override
                public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                    token.continuePermissionRequest();
                }
            }).check();

        });
    }
    private void setView() {
        String text = "<font color='#000000'>Don't have an account?</font><font color='#35d187'> Sign up</font>";
        binding.signUpTV.setText(Html.fromHtml(text), TextView.BufferType.SPANNABLE);
    }

    private void performValidation() {
        if (TextUtils.isEmpty(binding.emailET.getText().toString())) {
            binding.emailET.setError("Field Required");
            return;
        }
        if (TextUtils.isEmpty(binding.passwordET.getText().toString())) {
            binding.passwordET.setError("Field Required");
            return;
        }

        performSignin();
    }

    private void performSignin() {
        if (isOnline()) {

            mAuth.signInWithEmailAndPassword(binding.emailET.getText().toString().replaceAll(" ", ""),
                    binding.passwordET.getText().toString())
                    .addOnCompleteListener(SignInActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                setUser(user);

                            } else {
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(SignInActivity.this, "Authentication failed. "
                                        + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    private void setUser(FirebaseUser user1){

        final DatabaseReference users_table = FirebaseDatabase.getInstance().getReference("users");

        users_table.child(user1.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User USER = dataSnapshot.getValue(User.class);
                USER.setId(user1.getUid());
                Constants.currentUser = USER;
                Toast.makeText(getApplicationContext(),"Sign in Successful",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                users_table.removeEventListener(this);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
    }
}