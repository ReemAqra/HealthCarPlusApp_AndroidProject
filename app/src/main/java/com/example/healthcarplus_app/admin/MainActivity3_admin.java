package com.example.healthcarplus_app.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.healthcarplus_app.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity3_admin extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity3_admin);
        // ----------------------------- Navigation Section ------------------------------
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationId);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home_button) {
                return true;
            } else if (item.getItemId() == R.id.Search_button) {
                startActivity(new Intent(getApplicationContext(), SearchActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (item.getItemId() == R.id.sell_button) {
               // startActivity(new Intent(getApplicationContext(), AddProductActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (item.getItemId() == R.id.cost_button) {
                startActivity(new Intent(getApplicationContext(), MoneySafeActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            }
            return false;
        });
        // ----------------------------------------------------------------------------


    }
}