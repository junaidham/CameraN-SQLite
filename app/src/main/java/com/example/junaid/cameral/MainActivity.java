package com.example.junaid.cameral;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


/**
 * Created by Junaid Ahmad on 01/02/2018
 */

public class MainActivity extends AppCompatActivity {
    Button btnCamera,btnGallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //find view Id
        initView();

        // onClick

        //if we do this thing using INTENT then permission is not required
//        boolean result = checkPermission();
//        if (result) {
//            writeCalendarEvent();
//        }

    }

    public void onCamera(View view){
        Intent intent = new Intent(getApplicationContext(), Camera.class);
        startActivity(intent);
        finish();
    }

    public void onList(View view) {
        Intent intent = new Intent(getApplicationContext(), ListDisplay.class);
        startActivity(intent);
        finish();

    }


    private void initView() {
        btnCamera = findViewById(R.id.Camera);
        btnGallery = findViewById(R.id.Gallery);

    }







}

