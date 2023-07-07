package com.example.healthcarplus_app.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.healthcarplus_app.R;
//import com.example.healthcarplus_app.admin.SearchActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MoneySafeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {


    public TextView sumText;
    public EditText amountText;
    public String Process;
    public String Message1 = "Please Choose Your Process .. ";
    public Button Do1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_safe);
        // ----------------------------- Navigation Section ------------------------------
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationId);
        bottomNavigationView.setSelectedItemId(R.id.cost_button);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.home_button) {
                startActivity(new Intent(getApplicationContext(), MainAdminActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (item.getItemId() == R.id.Search_button) {
                startActivity(new Intent(getApplicationContext(), SearchAdminActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (item.getItemId() == R.id.add_button) {
                startActivity(new Intent(getApplicationContext(), AddProductActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (item.getItemId() == R.id.cost_button) {
                return true;
            }
            return false;
        });
        // ----------------------------------------------------------------------------

        Spinner spinner = findViewById(R.id.spinner);
        amountText = (EditText) findViewById(R.id.text11);
        sumText = findViewById(R.id.textView3);
        Do1 = findViewById(R.id.button7);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.bank, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String Amount= amountText.getText().toString();
        String Sum= sumText.getText().toString();
        final int[] SumInt = {new Integer(Sum)};
        int AmountInt = new Integer(Amount);

        Process = parent.getItemAtPosition(position).toString();

        Do1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

        if (Process.equals("Take The Amount") ){
            SumInt[0] = SumInt[0] - AmountInt;
            sumText.setText(Integer.toString(SumInt[0]));
        }
        else if (Process.equals("Put The Amount")){
            SumInt[0] = SumInt[0] + AmountInt;
            sumText.setText(Integer.toString(SumInt[0]));
        }
               // Toast.makeText(parent.getContext(), Integer.toString(AmountInt), Toast.LENGTH_SHORT).show();


            }
        });


    }



    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(parent.getContext(), Message1, Toast.LENGTH_SHORT).show();

    }




    }
