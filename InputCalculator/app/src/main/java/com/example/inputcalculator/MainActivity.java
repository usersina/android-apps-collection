package com.example.inputcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText input1;
    EditText input2;
    RadioGroup operatorRg;
    Button submitBtn;
    TextView resText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // -- UI Bindings
        input1 = findViewById(R.id.input1);
        input2 = findViewById(R.id.input2);
        operatorRg = findViewById(R.id.operatorRg);
        submitBtn = findViewById(R.id.submitBtn);
        resText = findViewById(R.id.resText);

        // -- Bind Button click event
        /* submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Hello toasty world!", Toast.LENGTH_LONG).show();
            }
        }); */
    }

    public void onCalculate(View v) {
        String viewText;
        int radioIdx = operatorRg.indexOfChild(findViewById(operatorRg.getCheckedRadioButtonId()));
        Log.i("onCalculate", "Selected Idx: " + radioIdx);

        double value1;
        double value2;



        try {
            value1 = Double.parseDouble(input1.getText().toString());
        } catch (Exception e) {
            resText.setText("First value is empty!");
            return;
        }
        try {
            value2 = Double.parseDouble(input2.getText().toString());
        } catch (Exception e) {
            resText.setText("Second value is empty!");
            return;
        }

        switch (radioIdx) {
            case 0:
                // viewText = "add";
                viewText = String.valueOf((value1 + value2));
                break;
            case 1:
                // viewText = "subtract";
                viewText = String.valueOf((value1 - value2));
                break;
            case 2:
                // viewText = "multiply";
                viewText = String.valueOf((value1 * value2));
                break;
            case 3:
                // viewText = "divide";
                if (value2 == 0) {
                    viewText = "Cannot divide by 0";
                } else {
                    viewText = String.valueOf(value1 / value2);
                }
                break;
            default:
                viewText = "Please choose an operator!";
                break;
        }
        resText.setText(viewText);
    }
}