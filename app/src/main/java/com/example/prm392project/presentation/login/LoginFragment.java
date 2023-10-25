package com.example.prm392project.presentation.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.prm392project.R;
import com.example.prm392project.databinding.FragmentLoginBinding;
import com.example.prm392project.presentation.store.PagerFragment;

public class LoginFragment extends Fragment {
    public static String USER_FILE_NAME = "User";
    public static String NAME_KEY = "username";
    public static String PASS_KEY = "password";

    private FragmentLoginBinding binding;
    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText userNameEdt = binding.idEdtUserName;
        EditText passwordEdt = binding.idEdtPassword;

        binding.idBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                String userName = userNameEdt.getText().toString();
                String password = passwordEdt.getText().toString();

                if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {
                    Toast.makeText(requireContext(), "Please enter user name and password", Toast.LENGTH_SHORT).show();
                } else {
                    loginUser(userName, password);
                }
                loginUser(userName, userName);
            }
        });
    }

    private void loginUser(String userName1, String password1) {
        SharedPreferences modePreferences = requireActivity().getSharedPreferences(USER_FILE_NAME, Context.MODE_PRIVATE);
        String saveUsername = modePreferences.getString(NAME_KEY, "123");
        String savePassword = modePreferences.getString(PASS_KEY, "123");
        if (userName1 != null && password1 != null && userName1.equals(saveUsername) && password1.equals(savePassword)) {

            modePreferences.edit().putString(NAME_KEY, userName1).apply();
            modePreferences.edit().putString(PASS_KEY, password1).apply();
            FragmentManager fm = requireActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.wrapper, new PagerFragment(), null).commit();

        } else {
            Toast.makeText(requireActivity(), "làm lại đi", Toast.LENGTH_LONG).show();
        }
    }
}