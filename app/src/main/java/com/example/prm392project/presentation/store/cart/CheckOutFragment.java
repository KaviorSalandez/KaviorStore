package com.example.prm392project.presentation.store.cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.prm392project.R;
import com.example.prm392project.control.SharePreferenceManager;
import com.example.prm392project.databinding.FragmentCheckoutBinding;
import com.example.prm392project.databinding.FragmentFavoriteBinding;
import com.example.prm392project.model.ItemCart;

import java.util.List;

public class CheckOutFragment extends Fragment {
    private FragmentCheckoutBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCheckoutBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.back.setOnClickListener(it -> getParentFragmentManager().popBackStack());
        List<ItemCart> poList = SharePreferenceManager.getItems(requireContext());
        long total = 0;
        for (ItemCart i : poList
        ) {
            total += i.price * i.quantity;
        }
        binding.txtTongtien.setText("Tổng tiền: " + String.valueOf(total) + "VND");
    }
}
