package com.kmelx.kmemobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {
    Button CreateAcc;
    RequestQueue mRequestQueue;
    EditText full_name;
    EditText pass;
    EditText cpass;
    EditText email;

    private final String url = "http://kmelx.com/api/registerNew/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        CreateAcc= (Button) findViewById(R.id.create_account);
        full_name= (EditText) findViewById(R.id.username);
        pass= (EditText) findViewById(R.id.pasword);
        cpass= (EditText) findViewById(R.id.pasword2);
        email= (EditText) findViewById(R.id.email);

        CreateAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRequestQueue= VolleySingleton.getInstance().getmRequestQueue();
                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String token= response;

                        try {
                            JSONObject obj = new JSONObject(response);


                                Intent intento = new Intent (SignUp.this, Home.class);
                                startActivity(intento);

                            Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_SHORT).show();

                        } catch (Throwable t) {
                            Log.e("My App", "Could not parse malformed JSON: \"" + response + "\"");
                        }


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
                        map.put("first_name",full_name.getText().toString());
                        map.put("password",pass.getText().toString());
                        map.put("email",email.getText().toString());
                        map.put("username",email.getText().toString());
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



        //fin
    }
}
