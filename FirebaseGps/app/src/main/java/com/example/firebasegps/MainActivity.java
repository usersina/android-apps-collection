package com.example.firebasegps;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.firebasegps.adapters.ProductAdapter;
import com.example.firebasegps.models.Product;
import com.example.firebasegps.services.FirebaseAuthService;
import com.example.firebasegps.services.FirestoreDBService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "MyMainActivity";

    private FirebaseAuthService firebaseAuthService;
    private FirestoreDBService firestoreDBService;

    AlertDialog.Builder alertDialog;

    RecyclerView productRv;
    ProductAdapter productAdapter;
    ArrayList<Product> productList;
    FloatingActionButton floatingActionButton;

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

        // -- Setup a listener for fab click
        floatingActionButton = findViewById(R.id.floating_action_btn);
        this.setupFloatingActionButtonClick();

        // -- Setup a listener for firestore db changes
        firebaseAuthService = new FirebaseAuthService();
        firestoreDBService = new FirestoreDBService();
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

    /**
     * Opens up an _AlertDialog_ with a form to add a new product.
     */
    private void setupFloatingActionButtonClick() {
        floatingActionButton.setOnClickListener(view -> {
            view = getLayoutInflater().inflate(R.layout.input_dialog, null);
            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setTitle("Add a new product");
            alertDialog.setCancelable(false);

            final EditText nameEt = (EditText) view.findViewById(R.id.name_et);
            final EditText priceEt = (EditText) view.findViewById(R.id.price_et);

            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Add product", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    try {
                        String name = nameEt.getText().toString();
                        double price = Double.valueOf(priceEt.getText().toString());
                        if (name.isEmpty() || priceEt.getText().toString().isEmpty()) { return ; }
                        firestoreDBService.addNewProduct(name, price);
                    } catch (Exception e) {
                        Log.d(TAG, "Cannot save the product, something is wrong with the input!");
                    }
                }
            });
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    alertDialog.dismiss();
                }
            });
            alertDialog.setView(view);
            alertDialog.show();
        });
    }

    /**
     * Setup firebase functionality such as anonymously signing in and getting
     * firestore data as a stream.
     */
    private void setupFirebaseServices() {
        firebaseAuthService.login().addOnCompleteListener(result -> {
            // -- A successful authentication, make a recycler view listener
            if (result.isSuccessful()) {
                // -- A one-time data fetch
//                firestoreDBService.productCollectionReference().get().addOnCompleteListener(task -> {
//                    if (task.isSuccessful()) {
//                        // productList.clear();
//                        ;productList.addAll(firestoreDBService.getProductList(task.getResult()))
//                        Log.w(TAG, "Getting the updated list!");
//                        productAdapter.notifyDataSetChanged();
//                    } else {
//                        Log.w(TAG, "Error getting documents!", task.getException());
//                    }
//                });
                // -- A progressive data fetch
                firestoreDBService.productCollectionReference().addSnapshotListener((querySnapshot, e) -> {
                    if (querySnapshot.isEmpty()) return;
                    productList.clear();
                    productList.addAll(firestoreDBService.getProductList(querySnapshot));
                    productAdapter.notifyDataSetChanged();
                });
            // -- Authenticated failed, simply log the error stack
            } else {
                Log.w(TAG, "Error signing in!", result.getException());
            }
        });
    }
}