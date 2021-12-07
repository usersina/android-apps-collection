package com.example.sqlite.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sqlite.models.Student;
import com.example.sqlite.utils.DBHandler;

import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    public static final String CREATE_TABLE = "CREATE TABLE students (id INTEGER PRIMARY KEY, firstname text, lastname text, classroom text);";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS students;";

    private final DBHandler dbHandler;

    public StudentDAO(Context context) {
        this.dbHandler = new DBHandler("students", 1, context, null);
    }

    // create
    public long insert(Student student) {
        SQLiteDatabase db = dbHandler.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("firstname", student.getFirstname());
        values.put("lastname", student.getLastname());
        values.put("classroom", student.getClassroom());

        long lastInsertId = db.insert("students", null, values);
        db.close();
        return lastInsertId;
    }

    // read
    public List<Student> getAll() {
        List<Student> result = new ArrayList<>();
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        String req = "SELECT * FROM students";

        Cursor cursor = db.rawQuery(req, null);
        if (cursor.getCount() == 0) return result;

        while (cursor.moveToNext()) {
            result.add(
                new Student(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)
                )
            );
        }
        cursor.close();
        db.close();
        return result;
    }

    // update
    public void update(Student student) {
        SQLiteDatabase db = dbHandler.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("id", student.getId());
        values.put("firstname", student.getFirstname());
        values.put("lastname", student.getLastname());
        values.put("classroom", student.getClassroom());

        db.update("students", values, "id = ?", new String[]{student.getId().toString()});
    }

    // delete
    public void delete(Integer id) {
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        db.delete("students", "id = ?", new String[]{id.toString()});
    }
}
