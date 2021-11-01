package com.example.intents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IntentActivity extends AppCompatActivity {
    Button calculateBtn;
    Button cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);

        Intent intent = getIntent();
        calculateBtn = findViewById(R.id.calculateBtn);
        cancelBtn = findViewById(R.id.cancelBtn);

        calculateBtn.setOnClickListener(v -> {
            Integer int1 = Integer.valueOf(intent.getStringExtra("int1"));
            Integer int2 = Integer.valueOf(intent.getStringExtra("int2"));
            String sign = intent.getStringExtra("operation");

            Integer res = 0;
            if(sign.charAt(0) == '+') {
                res = int1 + int2;
            } else if (sign.charAt(0) == '-') {
                res = int1 - int2;
            }

            Intent resIntent = new Intent();
            resIntent.putExtra("res", res.toString());

            setResult(RESULT_OK, resIntent);
            finish();
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resIntent = new Intent();
                setResult(RESULT_CANCELED, resIntent);
                finish();
            }
        });
    }
}