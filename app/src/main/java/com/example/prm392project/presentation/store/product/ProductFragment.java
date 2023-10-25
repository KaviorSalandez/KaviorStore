package com.example.prm392project.presentation.store.product;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392project.R;
import com.example.prm392project.databinding.FragmentProductBinding;
import com.example.prm392project.model.Product;
import com.example.prm392project.presentation.store.product.detail.ProductDetailFragment;

import java.util.ArrayList;
import java.util.List;

public class ProductFragment extends Fragment {
    public List<Product> productList = new ArrayList<>();
    public List<String> colorList = new ArrayList<>();
    public List<String> sizeList = new ArrayList<>();
    public List<String> imageList = new ArrayList<>();
    private RecyclerView recyclerProduct;
    private ProductItemAdapter productAdapter ;

    private FragmentProductBinding binding;
    public ProductFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProductBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initProdcts();
        recyclerProduct = binding.productList;
        productAdapter = new ProductItemAdapter(requireContext(), productList);
        recyclerProduct.setAdapter(productAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(requireContext(),2);
        recyclerProduct.setLayoutManager(layoutManager);
        productAdapter.setOnClickListener((position, p) -> {
            FragmentManager fm = requireActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction().setCustomAnimations(
                    R.anim.slide_in,  // enter
                    R.anim.fade_out,  // exit
                    R.anim.fade_in,   // popEnter
                    R.anim.slide_out  // popExit
            );
            transaction.replace(R.id.wrapper, new ProductDetailFragment(), null).addToBackStack(null).commit();
        });
    }

    private void initProdcts(){
        colorList.add("Trắng");
        colorList.add("Xanh biển");
        colorList.add("Be");
        colorList.add("Xanh đậm");
        colorList.add("Đen");
        colorList.add("Nâu");
        colorList.add("Xanh mint");
        colorList.add("Xanh rêu");
        colorList.add("Xanh tím");
        sizeList.add("S");
        sizeList.add("M");
        sizeList.add("L");
        sizeList.add("XL");
        sizeList.add("2XL");
        sizeList.add("3XL");
        sizeList.add("4XL");
        imageList.add("https://img.ws.mms.shopee.vn/vn-11134207-7r98o-lkqk86doy0g00b");
        productList.add(new Product("1", "T-Shirt Cotton 220GSM", 179000, colorList, sizeList, "Cotton", imageList, "https://img.ws.mms.shopee.vn/vn-11134207-7r98o-lkqk86doy0g00b"));
        productList.add(new Product("2", "T-Shirt Cotton 220GSM", 179000, colorList, sizeList, "Cotton", imageList, "https://mcdn.coolmate.me/image/July2023/mceclip0_67.jpg"));
        productList.add(new Product("3", "T-Shirt Cotton 220GSM", 179000, colorList, sizeList, "Cotton", imageList, "https://img.ws.mms.shopee.vn/vn-11134207-7r98o-lkqk86doy0g00b"));
        productList.add(new Product("4", "T-Shirt Cotton 220GSM", 179000, colorList, sizeList, "Cotton", imageList, "https://img.ws.mms.shopee.vn/vn-11134207-7r98o-lkqk86doy0g00b"));
        productList.add(new Product("5", "T-Shirt Cotton 220GSM", 179000, colorList, sizeList, "Cotton", imageList, "https://mcdn.coolmate.me/image/July2023/mceclip0_67.jpg"));
        productList.add(new Product("6", "T-Shirt Cotton 220GSM", 179000, colorList, sizeList, "Cotton", imageList, "https://img.ws.mms.shopee.vn/vn-11134207-7r98o-lkqk86doy0g00b"));
        productList.add(new Product("7", "T-Shirt Cotton 220GSM", 179000, colorList, sizeList, "Cotton", imageList, "https://img.ws.mms.shopee.vn/vn-11134207-7r98o-lkqk86doy0g00b"));
        productList.add(new Product("8", "T-Shirt Cotton 220GSM", 179000, colorList, sizeList, "Cotton", imageList, "https://img.ws.mms.shopee.vn/vn-11134207-7r98o-lkqk86doy0g00b"));
        productList.add(new Product("9", "T-Shirt Cotton 220GSM", 179000, colorList, sizeList, "Cotton", imageList, "https://mcdn.coolmate.me/image/July2023/mceclip0_67.jpg"));
        productList.add(new Product("10", "T-Shirt Cotton 220GSM", 179000, colorList, sizeList, "Cotton", imageList, "https://img.ws.mms.shopee.vn/vn-11134207-7r98o-lkqk86doy0g00b"));

    }
}