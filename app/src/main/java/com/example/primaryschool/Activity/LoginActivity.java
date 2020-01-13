package com.example.primaryschool.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.primaryschool.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    Button button;
    EditText etLoginUser , etLoginPassword ;
    String mobile_no,password;
    private static final String TAG = "App";
    SharedPreferences prefs;
    int j;
    String user_id, username, user_email, user_mobile, user_phone, user_type, user_address, user_address_type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
       prefs = PreferenceManager.getDefaultSharedPreferences(this);

        button = findViewById(R.id.button_login);
        etLoginUser = findViewById(R.id.userName);
        etLoginPassword = findViewById(R.id.passWord);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                startActivity(intent);*/
                mobile_no = etLoginUser.getText().toString();
                password = etLoginPassword.getText().toString();
                login();
            }
        });
    }
    private void login() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.10.108:1000/api/admin/login",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("Invalid Credentials"))
                        {
                            Toast.makeText(LoginActivity.this,response,Toast.LENGTH_LONG).show();
                        }

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            user_id = jsonObject.getString("users_id");
                            username = jsonObject.getString("users_name_bn");
                            user_email = jsonObject.getString("users_email");
                            user_mobile = jsonObject.getString("users_mobile");
                            user_phone = jsonObject.getString("users_phone");
                            user_type = jsonObject.getString("users_type");
                            user_address = jsonObject.getString("users_address");
                            user_address_type = jsonObject.getString("users_address_type");

                            prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

                            prefs.edit().putString("users_id",String.valueOf(user_id)).apply();
                            prefs.edit().putString("username",String.valueOf(username)).apply();
                            prefs.edit().putString("user_email",String.valueOf(user_email)).apply();
                            prefs.edit().putString("user_mobile",String.valueOf(user_mobile)).apply();
                            prefs.edit().putString("user_phone",String.valueOf(user_phone)).apply();
                            prefs.edit().putString("user_type",String.valueOf(user_type)).apply();
                            prefs.edit().putString("user_address",String.valueOf(user_address)).apply();
                            prefs.edit().putString("user_address_type",String.valueOf(user_address_type)).apply();




                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(intent);
                            //checkTotalStudent();
                            //prefs.edit().putString("total_student", "").apply();

                            //Toast.makeText(LoginActivity.this,school_id,Toast.LENGTH_LONG).show();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Log.d(TAG, "res: " + response);
                        //



                       /*Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);*/

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String , String> params = new HashMap<>();
                params.put("mobile_no",mobile_no);
                params.put("password",password);
                return params;
            }
        };

        Volley.newRequestQueue(this).add(stringRequest);
    }
}
