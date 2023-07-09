package com.example.healthcarplus_app.customer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.healthcarplus_app.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CallUsActivity extends AppCompatActivity {

    private ImageView img;
    private Button call;
    private Animation top ,bottom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_us);
        // ----------------------------- Navigation Section ------------------------------
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationId);
        bottomNavigationView.setSelectedItemId(R.id.call_btn);

        bottomNavigationView.setOnItemSelectedListener(item -> {
           if (item.getItemId() == R.id.search_btn) {
                startActivity(new Intent(getApplicationContext(), SearchCustomerActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (item.getItemId() == R.id.star_btn) {
//                startActivity(new Intent(getApplicationContext(), AddProductActivity.class));
                //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (item.getItemId() == R.id.call_btn) {

                return true;
            }
            return false;
        });
        // ----------------------------------------------------------------------------

        top= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottom= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        img=findViewById(R.id.imageView2);
        call = findViewById(R.id.button6);

        call.setAnimation(bottom);
        img.setAnimation(top);

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0599"));
                startActivity(intent);
            }
        });



    }
}