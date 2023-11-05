package com.example.prm392project.presentation.store.chat;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.prm392project.model.Chat;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ChatShareRfr {

    private static final String PREF_NAME = "chat_pref";
    private static final String KEY_ITEMS = "chats";
    public static void saveItems(Context context, List<Chat> items) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String json = gson.toJson(items);

        editor.putString(KEY_ITEMS, json);
        editor.apply();
    }
    public static List<Chat> getItems(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String json = sharedPreferences.getString(KEY_ITEMS, null);

        if (json != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Chat>>() {}.getType();
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
