package com.example.prm392project.presentation.store.profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.prm392project.R;
import com.example.prm392project.common.api.ApiService;
import com.example.prm392project.databinding.FragmentLoginBinding;
import com.example.prm392project.databinding.FragmentProfileBinding;
import com.example.prm392project.model.User;
import com.example.prm392project.presentation.login.LoginFragment;
import com.example.prm392project.presentation.store.setting.SettingFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    User userLogin;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userLogin = getUserLoggedIn();
        binding.backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = requireActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.wrapper, new SettingFragment(), null).commit();
            }
        });
        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin.setAddress(binding.txbUserAddress.getText().toString());
                userLogin.setPhone(binding.txbUserPhone.getText().toString());
                updateUserInfo(userLogin);


            }
        });
    }
    private User getUserLoggedIn(){

        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("USER_TOKEN", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");
        ApiService.apiService.getLoggedInUserProfile("Bearer " + token)
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.body() != null){
                            userLogin = response.body();
                            binding.txbUserFullName.setText(userLogin.getUsername());
                            binding.txbUserPhone.setText(userLogin.getPhone());
                            binding.txbUserAddress.setText(userLogin.getAddress());
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });
        return userLogin;
    }

    private void updateUserInfo(User user){
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("USER_TOKEN", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");
        ApiService.apiService.updateUser("Bearer " + token, user)
                .enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() == 200) {
                            Toast.makeText(requireContext(), "Câp nhật thành công.", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(requireContext(), "Cập nhật thất bại.", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(requireContext(), "Cập nhật thất bại.", Toast.LENGTH_SHORT).show();
                    }
                });
    }




}
