package com.example.prm392project.presentation.store.cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392project.control.SharePreferenceManager;
import com.example.prm392project.databinding.FragmentFavoriteBinding;
import com.example.prm392project.model.ItemCart;
import com.example.prm392project.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment {

    private FragmentFavoriteBinding binding;


    private RecyclerView recyclerCart;


    private ItemCartAdapter adapter;

    public CartFragment() {
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
        binding = FragmentFavoriteBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.back.setOnClickListener(it -> getParentFragmentManager().popBackStack());
        recyclerCart = binding.cartList;
        List<ItemCart> poList = SharePreferenceManager.getItems(requireContext());
        adapter = new ItemCartAdapter(requireContext(), poList);
        binding.cartList.setAdapter(adapter);
        binding.cartList.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.clearCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharePreferenceManager.clearItems(requireContext());
                poList.clear();
                adapter.notifyDataSetChanged();
            }
        });
        
    }
}