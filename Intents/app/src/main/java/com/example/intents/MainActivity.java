package com.example.intents;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    EditText inp1;
    EditText inp2;
    RadioGroup radioGroup;
    Button calcBtn;
    TextView resText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inp1 = findViewById(R.id.inp1);
        inp2 = findViewById(R.id.inp2);
        radioGroup = findViewById(R.id.radio);
        calcBtn = findViewById(R.id.calcBtn);
        resText = findViewById(R.id.resText);

        calcBtn.setOnClickListener(v -> {
            int selectedId = radioGroup.getCheckedRadioButtonId();
            if(selectedId == -1 || inp1.getText().toString().isEmpty() || inp1.getText().toString().isEmpty()) return;

            RadioButton selectedBtn = findViewById(selectedId);

            Intent myIntent = new Intent(getApplicationContext(), IntentActivity.class);
            myIntent.putExtra("int1", inp1.getText().toString());
            myIntent.putExtra("int2", inp2.getText().toString());
            myIntent.putExtra("operation", selectedBtn.getText().toString());

            startActivityForResult(myIntent, 1);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if(resultCode == RESULT_OK) {
                    resText.setText(data.getStringExtra("res"));
                } else {
                    resText.setText("Operation cancelled!");
                }
                break;
            default:
                break;
        }
    }
}