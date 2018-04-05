package com.wordpress.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.wordpress.R;
import com.wordpress.utils.MyData;

/**
 * Created by wail babou on 2016-12-24.
 */

public class Splash extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                MyData.addCategories();
                //sendTokenToServer(FirebaseInstanceId.getInstance().getToken());
                Intent i = new Intent(Splash.this, MainActivity.class);
                startActivity(i);
                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
    private void sendTokenToServer(final String token) {
        Log.e("token",token);
        String link = "http://192.168.1.2/myprojects/wordpress/?rest_route=/apnwp/register&os_type=android" +
                "&device_token="+token;
        final StringRequest request = new StringRequest(Request.Method.GET
                , link, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("fcm fcm",response);
                if(response
                        .equals("{\"isError\":\"false\",\"error\":\"200\",\"SuccessMessage\":\"User successfully added in wpuser table\"}")){

                    Log.e("Registration Service", "Response : Send Token Success");

                } else {
                    Log.e("Registration Service", "Response : Send Token Failed");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Registration Service", "Error :Send Token Failed"+ error);
            }
        });
        request.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 100000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 100000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
        Volley.newRequestQueue(this).add(request);
    }
}
