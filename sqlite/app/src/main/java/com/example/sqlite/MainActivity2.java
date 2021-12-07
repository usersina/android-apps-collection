package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.sqlite.dao.ProductDAO;
import com.example.sqlite.dao.StudentDAO;
import com.example.sqlite.models.Product;
import com.example.sqlite.models.Student;

import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    EditText firstnameEt, lastnameEt, controlEt;
    RadioGroup classroomRg;
    StudentDAO studentDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        firstnameEt = findViewById(R.id.firstname_et);
        lastnameEt = findViewById(R.id.lastname_et);
        classroomRg = findViewById(R.id.classroom_rg);
        controlEt = findViewById(R.id.result_et);

        studentDAO = new StudentDAO(getApplicationContext());
    }

    private void clearInputs() {
        firstnameEt.setText("");
        lastnameEt.setText("");
        classroomRg.clearCheck();
    }

    public void onAdd(View view) {
        try {
            String firstname = firstnameEt.getText().toString();
            String lastname = lastnameEt.getText().toString();
            int selectedId = classroomRg.getCheckedRadioButtonId();
            if(selectedId == -1 || firstnameEt.getText().toString().isEmpty() || lastnameEt.getText().toString().isEmpty()) return;

            RadioButton selectedClassroomRb = findViewById(selectedId);
            String classroom = selectedClassroomRb.getText().toString();

            Student student = new Student(firstname, lastname, classroom);

            Log.d("MainActivity2", "onAdd: " + student);
            studentDAO.insert(student);
            this.clearInputs();
            this.onShow(view);
        } catch (Exception exception) {
            exception.printStackTrace();
            System.err.println("Could not insert product!");
        }
    }

    public void onDelete(View view) {
        try {
            Log.d("MainActivity", "Deleting");
            Integer id = Integer.valueOf(controlEt.getText().toString());
            studentDAO.delete(id);
            this.clearInputs();
            this.onShow(view);
        } catch (Exception exception) {
            exception.printStackTrace();
            System.err.println("Could not delete product!");
        }
    }

    public void onShow(View view) {
        try {
            Log.d("MainActivity", "Showing");
            List<Student> myList = studentDAO.getAll();
            controlEt.setText(myList.toString());
        } catch (Exception exception) {
            exception.printStackTrace();
            System.err.println("Could not show products!");
        }
    }

    public void onUpdate(View view) {
        try {
            Integer id = Integer.valueOf(controlEt.getText().toString());
            String firstname = firstnameEt.getText().toString();
            String lastname = lastnameEt.getText().toString();
            int selectedId = classroomRg.getCheckedRadioButtonId();
            if(selectedId == -1 || firstnameEt.getText().toString().isEmpty() || lastnameEt.getText().toString().isEmpty()) return;

            RadioButton selectedClassroomRb = findViewById(selectedId);
            String classroom = selectedClassroomRb.getText().toString();

            Student student = new Student(id, firstname, lastname, classroom);
            Log.d("MainActivity2", "onUpdate: " + student);
            studentDAO.update(student);
            this.clearInputs();
            this.onShow(view);
        } catch (Exception exception) {
            exception.printStackTrace();
            System.err.println("Could not insert product!");
        }
    }

    // -- Additional
    public void onSearch(View view) {

    }

}