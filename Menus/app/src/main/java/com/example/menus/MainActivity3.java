package com.example.menus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity3 extends AppCompatActivity {
    ArrayList<String> contactList = new ArrayList<String>(
        Arrays.asList("Contact1", "Contact2", "Contact3", "Contact4")
    );
    ListView contactListview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        // ----------------------- Simple Array Adapter ----------------------- //
        contactListview = findViewById(R.id.contacts_listview);
        registerForContextMenu(contactListview);
//        contactListview.setOnItemLongClickListener((parent, view, position, id) -> {
//            System.out.println("Long clicking!");
//            openContextMenu(view);
//            return true;
//        });
        ArrayAdapter myAdapter = new ArrayAdapter(
            getApplicationContext(),
            android.R.layout.simple_list_item_1,
            contactList
        );
        contactListview.setAdapter(myAdapter);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.contacts_context_menu, menu);
    }
}