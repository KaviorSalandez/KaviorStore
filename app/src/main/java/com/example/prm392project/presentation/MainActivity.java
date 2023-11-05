package com.example.prm392project.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.prm392project.R;
import com.example.prm392project.databinding.ActivityMainBinding;
import com.example.prm392project.presentation.login.LoginFragment;
import com.example.prm392project.presentation.store.PagerFragment;
import com.example.prm392project.presentation.store.cart.CartFragment;

public class MainActivity extends AppCompatActivity {
    private static final String CHANNEL_ID = "CHANNEL 1";
    private static final int NOTIFICATION_ID = 1;
    private static final int REQUEST_CODE = 1001;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
        );

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.POST_NOTIFICATIONS)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.POST_NOTIFICATIONS},101);
            }
        }

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.wrapper, new LoginFragment(), null).commit();


        // Kiểm tra Intent có dấu hiệu "openCartFragment"
        if (getIntent().getBooleanExtra("openCartFragment", false)) {
            openCartFragment();
        }
    }

    private void openCartFragment() {
        // Thay `R.id.fragment_container` bằng ID của container Fragment của bạn
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.wrapper, new CartFragment(), null).commit();
    }
}