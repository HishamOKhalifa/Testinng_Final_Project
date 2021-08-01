package com.progkhalifah.testingfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView our_logo;
    TextView txt_with_us;
    Button btn_sign_in, btn_create_new_account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }//            getActionBar().hide();
        our_logo = findViewById(R.id.our_logo);
        txt_with_us= findViewById(R.id.txt_with_us);
        btn_create_new_account = findViewById(R.id.btn_create_new_account);
        btn_sign_in = findViewById(R.id.btn_signin);


        btn_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SignIn.class));
            }
        });

        btn_create_new_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Create_New_Account.class));
            }
        });





    }
}