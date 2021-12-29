package com.example.volly21.volleyexample.volley;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bgi166 on 18-01-2018.
 */

public class ServiceRequest {

    private Context context;
    private ServiceListener mServiceListener;
    private StringRequest stringRequest;

    public interface ServiceListener {
        void onCompleteListener(String response);

        void onErrorListener();
    }

    public ServiceRequest(Context mContext) {
        this.context = mContext;
    }

    public void cancelRequest() {
        if (stringRequest != null) {
            stringRequest.cancel();
        }
    }

    public void makeServiceRequest(final String url, int method, final HashMap<String, String> param, ServiceListener listener) {

        System.out.println("------url------" + url);
        System.out.println("------param------" + param);

        this.mServiceListener = listener;
        stringRequest = new StringRequest(method, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                System.out.println("----------response--------" + response);
                try {
                    mServiceListener.onCompleteListener(response);
                } catch (Exception e) {
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                        //Toast.makeText(context, "Network connection is slow.Please try again.", Toast.LENGTH_SHORT).show();
                    } else if (error instanceof AuthFailureError) {
                        //Toast.makeText(context, "AuthFailureError", Toast.LENGTH_SHORT).show();
                    } else if (error instanceof ServerError) {
                        // Toast.makeText(context, "ServerError", Toast.LENGTH_SHORT).show();
                    } else if (error instanceof NetworkError) {
                        //Toast.makeText(context, "NetworkError", Toast.LENGTH_SHORT).show();
                    } else if (error instanceof ParseError) {
                        //Toast.makeText(context, "ParseError", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                }
                mServiceListener.onErrorListener();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return param;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/x-www-form-urlencoded");
                return headers;
            }

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                Map headers = response.headers;
                String cookie = (String) headers.get("Set-Cookie");
                //Log.e("cookie", "cookie" + cookie);
                return super.parseNetworkResponse(response);
            }
        };

        //to avoid repeat request Multiple Time
        DefaultRetryPolicy retryPolicy = new DefaultRetryPolicy(0, -1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(60000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        stringRequest.setShouldCache(false);
        AppController.getInstance().addToRequestQueue(stringRequest);
    }

}
