package com.example.inputcalculator;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LifeCycle extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("dsi", "Resumed");
        int i = 15;
        Toast.makeText(getApplicationContext(), "Hello World: " + i, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("dsi", "Stopped");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("dsi", "Destroyed");
    }
}
