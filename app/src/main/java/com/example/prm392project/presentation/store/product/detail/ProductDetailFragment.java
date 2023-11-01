package com.example.prm392project.presentation.store.product.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.prm392project.R;
import com.example.prm392project.common.SliderData;
import com.example.prm392project.common.SliderVerticalAdapter;
import com.example.prm392project.common.api.ApiService;
import com.example.prm392project.databinding.LayoutProductBinding;
import com.example.prm392project.model.Product;
import com.example.prm392project.presentation.store.product.ProductItemAdapter;
import com.smarteist.autoimageslider.SliderView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailFragment extends Fragment {

    LayoutProductBinding binding;
    Product pDetail = new Product();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = LayoutProductBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       // initSlider();
        // fake


        Bundle bundle = getArguments();
        if (bundle != null) {
            String value = bundle.getString("pId");
            getProductDetail(Integer.parseInt(value));

        }
        else{
            Toast.makeText(requireContext(), "Not found!",Toast.LENGTH_SHORT).show();
        }


        binding.back.setOnClickListener(it -> getParentFragmentManager().popBackStack());
    }

//    private void initSlider() {
//        ArrayList<String> imageSlider = new ArrayList<>();
//        imageSlider.add("https://cf.shopee.vn/file/8353ca176db52037f00818ec85441ce2");
//        imageSlider.add("https://vn-test-11.slatic.net/p/f4debd693b9df0fb9531eca12350c015.png");
//        imageSlider.add("https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEg0zT-vU-BxW_sZwQLfhZsDP0uGf1UAQ1_FMUit8aHBPxGQ-42ggKcHVzJcDCDufkqR1_K08nz6LXlFBvcwdyfbVCdlF9cLme81BBRscgh2c85Ox0U_1CTMhE0bxCl3x9V8HQe-aT5DDmIuX5coJ0iGc_KX8HZOd-s7mnReWL_KbwQgRV41FX5H0YSw/s1600/f05ce6b17e612d14baca06076986ebe5.jpg=w700");
//        imageSlider.add("https://img.ws.mms.shopee.vn/vn-11134201-7qukw-li6rwxeb03bmfe");
//        imageSlider.add("https://vn-live-01.slatic.net/p/7ccb520854e72629dcd779974dd7afb1.jpg");
//        imageSlider.add("https://vn-test-11.slatic.net/p/05224f5cb2d7fdde7fa2c2fd8a7c0d7f.png");
//        imageSlider.add("https://mcdn.coolmate.me/image/August2022/dac-tinh-ao-ba-lo.jpg");
//        ArrayList<SliderData> sliderDataArrayList = new ArrayList<>();
//        ImageView imgView = binding.imageProductSlider;
//
//    }
private void getProductDetail(int id) {
    ApiService.apiService.getProductById(id)
            .enqueue(new Callback<Product>() {

                @Override
                public void onResponse(Call<Product> call, Response<Product> response) {
                    pDetail = response.body();
                    Glide.with(requireContext()).load(pDetail.getImageUrl()).into(binding.imageProductSlider);
                    binding.productName.setText(pDetail.getProductName());
                    binding.productPrice.setText(String.valueOf(pDetail.getPrice()));
                    binding.productQuantity.setText(String.valueOf("Số lượng: " +pDetail.getQuantity()));
                    binding.productDescription.setText(pDetail.getDescription());

                }

                @Override
                public void onFailure(Call<Product> call, Throwable t) {

                }
            });
}
}
