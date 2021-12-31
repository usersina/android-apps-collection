package com.example.firebasegps.services;

import android.util.Log;

import com.example.firebasegps.models.Product;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FirestoreDBService {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public CollectionReference productCollectionReference() {
        return db.collection("products");
    }

    public ArrayList<Product> getProductList(QuerySnapshot querySnapshot) {
        ArrayList<Product> myList = new ArrayList<>();
        for (QueryDocumentSnapshot document: querySnapshot) {
            Log.d("test", "setupFirebaseServices: " + document.getId());
            // myList.add(document.toObject(Product.class)); // Does not consider the id
            myList.add(new Product(
                document.getId(),
                String.valueOf(document.getData().get("name")),
                Double.valueOf(document.getData().get("price").toString())
            ));
        }
        return myList;
    };

    public void addNewProduct(String name, double price) {
        Map<String, Object> dataToSave = new HashMap<String, Object>();
        dataToSave.put("name", name);
        dataToSave.put("price", price);
        db.collection("products").add(dataToSave);
    }

    public void updateProduct(String id, String name, double price) {
        Map<String, Object> dataToSave = new HashMap<String, Object>();
        dataToSave.put("name", name);
        dataToSave.put("price", price);
        db.collection("products").document(id).set(dataToSave);
    }

    public void deleteProduct(String id) {
        db.collection("products").document(id).delete();
    }
}
