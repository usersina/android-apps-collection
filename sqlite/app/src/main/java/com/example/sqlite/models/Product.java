package com.example.sqlite.models;

public class Product {
    Integer id;
    String name;
    Integer quantiy;

    public Product() { }

    public Product(Integer id, String name, Integer quantity) {
        super();
        this.id = id;
        this.name = name;
        this.quantiy = quantity;
    }

    public Integer getId(){
        return this.id;
    };
    public String getName() {
        return this.name;
    };
    public int getQuantity() {
        return this.quantiy;
    };

    public void setId(int id) {
        this.id = id;
    }
    public void setName() {
        this.name = name;
    }
    public void setQuantiy() {
        this.quantiy = quantiy;
    }
}
