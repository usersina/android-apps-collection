package com.example.sqlite.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sqlite.models.Product;
import com.example.sqlite.utils.DBHandler;

public class ProductDAO {
    public static final String CREATE_TABLE = "CREATE TABLE products (id INTEGER PRIMARY KEY, name TEXT, quantity INTEGER);";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS product;";

    private DBHandler dbHandler;

    public ProductDAO(Context context) {
        dbHandler = new DBHandler(context, null);
    }

    // create
    public long insert(Product product) {
        SQLiteDatabase db = dbHandler.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("id", product.getId());
        values.put("name", product.getName());
        values.put("quantity", product.getQuantity());

        long lastInsertId = db.insert("products", null, values);
        db.close();

        return lastInsertId;
    }

    // read
    // TODO: Update return type to a list of products
    public Cursor getAll() {
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        String req = "SELECT * FROM products";
        return db.rawQuery(req, null);
    }

    // update
    public void update(Product product) {
        SQLiteDatabase db = dbHandler.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("id", product.getId());
        values.put("name", product.getName());
        values.put("quantity", product.getQuantity());

        db.update("products", values, "id = ?", new String[]{product.getId().toString()});
    }

    // delete
    public void delete(Integer id) {
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        db.delete("products", "id = ?", new String[]{id.toString()});
    }
}
