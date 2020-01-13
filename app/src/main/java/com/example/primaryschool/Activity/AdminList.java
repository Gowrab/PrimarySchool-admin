package com.example.primaryschool.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.example.primaryschool.R;

public class AdminList extends AppCompatActivity {
    SharedPreferences prefs;
    String user_address_type, id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_list);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        user_address_type = prefs.getString("users_address_type","defaultValue");
    }
}
