package com.example.sqlite.dao;

import android.content.Context;

import com.example.sqlite.utils.DBHandler;

public class ProductDAO {
    public static final String CREATE_TABLE = "CREATE TABLE products (id INTEGER PRIMARY KEY, name TEXT, quantity INTEGER);";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS product;";

    private DBHandler dbHandler;

    public ProductDAO(Context context) {
        dbHandler = new DBHandler(context, null);
    }
}
