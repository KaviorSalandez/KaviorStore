package com.example.prm392project.common.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.prm392project.model.Cart;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SharedPreferenceManager {
    private static final String PREF_NAME = "item_pref";
    private static final String KEY_ITEMS = "items";
    public static void saveItems(Context context, List<Cart> items) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String json = gson.toJson(items);

        editor.putString(KEY_ITEMS, json);
        editor.apply();
    }
    public static List<Cart> getItems(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String json = sharedPreferences.getString(KEY_ITEMS, null);

        if (json != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Cart>>() {}.getType();
            return gson.fromJson(json, type);
        }

        return new ArrayList<>();
    }
    public static void clearItems(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Ghi đè danh sách đã lưu bằng một danh sách trống
        editor.putString(KEY_ITEMS, null);

        // Hoặc có thể sử dụng danh sách trống để ghi đè
        // List<Item> emptyItemList = new ArrayList<>();
        // String emptyItemListJson = new Gson().toJson(emptyItemList);
        // editor.putString(KEY_ITEMS, emptyItemListJson);

        editor.apply();
    }
}
