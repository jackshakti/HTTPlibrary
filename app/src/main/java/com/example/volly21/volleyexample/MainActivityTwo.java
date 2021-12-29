package com.example.volly21.volleyexample;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.volly21.R;


public class MainActivityTwo extends AppCompatActivity {

    TextView textView;

    String sName = "", sEmail = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_two);

        textView = (TextView) findViewById(R.id.mainTwo_name_textView);

        // getting data from the main Activity
        Intent intent = getIntent();
        sName = intent.getStringExtra("Intent-Name");  // getting json data
        sEmail = intent.getStringExtra("Intent-input"); // getting input data

        //set the getting data's
        textView.setText(sName +" \n "+sEmail);


    }
}
