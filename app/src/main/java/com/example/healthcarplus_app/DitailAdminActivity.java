package com.example.healthcarplus_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.github.clans.fab.FloatingActionButton;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class DitailAdminActivity extends AppCompatActivity {

    TextView detailTitle, detailPrice, detailDesc;
    FloatingActionButton deleteButton, editButton;
    ImageView detailImage;
    String key = "",imageUrl="";
    String number = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ditail_admin);

        detailTitle = findViewById(R.id.detailTitle);
        detailPrice = findViewById(R.id.detailPrice);
        detailDesc = findViewById(R.id.detailDesc);
        detailImage = findViewById(R.id.detailimage);
        deleteButton = findViewById(R.id.deleteButton);
        editButton = findViewById(R.id.editButton);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            detailTitle.setText(bundle.getString("pName"));
            detailDesc.setText(bundle.getString("pDescription"));
            detailPrice.setText(bundle.getString("pPrice")+"₪");
            number=bundle.getString("pNumber");
            imageUrl = bundle.getString("pImage");
            Glide.with(this).load(bundle.getString("pImage")).into(detailImage);
            key = bundle.getString("Key");
        }

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Android Tutorials");
                FirebaseStorage storage = FirebaseStorage.getInstance();
                StorageReference storageReference = storage.getReferenceFromUrl(imageUrl);
                storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        reference.child(key).removeValue();
                        Toast.makeText(DitailAdminActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    }
                });
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DitailAdminActivity.this, UpdateActivity.class)
                        .putExtra("pName", detailTitle.getText().toString())
                        .putExtra("pDescription", detailDesc.getText().toString())
                        .putExtra("pPrice", detailPrice.getText().toString())
                        .putExtra("pNumber", number)
                        .putExtra("pImage", imageUrl)
                        .putExtra("Key", key);
                startActivity(intent);
            }
        });

    }
}