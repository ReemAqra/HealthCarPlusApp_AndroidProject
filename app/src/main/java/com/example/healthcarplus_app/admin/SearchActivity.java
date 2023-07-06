package com.example.healthcarplus_app.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.SearchView;

import com.example.healthcarplus_app.R;
import com.example.healthcarplus_app.RecycleAdapter;
import com.example.healthcarplus_app.product;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private RecyclerView recycleView;
    List<product> ProductList;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        recycleView= findViewById(R.id.recycleView);
        searchView = (SearchView) findViewById(R.id.search);
        searchView.clearFocus();


        // ----------------------------- Navigation Section ------------------------------
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationId);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home_button) {
                startActivity(new Intent(getApplicationContext(), MainActivity3_admin.class));
//                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (item.getItemId() == R.id.Search_button) {
                    return true;
            } else if (item.getItemId() == R.id.add_button) {
                startActivity(new Intent(getApplicationContext(), AddProductActivity.class));
//                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (item.getItemId() == R.id.cost_button) {
                startActivity(new Intent(getApplicationContext(), MoneySafeActivity.class));
//                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            }
            return false;
        });
        // ----------------------------------------------------------------------------

GridLayoutManager gridLayoutManager =new GridLayoutManager(SearchActivity.this , 1);
        recycleView.setLayoutManager(gridLayoutManager);

        AlertDialog.Builder builder= new AlertDialog.Builder(SearchActivity.this);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        ProductList = new ArrayList<>();
        RecycleAdapter adapter = new RecycleAdapter(SearchActivity.this, ProductList);
        recycleView.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("Android Tutorials");
        dialog.show();

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ProductList.clear();
                for (DataSnapshot itemSnapshot: snapshot.getChildren()){
                    product Product = itemSnapshot.getValue(product.class);
                    Product.setpName(itemSnapshot.getKey());
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