package com.example.prm392project.model;

import java.util.List;

public class Product {
    private String id;
    private String productName;
    private Integer price;
    private List<String> color;
    private List<String> size;
    private String material;
    private List<String> images;
    private String thumbnail;

    public Product() {
    }

    public Product(String id, String productName, Integer price, List<String> color, List<String> size, String material, List<String> images, String thumbnail) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.color = color;
        this.size = size;
        this.material = material;
        this.images = images;
        this.thumbnail = thumbnail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public List<String> getColor() {
        return color;
    }

    public void setColor(List<String> color) {
        this.color = color;
    }

    public List<String> getSize() {
        return size;
    }

    public void setSize(List<String> size) {
        this.size = size;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
