package com.example.prm392project.model;

    public class Product {

        public int id;

        public String productName;

        public String description;

        public Double price;

        public String imageUrl;

        public int quantity;

        public Category category;

        public Product() {
        }



        public Product(int id, String productName, Double price, String imageUrl) {
            this.id = id;
            this.productName = productName;
            this.price = price;
            this.imageUrl = imageUrl;
        }


        public Product(int id, String productName, String description, Double price, String imageUrl, int quantity, Category category) {
            this.id = id;
            this.productName = productName;
            this.description = description;
            this.price = price;
            this.imageUrl = imageUrl;
            this.quantity = quantity;
            this.category = category;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public Category getCategory() {
            return category;
        }

        public void setCategory(Category category) {
            this.category = category;
        }
    }
