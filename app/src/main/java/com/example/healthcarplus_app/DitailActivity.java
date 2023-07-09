package com.example.healthcarplus_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DitailActivity extends AppCompatActivity {
    TextView detailTitle, detailPrice, detailDesc;
    ImageView detailImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ditail);

        detailTitle = findViewById(R.id.detailTitle);
        detailPrice = findViewById(R.id.detailPrice);
        detailDesc = findViewById(R.id.detailDesc);
        detailImage = findViewById(R.id.detailImage);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            detailTitle.setText(bundle.getString("pName"));
            detailDesc.setText(bundle.getString("pDescription"));
            detailPrice.setText(bundle.getString("pPrice")+"₪");
//            imageUrl = bundle.getString("pImage");
            Glide.with(this).load(bundle.getString("pImage")).into(detailImage);

        }
    }
}