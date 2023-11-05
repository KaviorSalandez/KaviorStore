package com.example.prm392project.presentation.store.chat;

import com.example.prm392project.model.Chat;

import java.util.ArrayList;
import java.util.List;

public class ConstantChat {

    public static final List<Chat> chatList = new ArrayList<>();
    static {
        chatList.add(new Chat("Xin chao"));
    }
}
