package com.example.menus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    LinearLayout firstLayout;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        firstLayout = findViewById(R.id.first);
        textView = findViewById(R.id.text);

        registerForContextMenu(firstLayout);
        registerForContextMenu(textView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
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
}