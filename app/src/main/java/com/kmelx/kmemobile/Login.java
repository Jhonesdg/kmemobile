package com.kmelx.kmemobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    Button btnLogin;
    EditText username;
    EditText passwd;
    JsonObjectRequest array;
    RequestQueue mRequestQueue;
    private final String url = "http://kmelx.com/api/login_kme/";
    private final String TAG = "PRUEBITA";
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_login);
        username= (EditText) findViewById(R.id.username);
        passwd= (EditText) findViewById(R.id.pasword);
        btnLogin= (Button) findViewById(R.id.btnlogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            mRequestQueue= VolleySingleton.getInstance().getmRequestQueue();
                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String token= response;
                        Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> map = new HashMap<String,String>();
                        map.put("username",username.getText().toString());
                        map.put("password",passwd.getText().toString());
                        return map;
                    }

                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String,String> params = new HashMap<String,String>();
                        params.put("Content-Type","application/x-www-form-urlencoded");
                        return params;
                    }
                };
                mRequestQueue.add(request);
            }
        });





    }
    public void Signup(View v){
        Intent intento = new Intent (Login.this, SignUp.class);
        startActivity(intento);

    }
}
