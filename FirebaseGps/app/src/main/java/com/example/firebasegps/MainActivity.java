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
import com.example.firebasegps.services.FirebaseAuthService;
import com.example.firebasegps.services.FirestoreDBService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "MyMainActivity";

    private FirebaseAuthService firebaseAuthService;
    private FirestoreDBService firestoreDBService;

    RecyclerView productRv;
    ProductAdapter productAdapter;
    ArrayList<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // -- Configure the recycler view
        productRv = findViewById(R.id.products_rv);
        productRv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        // -- Configure the array adapter
        productList = new ArrayList<>();
        productAdapter = new ProductAdapter(getApplicationContext(), productList);
        productRv.setAdapter(productAdapter);

        // -- Setup a listener for firestore db changes
        this.setupFirebaseServices();
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

    private void setupFirebaseServices() {
        firebaseAuthService = new FirebaseAuthService();
        firebaseAuthService.login().addOnCompleteListener(result -> {
            // -- A successful authentication, make a recycler view listener
            if (result.isSuccessful()) {
                firestoreDBService = new FirestoreDBService();
                firestoreDBService.productCollectionReference().get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        productList.addAll(firestoreDBService.getProductList(task.getResult()));
                        productAdapter.notifyDataSetChanged();
                    } else {
                        Log.w(TAG, "Error getting documents!", task.getException());
                    }
                });
            // -- Authenticated failed, simply log the error stack
            } else {
                Log.w(TAG, "Error signing in!", result.getException());
            }
        });
    }
}