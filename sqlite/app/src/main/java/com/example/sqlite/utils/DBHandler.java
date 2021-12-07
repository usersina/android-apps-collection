package com.example.sqlite.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sqlite.dao.ProductDAO;
import com.example.sqlite.dao.StudentDAO;

public class DBHandler extends SQLiteOpenHelper {

    public DBHandler(String dbname, int version, Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, dbname, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)  {
        db.execSQL(ProductDAO.CREATE_TABLE);
        db.execSQL(StudentDAO.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ProductDAO.DROP_TABLE);
        db.execSQL(ProductDAO.DROP_TABLE);
        this.onCreate(db);
    }
}
