package com.progkhalifah.testingfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

public class Rating_Student extends AppCompatActivity {

    EditText ed_star_bar;
    RatingBar rating_bar;
    Button btn_result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating__student);
        ed_star_bar = findViewById(R.id.edtxt_star_bar);
        rating_bar = findViewById(R.id.rating_bar);
        btn_result =findViewById(R.id.btn_result);


        btn_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*int rating = Integer.getInteger(ed_star_bar.getText().toString());
                rating_bar.setNumStars(5);
                rating_bar.setRating(rating);*/
                String s = ed_star_bar.getText().toString();
                float rating = Float.parseFloat(s);
                rating_bar.setRating(rating);




            }
        });

    }



}