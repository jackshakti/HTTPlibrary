package com.example.volly21.volleyexample;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.example.volly21.R;
import com.example.volly21.volleyexample.volley.ServiceRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Jackshakti
 */

public class MainJsonArray extends AppCompatActivity {

    private ServiceRequest mRequest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        postRequest("https://api.androidhive.info/contacts/");
    }

    private void postRequest(String Url) {


        HashMap<String, String> jsonParams = new HashMap<String, String>();
        //jsonParams.put("username", "");

        mRequest = new ServiceRequest(MainJsonArray.this);
        mRequest.makeServiceRequest(Url, Request.Method.GET, jsonParams, new ServiceRequest.ServiceListener() {
            @Override
            public void onCompleteListener(String response) {

                try {
                    JSONObject object = new JSONObject(response);
                    if (object.length() > 0) {


                        //Json Array
                        JSONArray jsonArray = object.getJSONArray("contacts");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject resObject = jsonArray.getJSONObject(i);

                            JSONArray jsonArray3 = resObject.getJSONArray("contacts3");
                            for (int j = 0; j < jsonArray3.length(); j++) {
                                JSONObject resObject3 = jsonArray3.getJSONObject(j);
                            }
                        }


                        JSONArray jsonArray1 = object.getJSONArray("contacts2");
                        for (int i = 0; i < jsonArray1.length(); i++) {
                            JSONObject resObject = jsonArray1.getJSONObject(i);
                        }
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
