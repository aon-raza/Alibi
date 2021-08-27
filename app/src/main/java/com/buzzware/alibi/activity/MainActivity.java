package com.buzzware.alibi.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.buzzware.alibi.R;
import com.buzzware.alibi.classes.Constants;
import com.buzzware.alibi.databinding.ActivityMainBinding;
import com.buzzware.alibi.databinding.ActivitySignInBinding;
import com.buzzware.alibi.fragment.EventsMapsFragment;

import nl.psdcompany.duonavigationdrawer.widgets.DuoDrawerToggle;

public class MainActivity extends BaseActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setView();
        setDrawer();
        setListeners();

        loadFragment(new EventsMapsFragment());
    }

    private void setView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        binding.includeView.otherIV.setImageResource(R.drawable.bell_icon);
    }

    private void setDrawer() {
        DuoDrawerToggle drawerToggle = new DuoDrawerToggle(this, binding.drawer,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        binding.drawer.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }
    private void setListeners() {
        binding.includeView.menuIV.setOnClickListener(v -> {
            checkOpenOrCloseDrawer();
        });
//        binding.navView.drawerMenuIV.setOnClickListener(v -> {
//            checkOpenOrCloseDrawer();
//        });

        binding.navView.createEventBT.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AddLocationActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            checkOpenOrCloseDrawer();
        });

        binding.includeView.otherIV.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, NotificationsActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            checkOpenOrCloseDrawer();
        });

        binding.navView.logoutBtn.setOnClickListener(view -> {
            if(isOnline()) {
                mAuth.signOut();
                Constants.currentUser = null;
                Toast.makeText(getApplicationContext(),"Signed Out!",Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(MainActivity.this, SignInActivity.class);
                startActivity(intent1);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        });

        binding.navView.activeEventsTV.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, ActiveEventsActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            checkOpenOrCloseDrawer();
        });

        binding.navView.rostersTV.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, RostersActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            checkOpenOrCloseDrawer();
        });

        binding.navView.calenderTV.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, CalenderActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            checkOpenOrCloseDrawer();
        });
    }

    private void checkOpenOrCloseDrawer(){
        if (binding.drawer.isDrawerOpen()) {
            binding.drawer.closeDrawer(Gravity.LEFT);
        } else {
            binding.drawer.openDrawer(Gravity.LEFT);
        }
    }

    private void loadFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(binding.mainFragmentContainer.getId(), fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
    }
}