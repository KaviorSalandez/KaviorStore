package com.example.prm392project.presentation.store.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.prm392project.R;
import com.example.prm392project.common.SliderData;
import com.example.prm392project.common.SliderVerticalAdapter;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class AboutDetailFragment extends Fragment {
    private TextView title;
    private TextView subTitle;
    private ImageButton back;
    private String titleContent;
    private String subTitleContent;

    public AboutDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getParentFragmentManager().setFragmentResultListener("storyKey", this, (requestKey, bundle) -> {
            // We use a String here, but any type that can be put in a Bundle is supported.
            titleContent = bundle.getString("storyTitle");
            subTitleContent = bundle.getString("storySubTitle");

            title.setText(titleContent);
            subTitle.setText(subTitleContent);
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        title = view.findViewById(R.id.aboutTitle);
        subTitle = view.findViewById(R.id.aboutSubTitle);
        back = view.findViewById(R.id.backHome);

//        initSlider(view);
//        back.setOnClickListener(it -> getParentFragmentManager().popBackStack());

    }

//    private void initSlider(View view) {
//        ArrayList<String> imageSlider = new ArrayList<>();
//        imageSlider.add("https://cf.shopee.vn/file/8353ca176db52037f00818ec85441ce2");
//        imageSlider.add("https://vn-test-11.slatic.net/p/f4debd693b9df0fb9531eca12350c015.png");
//        imageSlider.add("https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEg0zT-vU-BxW_sZwQLfhZsDP0uGf1UAQ1_FMUit8aHBPxGQ-42ggKcHVzJcDCDufkqR1_K08nz6LXlFBvcwdyfbVCdlF9cLme81BBRscgh2c85Ox0U_1CTMhE0bxCl3x9V8HQe-aT5DDmIuX5coJ0iGc_KX8HZOd-s7mnReWL_KbwQgRV41FX5H0YSw/s1600/f05ce6b17e612d14baca06076986ebe5.jpg=w700");
//        imageSlider.add("https://img.ws.mms.shopee.vn/vn-11134201-7qukw-li6rwxeb03bmfe");
//        imageSlider.add("https://vn-live-01.slatic.net/p/7ccb520854e72629dcd779974dd7afb1.jpg");
//        imageSlider.add("https://vn-test-11.slatic.net/p/05224f5cb2d7fdde7fa2c2fd8a7c0d7f.png");
//        imageSlider.add("https://mcdn.coolmate.me/image/August2022/dac-tinh-ao-ba-lo.jpg");
//        ArrayList<SliderData> sliderDataArrayList = new ArrayList<>();
//        SliderView sliderView = view.findViewById(R.id.imageAboutSlider);
//        for (String imageItem : imageSlider) {
//            sliderDataArrayList.add(new SliderData(imageItem));
//        }
//        SliderVerticalAdapter adapter = new SliderVerticalAdapter(sliderDataArrayList);
//        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
//        sliderView.setSliderAdapter(adapter);
//        sliderView.setScrollTimeInSec(3);
//        sliderView.setAutoCycle(true);
//        sliderView.startAutoCycle();
//    }
}