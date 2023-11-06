package com.example.prm392project.presentation.store.home;

import android.Manifest;
import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.prm392project.R;
import com.example.prm392project.common.SliderData;
import com.example.prm392project.common.SliderHorizontalAdapter;
import com.example.prm392project.control.SharePreferenceManager;
import com.example.prm392project.databinding.FragmentHomeBinding;

import com.example.prm392project.model.ItemCart;
import com.example.prm392project.presentation.MainActivity;
import com.example.prm392project.presentation.store.chat.ChatActivity;

import com.example.prm392project.presentation.store.cart.CartFragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.smarteist.autoimageslider.SliderView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements OnMapReadyCallback {
    private static final String CHANNEL_ID = "CHANNEL 1";
    private static final int NOTIFICATION_ID = 1;
    private static final int REQUEST_CODE = 1001;
    public List<ItemCart> listItemCart;

    private FragmentHomeBinding binding;
    private MapView mapView;
    public HomeFragment() {
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
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        mapView = binding.mapViewHcm;
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initSlider();
        initCard();
        binding.logo.setOnClickListener(v -> {
            Uri uri = Uri.parse("https://www.coolmate.me/"); // missing 'http://' will cause crashed
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });
        listItemCart = SharePreferenceManager.getItems(requireContext());
        if(listItemCart != null && listItemCart.size()>0){
            // thông báo
           // Toast.makeText(requireContext(), "aaaaaa", Toast.LENGTH_SHORT).show();
            MakeNotification3();
        }
        binding.chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChatActivity.class);
                startActivity(intent);
            }
        });

//        moveToDetail(binding.storyHeader, binding.storyTitle, binding.storyShortDes);
//        moveToDetail(binding.storyHeader1, binding.storyTitle1, binding.storyShortDes1);
//        moveToDetail(binding.storyHeader2, binding.storyTitle2, binding.storyShortDes2);
        binding.cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = requireActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction().setCustomAnimations(
                        R.anim.slide_in,  // enter
                        R.anim.fade_out,  // exit
                        R.anim.fade_in,   // popEnter
                        R.anim.slide_out  // popExit
                );
                transaction.replace(R.id.wrapper, new CartFragment(), null).addToBackStack(null).commit();
            }
        });
    }

//    void moveToDetail(View clickView, TextView title, TextView subTitle){
//        clickView.setOnClickListener(v -> {
//            Bundle result = new Bundle();
//            result.putString("storyTitle", title.getText().toString());
//            result.putString("storySubTitle", subTitle.getText().toString());
//            // The child fragment needs to still set the result on its parent fragment manager.
//            getParentFragmentManager().setFragmentResult("storyKey", result);
//            FragmentManager fm = requireActivity().getSupportFragmentManager();
//            FragmentTransaction transaction = fm.beginTransaction().setCustomAnimations(
//                    R.anim.slide_in,  // enter
//                    R.anim.fade_out,  // exit
//                    R.anim.fade_in,   // popEnter
//                    R.anim.slide_out  // popExit
//            );
//            transaction.replace(R.id.wrapper, new AboutDetailFragment(), null).addToBackStack(null).commit();
//        });
//    }

    private void initSlider() {
        ArrayList<String> imageSlider = new ArrayList<>();
        imageSlider.add("https://akkogear.com.vn/wp-content/uploads/2020/06/ban-phim-co-akko-3108-v2-monets-pond-akko-switch-v2-05.jpg?fbclid=IwAR3octFx6FYcsBfmWMlJvNceIFwyvjzBT1vmKG69Z5fwWT_JnCmanqAN6fo");
        imageSlider.add("https://akkogear.com.vn/wp-content/uploads/2023/03/ban-phim-akko-5108b-plus-demon-slayer-kamado-tanjirou-12-800x800.jpg");
        imageSlider.add("https://akkogear.com.vn/wp-content/uploads/2022/12/kit-ban-phim-monsgeek-m1-qmk-03.jpg");
        imageSlider.add("https://akkogear.com.vn/wp-content/uploads/2021/03/ban-phim-akko-3068-v2-2021-year-of-the-ox-04.jpg");
        imageSlider.add("https://cafefcdn.com/203337114487263232/2021/5/3/shark-tank-4-tap-1-coolmate-7-16200045691081324100286.jpg");
        imageSlider.add("https://akkogear.com.vn/wp-content/uploads/2020/12/AKKO_3084_Silent_Bluetooth_00-800x800.jpg");
        imageSlider.add("https://akkogear.com.vn/wp-content/uploads/2020/08/akko-3096-midnight-03-800x800.jpg");
        ArrayList<SliderData> sliderDataArrayList = new ArrayList<>();
        SliderView sliderView = binding.imageSlider;
        for (String imageItem : imageSlider) {
            sliderDataArrayList.add(new SliderData(imageItem));
        }
        SliderHorizontalAdapter adapter = new SliderHorizontalAdapter(sliderDataArrayList);
        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
        sliderView.setSliderAdapter(adapter);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();
    }

    private void initCard(){
        Log.d("ntt", "onClick: map");
        CardView map = binding.cardMap;
        map.setOnClickListener(v -> {
            Log.d("ntt", "onClick: map click");
//                Intent intent = new Intent(requireActivity(), MapsActivity.class);
//                startActivity(intent);
        });
        String imageUrl1 = "https://mcdn.coolmate.me/uploads/April2022/Screen_Shot_2022-03-29_at_17.25_1.png";
        Picasso.get().load(imageUrl1).into(binding.storyPic);

        String imageUrl2 = "https://mcdn.coolmate.me/uploads/April2022/1-8_14.jpg";
        Picasso.get().load(imageUrl2).into(binding.storyPic1);

        String imageUrl3 = "https://mcdn.coolmate.me/uploads/December2021/3_(1)_1_(1).jpg";
        Picasso.get().load(imageUrl3).into(binding.storyPic2);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        LatLng location = new LatLng(21.013180426269333, 105.52654631092821); // Ví dụ: Tọa độ TP.HCM
        googleMap.addMarker(new MarkerOptions().position(location).title("Kavior Store in Ha Noi"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15)); // Zoom vào vị trí cụ thể
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    public void MakeNotification3(){
        //create channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = requireContext().getString(R.string.channel_name);
            String description = requireContext().getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = requireContext().getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
        // Tạo intent để mở MainActivity với dấu hiệu "openCartFragment"
        Intent intent = new Intent(requireContext(), MainActivity.class);
        intent.putExtra("openCartFragment", true);
        PendingIntent pendingIntent = PendingIntent.getActivity(requireContext(), 0, intent, PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);

        //xay dung thong bao
        NotificationCompat.Builder notificationManager  = new NotificationCompat.Builder(requireContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.baseline_notifications_active_24)
                .setContentTitle("Thông báo")
                .setContentText("Có đơn hàng trong sản phẩm của bạn !!!")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        // Set full-screen intent
        notificationManager.setFullScreenIntent(pendingIntent, true);
        //thong bao
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(requireContext());
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            notificationManagerCompat.notify(NOTIFICATION_ID, notificationManager.build());
        } else {
            ActivityCompat.requestPermissions((Activity) requireContext(), new String[]{Manifest.permission.POST_NOTIFICATIONS}, REQUEST_CODE);
        }
    }
}

