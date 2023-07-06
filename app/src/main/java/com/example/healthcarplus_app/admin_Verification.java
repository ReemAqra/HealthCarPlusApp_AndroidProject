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
<<<<<<< HEAD

    private CheckBox chk;

=======
    CheckBox remember;
    private SharedPreferences mPref;
    private static final String code="PrefCode";
    private Boolean saveCode;
>>>>>>> f96b14769af60584031c7e1761c4eba8218043b3
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_verification);
        textView =findViewById(R.id.textView);
        Code_editText =findViewById(R.id.Code_editText);
        button =findViewById(R.id.button);
<<<<<<< HEAD
        chk =findViewById(R.id.chk);
=======
        remember = (CheckBox) findViewById(R.id.remember);
        String msg ="123admin";
        mPref = getSharedPreferences(code,MODE_PRIVATE);

        saveCode = mPref.getBoolean("saveCode", false);
        if (saveCode == true) {
            Code_editText.setText(mPref.getString("",""));
            remember.setChecked(true);
        }
>>>>>>> f96b14769af60584031c7e1761c4eba8218043b3


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String password= String.valueOf(Code_editText.getText());
<<<<<<< HEAD
                 if (password.equals(msg)){
=======
                if (password.equals(msg)){
                    if (remember.isChecked()){
                        Boolean boolisChecked = remember.isChecked();
                        SharedPreferences.Editor editor = mPref.edit();
                        editor.putString("code",Code_editText.getText().toString());
                        editor.putBoolean("pref_check", boolisChecked);
                        editor.commit();
                        Toast.makeText(getApplicationContext(),"Your Code saved.", Toast.LENGTH_SHORT).show();

                    }else {
                    mPref.edit().clear().apply();
                    }
>>>>>>> f96b14769af60584031c7e1761c4eba8218043b3

                    Intent intent = new Intent(admin_Verification.this, MainActivity3_admin.class);
                    startActivity(intent);
                }else {
                     Toast.makeText(getApplicationContext(), "Wrong!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }



}