package com.example.prm392project.model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

public class Cart {
    private int id;
    private Double price;

    private String address;

    private String phone;
    private String note;

    public String getPhone() {
        return phone;
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

    public Cart() {
    }

    public Cart(int id, Double price, String address, String phone, String note) {
        this.id = id;
        this.price = price;
        this.address = address;
        this.phone = phone;
        this.note = note;
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

}
