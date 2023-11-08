package com.example.prm392project.model;

public class Chat {

    private String msg;
    private int user_id;

    public Chat() {
    }


    public Chat(int uid, String msg) {
        this.user_id = uid;
        this.msg = msg;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
