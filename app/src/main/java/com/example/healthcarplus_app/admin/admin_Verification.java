package com.example.healthcarplus_app.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthcarplus_app.R;
import android.preference.PreferenceManager;

public class admin_Verification extends AppCompatActivity {


    private TextView textView;
    private EditText Code_editText;

    private Button button;
    private CheckBox chk;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    public static final String FLAG = "FLAG";
    public static final String PASS = "PASS";
    private boolean flag = false;
    CheckBox remember;

    private static final String code="PrefCode";
    private Boolean saveCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_verification);
        textView =findViewById(R.id.textView);
        Code_editText =findViewById(R.id.Code_editText);
        button =findViewById(R.id.button);

        remember = (CheckBox) findViewById(R.id.chk);
        String msg ="123admin";
        //mPref = getSharedPreferences(code,MODE_PRIVATE);
        prefs= PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
        flag = prefs.getBoolean(FLAG, false);

        if(flag){
            String password = prefs.getString(PASS, "");

            Code_editText.setText(password);
            remember.setChecked(true);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String password = Code_editText.getText().toString();

                if(remember.isChecked()){
                    if(!flag) {
                        editor.putString(PASS, password);
                        editor.putBoolean(FLAG, true);
                        editor.commit();
                        Toast.makeText(getApplicationContext(),"Your Code saved.", Toast.LENGTH_SHORT).show();
                        }
                }
                if (password.equals("123admin")){
                    Intent intent = new Intent(admin_Verification.this, SearchAdminActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(), "Wrong!", Toast.LENGTH_LONG).show();
                }

//                String password= String.valueOf(Code_editText.getText());
//                    if (remember.equals(msg)){
//                        if (remember.isChecked()){
//                            Boolean boolisChecked = remember.isChecked();
//                            SharedPreferences.Editor editor = mPref.edit();
//                            editor.putString("code",Code_editText.getText().toString());
//                            editor.putBoolean("pref_check", boolisChecked);
//                            editor.commit();
//                            Toast.makeText(getApplicationContext(),"Your Code saved.", Toast.LENGTH_SHORT).show();
//                        }else {
//                            mPref.edit().clear().apply();
//                        }
//                        Intent intent = new Intent(admin_Verification.this, MainAdminActivity.class);
//                        startActivity(intent);
//                    }else {
//                        Toast.makeText(getApplicationContext(), "Wrong!", Toast.LENGTH_LONG).show();
//                    }


            };
        });





    }
}

