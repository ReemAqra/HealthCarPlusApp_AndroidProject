package com.example.healthcarplus_app.customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.healthcarplus_app.R;
import com.example.healthcarplus_app.RecycleAdapter;
import com.example.healthcarplus_app.admin.AddProductActivity;
import com.example.healthcarplus_app.admin.MoneySafeActivity;
import com.example.healthcarplus_app.admin.SearchAdminActivity;
import com.example.healthcarplus_app.product;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainCustomerActivity extends AppCompatActivity {
    private RecyclerView recycleView;
    List<product> ProductList;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    SearchView searchView;
    RecycleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_customer);

        recycleView= findViewById(R.id.recycleView_2);

        GridLayoutManager gridLayoutManager =new GridLayoutManager(MainCustomerActivity.this , 1);
        recycleView.setLayoutManager(gridLayoutManager);

        AlertDialog.Builder builder= new AlertDialog.Builder(MainCustomerActivity.this);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        ProductList = new ArrayList<>();
        adapter = new RecycleAdapter(MainCustomerActivity.this, ProductList);
        recycleView.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("Android Tutorials");
        dialog.show();

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ProductList.clear();
                for (DataSnapshot itemSnapshot: snapshot.getChildren()){
                    product Product = itemSnapshot.getValue(product.class);
                    //Product.setpName(itemSnapshot.getKey());
                    ProductList.add(Product);
                }
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();

            }
        });


    }

}