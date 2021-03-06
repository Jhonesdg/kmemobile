package com.kmelx.kmemobile.Account;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.kmelx.kmemobile.Home;
import com.kmelx.kmemobile.R;
import com.kmelx.kmemobile.VolleySingleton;

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

    private final String url = "http://kmelx.com/api/registerUser/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        CreateAcc= (Button) findViewById(R.id.create_account);
        full_name= (EditText) findViewById(R.id.username);
        pass= (EditText) findViewById(R.id.apasword);
        cpass= (EditText) findViewById(R.id.pasword2);
        email= (EditText) findViewById(R.id.email);

        CreateAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRequestQueue= VolleySingleton.getInstance().getmRequestQueue();

                if(!full_name.getText().toString().equals("") &&
                        !pass.getText().toString().equals("") &&
                        !email.getText().toString().equals("")) {



                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject sign = new JSONObject(response);

                            if (sign.getString("username")!=""){
                                SharedPreferences sp = getSharedPreferences("spPersonalData", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sp.edit();
                                editor.putString("Username", String.valueOf(email.getText()));
                                editor.putString("Password", String.valueOf(pass.getText()));
                                Intent intento = new Intent (SignUp.this, Home.class);
                                startActivity(intento);
                            }else{
                                Toast.makeText(getApplicationContext(),sign.getString("status"),Toast.LENGTH_SHORT).show();
                            }

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

            }else{
                    Toast.makeText(SignUp.this, "Campos Vacios", Toast.LENGTH_SHORT).show();
                }
            }
        });



        //fin
    }
}
