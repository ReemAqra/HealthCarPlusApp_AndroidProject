package com.example.healthcarplus_app.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.healthcarplus_app.R;
import com.example.healthcarplus_app.admin.AddProductActivity;
import com.example.healthcarplus_app.admin.MoneySafeActivity;
import com.example.healthcarplus_app.admin.SearchAdminActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainCustomerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_customer);
        // ----------------------------- Navigation Section ------------------------------
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationId);
        bottomNavigationView.setSelectedItemId(R.id.home_btn);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home_btn) {
                return true;
            } else if (item.getItemId() == R.id.search_btn) {
                startActivity(new Intent(getApplicationContext(), SearchCustomerActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (item.getItemId() == R.id.star_btn) {
                startActivity(new Intent(getApplicationContext(), AddProductActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (item.getItemId() == R.id.call_btn) {
                startActivity(new Intent(getApplicationContext(), CallUsActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            }
            return false;
        });
        // ----------------------------------------------------------------------------
    }
}