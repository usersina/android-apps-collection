package com.example.firebasegps.models;

public class Product {
    String id;
    String name;
    double price;

    public Product() {};

    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() {return id; }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
