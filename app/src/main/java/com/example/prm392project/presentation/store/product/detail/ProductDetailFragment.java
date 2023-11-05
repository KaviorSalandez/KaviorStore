package com.example.prm392project.presentation.store.product.detail;

import android.Manifest;
import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.prm392project.R;
import com.example.prm392project.common.SliderData;
import com.example.prm392project.common.SliderVerticalAdapter;
import com.example.prm392project.common.api.ApiService;
import com.example.prm392project.control.SharePreferenceManager;
import com.example.prm392project.databinding.LayoutProductBinding;
import com.example.prm392project.model.ItemCart;
import com.example.prm392project.model.Product;
import com.example.prm392project.presentation.MainActivity;
import com.example.prm392project.presentation.store.cart.CartFragment;
import com.example.prm392project.presentation.store.product.ProductItemAdapter;
import com.smarteist.autoimageslider.SliderView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailFragment extends Fragment {
    // Kênh notification
    private static final String CHANNEL_ID = "CHANNEL 1";
    private static final int NOTIFICATION_ID = 1;
    private static final int REQUEST_CODE = 1001;
    LayoutProductBinding binding;
    Product pDetail = new Product();

    public  boolean checkHaveCart = false;
    Context context;
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
                    binding.imgAddToCart.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            SharePreferenceManager share = new SharePreferenceManager();
                            List<ItemCart> savedItems = share.getItems(requireContext());
                            ItemCart cart = new ItemCart();
                            cart.Name = pDetail.productName;
                            cart.Img = pDetail.getImageUrl();
                            cart.ProductId = pDetail.getId();
                            cart.price = pDetail.getPrice();
                            cart.quantity = 1;
                            for (ItemCart i : savedItems) {
                                if(i.getProductId() == cart.ProductId){
                                    i.quantity+=1;
                                    checkHaveCart = true;
                                }
                            }
                            if(checkHaveCart == true){
                                checkHaveCart = false;
                            }else {
                                savedItems.add(cart);
                            }
                            SharePreferenceManager.saveItems(requireContext(), savedItems);

                            // thông báo
                            MakeNotification2(cart);
                        }
                    });

                }

                @Override
                public void onFailure(Call<Product> call, Throwable t) {

                }
            });
}


    public void MakeNotification2(ItemCart item){
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
        //tao intent để mở Main Activity
        Intent intent = new Intent(requireContext(), MainActivity.class);
        intent.putExtra("openCartFragment", true);
        PendingIntent pendingIntent = PendingIntent.getActivity(requireContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_MUTABLE);
        //xay dung thong bao
        NotificationCompat.Builder notificationManager  = new NotificationCompat.Builder(requireContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.baseline_notifications_active_24)
                .setContentTitle("Thông báo")
                .setContentText("Đặt hàng thành công sản phẩm "+item.getName())
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



