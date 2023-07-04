package com.example.healthcarplus_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class admin_Verification extends AppCompatActivity {
    private TextView textView;
    private EditText Code_editText;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_verification);
        textView =findViewById(R.id.textView);
        Code_editText =findViewById(R.id.Code_editText);
        button =findViewById(R.id.button);

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