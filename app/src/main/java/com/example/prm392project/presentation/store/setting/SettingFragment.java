package com.example.prm392project.presentation.store.setting;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.prm392project.R;
import com.example.prm392project.databinding.FragmentSettingBinding;
import com.example.prm392project.presentation.login.LoginFragment;
import com.example.prm392project.presentation.store.chat.ChatShareRfr;

public class SettingFragment extends Fragment {
    public static String USER_FILE_NAME = "User";
    public static String NAME_KEY = "username";
    public static String PASS_KEY = "password";

    private FragmentSettingBinding binding;
    public SettingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSettingBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedPreferences modePreferences = requireActivity().getSharedPreferences(USER_FILE_NAME, Context.MODE_PRIVATE);
        TextView username = binding.username;
        TextView email = binding.email;
        String usn = modePreferences.getString(NAME_KEY, "123");
        username.setText(usn);
        email.setText(usn + "@gmail.com");
        binding.logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modePreferences.edit().putString(NAME_KEY, null).apply();
                modePreferences.edit().putString(PASS_KEY, null).apply();
//                Intent intent = new Intent(requireActivity(), LoginFragment.class);
//                startActivity(intent);

                // Xóa lịch sử chat trong sharepreference
                ChatShareRfr.clearItems(requireContext());

                FragmentManager fm = requireActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.wrapper, new LoginFragment(), null).commit();
            }
        });
    }
}