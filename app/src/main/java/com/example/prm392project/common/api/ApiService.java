package com.example.prm392project.common.api;

import com.example.prm392project.model.Product;
import com.example.prm392project.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    //http://localhost:8080/user/uid?id=1
    ApiService apiService = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @GET("product/p")
    Call<Product> getProductById(@Query("id") int id);
    @GET("product")
    Call<List<Product>> getAllProducts();

    @POST("auth/register")
    Call<User> register(@Body User user);

}
