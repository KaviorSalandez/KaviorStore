package com.example.prm392project.presentation.store.cart;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392project.R;
import com.example.prm392project.control.SharePreferenceManager;
import com.example.prm392project.databinding.FragmentFavoriteBinding;
import com.example.prm392project.model.ItemCart;
import com.example.prm392project.model.Product;
import com.example.prm392project.presentation.store.product.ProductFragment;

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
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Thay `R.id.fragment_container` bằng ID của container Fragment của bạn
                PagerFragment productFragment = new PagerFragment();
                FragmentManager fm = requireActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.wrapper, productFragment, null).addToBackStack(null).commit();

            }
        });
        recyclerCart = binding.cartList;
        List<ItemCart> poList = SharePreferenceManager.getItems(requireContext());

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {

                /*/ / Lấy vị trí của mục được di chuyển và mục đích đến
                int fromPosition = viewHolder.getAdapterPosition();
                int toPosition = target.getAdapterPosition();

                // Di chuyển mục trong Adapter
                mAdapter.onItemMove(fromPosition, toPosition);*/

                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // Lấy vị trí của item bị trượt
                int position = viewHolder.getAdapterPosition();
                poList.remove(position);
                SharePreferenceManager.saveItems(requireContext(), poList);
                adapter.notifyItemRemoved(position);
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerCart);

        long total = 0;
        for (ItemCart i : poList
        ) {
            total += i.price * i.quantity;
        }
        binding.total.setText("Tổng tiền: " + String.valueOf(total) + "VND");

        adapter = new ItemCartAdapter(requireContext(), poList, new IOnClickQuantity() {
            @Override
            public void onClickListener() {
                binding.total.setText("Tổng tiền: " + String.valueOf(totalBill(poList)) + "VND");
            }
        });
        binding.cartList.setAdapter(adapter);
        binding.cartList.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.clearCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.total.setText("Tổng tiền: "+0+" VND");
                SharePreferenceManager.clearItems(requireContext());
                poList.clear();
                adapter.notifyDataSetChanged();

            }
        });
        binding.checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = requireActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction().setCustomAnimations(
                        R.anim.slide_in,  // enter
                        R.anim.fade_out,  // exit
                        R.anim.fade_in,   // popEnter
                        R.anim.slide_out  // popExit
                );
                transaction.replace(R.id.wrapper, new CheckOutFragment(), null).addToBackStack(null).commit();
            }
        });
    }

    private long totalBill(List<ItemCart> list) {
        long total = 0;
        for (ItemCart i : list
        ) {
            total += i.price * i.quantity;
        }
        return total;
    }
}