package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sqlite.dao.ProductDAO;
import com.example.sqlite.models.Product;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText idEt, nameEt, quantityEt;
    TextView productsTv;

    ProductDAO productDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        idEt = findViewById(R.id.id_inp);
        nameEt = findViewById(R.id.name_inp);
        quantityEt = findViewById(R.id.qty_inp);
        productsTv = findViewById(R.id.products_tv);

        productDAO = new ProductDAO(getApplicationContext());
    }

    private void clearInputs() {
        idEt.setText("");
        nameEt.setText("");
        quantityEt.setText("");
    }

    public void onAdd(View view) {
        try {
            Integer id = Integer.valueOf(idEt.getText().toString());
            String name = nameEt.getText().toString();
            Integer quantity = Integer.valueOf(quantityEt.getText().toString());

            Product product = new Product(id, name, quantity);

            Log.d("MainActivity", "Adding: " + product.toString());
            productDAO.insert(product);
            this.clearInputs();
        } catch (Exception exception) {
            exception.printStackTrace();
            System.err.println("Could not insert product!");
        }
    }

    public void onDelete(View view) {
        try {
            Log.d("MainActivity", "Deleting");
            Integer id = Integer.valueOf(idEt.getText().toString());
            productDAO.delete(id);
            this.clearInputs();
        } catch (Exception exception) {
            exception.printStackTrace();
            System.err.println("Could not delete product!");
        }
    }

    public void onShow(View view) {
        try {
            Log.d("MainActivity", "Showing");
            List<Product> myList = productDAO.getAll();
//            for (item: myList) {
//                Log.d("MainActivity", "Item");
//
//            }
            Log.d("MainActivity", myList.toString());
        } catch (Exception exception) {
            exception.printStackTrace();
            System.err.println("Could not show products!");
        }
    }

}