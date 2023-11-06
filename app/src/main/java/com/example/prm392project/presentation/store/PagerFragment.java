package com.example.prm392project.presentation.store;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.Manifest;
import com.example.prm392project.R;
import com.example.prm392project.control.SharePreferenceManager;
import com.example.prm392project.databinding.FragmentPagerBinding;
import com.example.prm392project.model.ItemCart;
import com.example.prm392project.presentation.MainActivity;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;

public class PagerFragment extends Fragment {


    private FragmentPagerBinding binding;
    private PagerViewModel viewModel;


    public PagerFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPagerBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(PagerViewModel.class);
        List<PagerViewModel.Tab> tabs = viewModel.getTabs();
        PagerAdapter adapter = new PagerAdapter(tabs, (AppCompatActivity) requireActivity());
        binding.viewpager.setAdapter(adapter);
        new TabLayoutMediator(binding.navigation, binding.viewpager, (tab, position) -> {
            tab.setIcon(getResources().getDrawable(tabs.get(position).icon));
        }).attach();
        binding.viewpager.setUserInputEnabled(false);


        binding.navigation.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Integer position = tab.getPosition();
                if (position != null) {
                    binding.viewpager.setCurrentItem(position);
                    binding.navigation.selectTab(binding.navigation.getTabAt(position));
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }



}