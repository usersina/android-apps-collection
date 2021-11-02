package com.example.intents;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class ImplicitManagerActivity extends AppCompatActivity {
    EditText numOrUrlInp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit_manager);
        numOrUrlInp = findViewById(R.id.numOrUrlInp);
    }


    public void onNumberCall(View view) {
        String myInput = numOrUrlInp.getText().toString();
        if(myInput.isEmpty()) return;
        Log.d("IMPLICIT", "onNumberCall: Call phone!");
        int permissionCheck = ContextCompat.checkSelfPermission(
            ImplicitManagerActivity.this,
            Manifest.permission.CALL_PHONE
        );
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                ImplicitManagerActivity.this,
                new String[]{Manifest.permission.CALL_PHONE}, 123
            );
        } else {
            Intent callIntent = new Intent(
                Intent.ACTION_CALL,
                Uri.parse("tel:" + myInput)
            );
            startActivity(callIntent);
        }
    }

    public void onPhoneDial(View view) {
        Intent dialIntent = new Intent(Intent.ACTION_DIAL);
        startActivity(dialIntent);
    }

    public void onWebsiteOpen(View view) {
        String myInput = numOrUrlInp.getText().toString();
        if(myInput.isEmpty()) return;
        if (!myInput.startsWith("http://") && !myInput.startsWith("https://"))
            myInput = "http://" + myInput;
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(myInput));
        startActivity(browserIntent);
    }

    public void onAppManage(View view) {
        Intent appManagerIntent = new Intent(Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS);
        startActivity(appManagerIntent);
    }

    public void onWifiManage(View view) {
        Intent wifiManager = new Intent(Settings.ACTION_WIFI_SETTINGS);
        startActivity(wifiManager);
    }
}
