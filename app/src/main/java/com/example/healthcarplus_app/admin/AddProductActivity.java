package com.example.healthcarplus_app.admin;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.healthcarplus_app.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AddProductActivity extends AppCompatActivity {
     ImageView Image;
     EditText nameEditText ;
     EditText priceEditText;
     EditText desEditText;
     EditText numEditText;

    String ProductImage;
    String ProductName;
    String ProductPrice;
    String ProductDescription;
    String ProductNum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        Image = (ImageView) findViewById(R.id.imageView3);
        nameEditText = (EditText) findViewById(R.id.nametxt);
        priceEditText = (EditText) findViewById(R.id.pricetxt);
        desEditText = (EditText) findViewById(R.id.destxt);
        numEditText =  (EditText) findViewById(R.id.numtxt);

       ProductName= nameEditText.getText().toString();
       ProductPrice= priceEditText.getText().toString();
        ProductDescription= desEditText.getText().toString();
       ProductNum= numEditText.getText().toString();

        checkInstance(savedInstanceState);



        Button PostButton = (Button) findViewById(R.id.button);
        String imageURL;
       final Uri[] uri = new Uri[1];

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            uri[0] = data.getData();
                            Image.setImageURI(uri[0]);
                        } else {
                            Toast.makeText(AddProductActivity.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });


        PostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nameEditText.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Write The Name", Toast.LENGTH_SHORT).show();
                    nameEditText.setError("Field can't be empty");
                } else if (priceEditText.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Write The Price", Toast.LENGTH_SHORT).show();
                    priceEditText.setError("Field can't be empty");
                } else if (desEditText.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Write The Description", Toast.LENGTH_SHORT).show();
                    desEditText.setError("Field can't be empty");
                } else if (numEditText.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Write The Number", Toast.LENGTH_SHORT).show();
                    numEditText.setError("Field can't be empty");
                } else {
                    Toast.makeText(getApplicationContext(), "Done :)", Toast.LENGTH_SHORT).show();
                }
                saveData();

            }
        });

    }

    private void checkInstance(Bundle savedInstanceState) {
        if(savedInstanceState != null){
            ProductName = savedInstanceState.getString("ProductName");
            ProductPrice = savedInstanceState.getString("ProductPrice");
            ProductDescription = savedInstanceState.getString("ProductDescription");
            ProductNum = savedInstanceState.getString("ProductNum");
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString("ProductName", ProductName);
        outState.putString("ProductPrice", ProductPrice);
        outState.putString("ProductDescription", ProductDescription);
        outState.putString("ProductNum", ProductNum);


    }

    public void saveData() {

    }
   }
