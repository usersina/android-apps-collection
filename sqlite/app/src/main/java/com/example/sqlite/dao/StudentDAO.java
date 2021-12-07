package com.example.sqlite.dao;

import android.content.Context;

import com.example.sqlite.utils.DBHandler;

public class StudentDAO {
    public static final String CREATE_TABLE = "CREATE TABLE students (id INTEGER PRIMARY KEY, firstname text, lastname text, classroom text);";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS students;";

    private final DBHandler dbHandler;

    public StudentDAO(Context context) {
        this.dbHandler = new DBHandler("products", 1, context, null);
    }
}
