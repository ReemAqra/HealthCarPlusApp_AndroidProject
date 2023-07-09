package com.example.healthcarplus_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.healthcarplus_app.admin.admin_Verification;
import com.example.healthcarplus_app.customer.MainCustomerActivity;
import com.example.healthcarplus_app.customer.SearchCustomerActivity;

public class MainActivity2 extends AppCompatActivity {
    private Button Customer_button,Admin_button;
    private ImageView logo_imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        logo_imageView =findViewById(R.id.logo_imageView);
        Customer_button= findViewById(R.id.Customer_button);
        Admin_button= findViewById(R.id.Admin_button);

        Admin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, admin_Verification.class);
                startActivity(intent);
            }
        });
        Customer_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, SearchCustomerActivity.class);
                startActivity(intent);
            }
        });

    }
}