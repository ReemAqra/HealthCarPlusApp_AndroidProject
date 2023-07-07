package com.example.healthcarplus_app.customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.example.healthcarplus_app.R;
import com.example.healthcarplus_app.RecycleAdapter;
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

public class SearchCustomerActivity extends AppCompatActivity {
    private RecyclerView recycleView;
    List<product> ProductList;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    SearchView searchView;
    RecycleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_customer);

        recycleView= findViewById(R.id.recycleView_1);
        searchView = (SearchView) findViewById(R.id.search);
        searchView.clearFocus();

        // ----------------------------- Navigation Section ------------------------------
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationId);
        bottomNavigationView.setSelectedItemId(R.id.search_btn);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home_btn) {
                startActivity(new Intent(getApplicationContext(), MainCustomerActivity.class));
                //  overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (item.getItemId() == R.id.search_btn) {
                return true;
            } else if (item.getItemId() == R.id.star_btn) {
//                startActivity(new Intent(getApplicationContext(), AddProductActivity.class));
                //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (item.getItemId() == R.id.call_btn) {
                startActivity(new Intent(getApplicationContext(), CallUsActivity.class));
                //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            }
            return false;
        });
        // ----------------------------------------------------------------------------
        GridLayoutManager gridLayoutManager =new GridLayoutManager(SearchCustomerActivity.this , 1);
        recycleView.setLayoutManager(gridLayoutManager);

        AlertDialog.Builder builder= new AlertDialog.Builder(SearchCustomerActivity.this);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        ProductList = new ArrayList<>();
        adapter = new RecycleAdapter(SearchCustomerActivity.this, ProductList);
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

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchList(newText);
                return false;
            }
        });
    }
    public void searchList(String text){
        ArrayList<product> searchList = new ArrayList<>();
        for (product dataClass: ProductList){
            if (dataClass.getpName().toLowerCase().contains(text.toLowerCase())){
                searchList.add(dataClass);
            }
        }
        adapter.searchProduct(searchList);
    }
}