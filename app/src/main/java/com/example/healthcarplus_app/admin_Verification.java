package com.example.healthcarplus_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthcarplus_app.admin.MainActivity3_admin;

public class admin_Verification extends AppCompatActivity {


    private TextView textView;
    private EditText Code_editText;
    private Button button;

    private CheckBox chk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_verification);
        textView =findViewById(R.id.textView);
        Code_editText =findViewById(R.id.Code_editText);
        button =findViewById(R.id.button);
        chk =findViewById(R.id.chk);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg ="123admin";
                String password= String.valueOf(Code_editText.getText());
                 if (password.equals(msg)){

                    Intent intent = new Intent(admin_Verification.this, MainActivity3_admin.class);
                    startActivity(intent);
                }else {
                     Toast.makeText(getApplicationContext(), "Wrong!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }



}