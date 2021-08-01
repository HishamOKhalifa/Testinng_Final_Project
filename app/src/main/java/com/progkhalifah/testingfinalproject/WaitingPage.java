package com.progkhalifah.testingfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class WaitingPage extends AppCompatActivity {

    ImageView image_wait_page;
    ProgressBar progressbar_wait;
    TextView txt_you_have_to_wait;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting_page);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }        image_wait_page = findViewById(R.id.image_waiting);
        progressbar_wait = findViewById(R.id.progressbar_waiting);
        txt_you_have_to_wait = findViewById(R.id.txt_you_have_to_wait);

    }
}