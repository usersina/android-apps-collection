package com.example.firebasegps;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.firebasegps.adapters.ProductAdapter;
import com.example.firebasegps.models.Product;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView productRv;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    ProductAdapter productAdapter;
    ArrayList<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        mAuth.signInAnonymously();
        // mAuth.signOut();

        productRv = findViewById(R.id.products_rv);
//        productRv.setHasFixedSize(true);
        productRv.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        productList = new ArrayList<>();
        productAdapter = new ProductAdapter(getApplicationContext(), productList);
        productRv.setAdapter(productAdapter);

        db = FirebaseFirestore.getInstance();
        db.collection("products").get()
            .addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document: task.getResult()) {
                        Log.d("MainActivityOnCreate", document.getId() + " => " + document.getData());
                        Log.d("MainActivityOnCreate", (String) document.getData().get("name"));
                        Log.d("MainActivityOnCreate", String.valueOf(document.getData().get("price")));
                        productList.add(new Product(String.valueOf(document.getData().get("name")), Double.valueOf((Long) document.getData().get("price"))));
                    }
                    productAdapter.notifyDataSetChanged();
                } else {
                    Log.w("MainActivityOnCreate: ", "Error getting documents!", task.getException());
                }
            });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.gps_item:
                startActivity(new Intent(getApplicationContext(), GPSActivity.class));
                break;
            case R.id.wifi_item:
                startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                break;
            case R.id.exit_item:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}