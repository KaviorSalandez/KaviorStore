package com.example.prm392project.model;

public class Chat {
    private int id;
    private String msg;

    public Chat() {
    }

    public Chat(int id, String msg) {
        this.id = id;
        this.msg = msg;
    }

    public Chat(String msg) {
        this.msg = msg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
