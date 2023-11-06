package com.example.prm392project.model;

import java.time.Instant;

public class Cart {
    private int id;
    private Double price;

    private String address;

    private String phone;

    private  int uId;

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    private  String note;

    public String getPhone() {
        return phone;
    }
    public Cart(int id, Double price, String address, String phone, String note, Instant orderDate, int uId) {
        this.id = id;
        this.price = price;
        this.address = address;
        this.phone = phone;
        this.note = note;
        this.orderDate = orderDate;
        this.uId = uId;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    private Instant orderDate;

    public Cart() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Instant getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Instant orderDate) {
        this.orderDate = orderDate;
    }
}
