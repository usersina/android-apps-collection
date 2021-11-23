package com.example.menus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    LinearLayout firstLayout;
    LinearLayout secondLayout;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        firstLayout = findViewById(R.id.first);
        secondLayout = findViewById(R.id.second);
        textView = findViewById(R.id.text);

        // -- 1. Register the views
        registerForContextMenu(firstLayout);
        registerForContextMenu(textView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        // -- 2. Associate each view to its menu
        switch (v.getId()) {
            case R.id.first: {
                System.out.println("First menu");
                getMenuInflater().inflate(R.menu.context_menu1, menu);
                break;
            }
            case R.id.text: {
                System.out.println("Second menu");
                getMenuInflater().inflate(R.menu.context_menu2, menu);
                break;
            }
        }
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            // First menu options
            case R.id.red_item:
                secondLayout.setBackgroundColor(getResources().getColor(R.color.red));
                break;
            case R.id.yellow_item:
                secondLayout.setBackgroundColor(getResources().getColor(R.color.yellow));
                break;

            // Second menu options
            case R.id.small_size_item:
                textView.setTextSize(14);
                break;
            case R.id.medium_size_item:
                textView.setTextSize(30);
                break;
        }
        return super.onContextItemSelected(item);
    }
}