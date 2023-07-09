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

import com.example.healthcarplus_app.RecycleAdapter1;
import com.example.healthcarplus_app.product;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SearchAdminActivity extends AppCompatActivity {

    private RecyclerView recycleView;
    List<product> ProductList;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    SearchView searchView;
    RecycleAdapter1 adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_admin);

        recycleView= findViewById(R.id.recycleView);
        searchView = (SearchView) findViewById(R.id.search);
        searchView.clearFocus();


        // ----------------------------- Navigation Section ------------------------------
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationId);
        bottomNavigationView.setSelectedItemId(R.id.Search_button);

        bottomNavigationView.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.Search_button) {
                    return true;
            } else if (item.getItemId() == R.id.add_button) {
                startActivity(new Intent(getApplicationContext(), AddProductActivity.class));
              //  overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (item.getItemId() == R.id.cost_button) {
                startActivity(new Intent(getApplicationContext(), MoneySafeActivity.class));
              //  overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            }
            return false;
        });
        // ----------------------------------------------------------------------------

        GridLayoutManager gridLayoutManager =new GridLayoutManager(SearchAdminActivity.this , 1);
        recycleView.setLayoutManager(gridLayoutManager);

        AlertDialog.Builder builder= new AlertDialog.Builder(SearchAdminActivity.this);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        ProductList = new ArrayList<>();
        adapter = new RecycleAdapter1(SearchAdminActivity.this, ProductList);
        recycleView.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("Android Tutorials");
        dialog.show();

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ProductList.clear();
                for (DataSnapshot itemSnapshot: snapshot.getChildren()){
                    product Product = itemSnapshot.getValue(product.class);
                    Product.setKey(itemSnapshot.getKey());


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