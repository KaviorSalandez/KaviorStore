package com.example.prm392project.model;

public class ItemCart {

    public int ProductId;
    public  String  Name;
    public  int quantity ;
    public String Img;
    public  double price;

    public int getProductId() {
        return ProductId;
    }

    public ItemCart(int productId, String name, int quantity, String img, double price) {
        ProductId = productId;
        Name = name;
        this.quantity = quantity;
        Img = img;
        this.price = price;
    }
    public ItemCart() {

    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String img) {
        Img = img;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
