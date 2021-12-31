package com.example.firebasegps.services;

import com.example.firebasegps.models.Product;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class FirestoreDBService {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public CollectionReference productCollectionReference() {
        return db.collection("products");
    }

    public ArrayList<Product> getProductList(QuerySnapshot querySnapshot) {
        ArrayList<Product> myList = new ArrayList<>();
        for (QueryDocumentSnapshot document: querySnapshot) {
            // myList.add(new Product(String.valueOf(document.getData().get("name")), Double.valueOf((Long) document.getData().get("price"))));
            myList.add(document.toObject(Product.class));
        }
        return myList;
    };

}
