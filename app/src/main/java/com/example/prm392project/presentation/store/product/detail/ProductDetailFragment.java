package com.example.prm392project.presentation.store.product.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.prm392project.common.SliderData;
import com.example.prm392project.common.SliderVerticalAdapter;
import com.example.prm392project.databinding.LayoutProductBinding;
import com.smarteist.autoimageslider.SliderView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductDetailFragment extends Fragment {

    LayoutProductBinding binding;

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
        Glide.with(this).load("https://vn-live-01.slatic.net/p/7ccb520854e72629dcd779974dd7afb1.jpg").into(binding.imageProductSlider);
        GridView gridview = binding.colorPick;
        gridview.setAdapter(new ColorAdapter(requireContext()));

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
}
