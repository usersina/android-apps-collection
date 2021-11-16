package com.example.lists;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.lists.adapters.ContactsAdapter;
import com.example.lists.listeners.RecyclerTapListener;
import com.example.lists.models.Contact;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
//    String[] daysList = {"Monday", "Tuesday", "Wednesday"};
//    ListView days_list_view;

    List<Contact> contactList = new ArrayList<Contact>();
    RecyclerView contactsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ----------------------- Simple Array Adapter ----------------------- //
        //
//        days_list_view = findViewById(R.id.days_list_view);
//        ArrayAdapter myAdapter = new ArrayAdapter(
//            getApplicationContext(),
//            android.R.layout.simple_list_item_1,
//            daysList
//        );
//        days_list_view.setAdapter(myAdapter);

        // ----------------------- Custom Adapter ----------------------- //
        // -- Bind & fill list
        contactsRecyclerView = findViewById(R.id.contacts_rv);
        contactList.add(new Contact("Mario Bros", "21 342 123"));
        contactList.add(new Contact("Luigi Bros", "52 345 678"));

        // -- Scroll manager
        LinearLayoutManager contactsLayoutManager = new LinearLayoutManager(
                getApplicationContext(),
                LinearLayoutManager.VERTICAL,
                false
        );
        contactsRecyclerView.setLayoutManager(contactsLayoutManager);

        // -- Add an onTap listener
        contactsRecyclerView.addOnItemTouchListener(
                new RecyclerTapListener(
                        getApplicationContext(),
                        contactsRecyclerView,
                        new RecyclerTapListener.ClickListener() {
                            @Override
                            public void onTap(View view, int position) {
                                Contact contact = contactList.get(position);
                                Toast.makeText(getApplicationContext(), contact.getName(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onLongTap(View view, int position) {}
                        })
        );

        // -- Create & set the adapter
        ContactsAdapter contactsAdapter = new ContactsAdapter(contactList);
        contactsRecyclerView.setAdapter(contactsAdapter);
    }
}