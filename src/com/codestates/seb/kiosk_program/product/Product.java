package com.codestates.seb.kiosk_program.product;

public class Product {
    private int productId;
    private String name;
    private int amount;
    private int price;

    public Product(String name, int amount, int price) {
        this.name = name;
        this.amount = amount;
        this.price = price;
    }

    public Product(int productId, String name, int amount, int price) {
        this.productId = productId;
        this.name = name;
        this.amount = amount;
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public int getPrice() {
        return price;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
