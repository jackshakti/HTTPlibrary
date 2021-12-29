package com.example.volly21.volleyexample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.example.volly21.R;
import com.example.volly21.volleyexample.volley.ServiceRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private ServiceRequest mRequest;

    private Button button;
    private EditText editText;

    String name = "", email = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button1);
        editText = (EditText)findViewById(R.id.et_input);

//        postRequest("https://api.androidhive.info/volley/person_object.json");
        postRequest("http://www.mocky.io/v2/5b9e34e63200001100db944e");

        //iconstant
        //postRequest(Iconstant.url);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(MainActivity.this, MainActivityTwo.class);

                //Passing data from one activity to another activity
                intent.putExtra("Intent-Name", name);   // data from json
                intent.putExtra("Intent-input", editText.getText().toString());  // data from input
                startActivity(intent);

            }
        });

    }


    private void postRequest(String Url) {


        HashMap<String, String> jsonParams = new HashMap<String, String>();
        //jsonParams.put("username", "");

        mRequest = new ServiceRequest(MainActivity.this);
        mRequest.makeServiceRequest(Url, Request.Method.GET, jsonParams, new ServiceRequest.ServiceListener() {
            @Override
            public void onCompleteListener(String response) {

                try {
                    JSONObject object = new JSONObject(response);
                    if (object.length() > 0) {

                        //Json object
                        name = object.getString("name");
                        email = object.getString("email");
                        //Print the value
                        System.out.println("---------------name------------" + name);
                        System.out.println("---------------email------------" + email);


                        button.setVisibility(View.VISIBLE);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onErrorListener() {
            }
        });
    }


}
