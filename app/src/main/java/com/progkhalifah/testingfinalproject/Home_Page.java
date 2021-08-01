package com.progkhalifah.testingfinalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import de.hdodenhof.circleimageview.CircleImageView;

public class Home_Page extends AppCompatActivity {

    TextView txt_toolbar;
    CircleImageView profile_image;
    FirebaseAuth firebaseAuth;
    FloatingActionButton btn_add_post, btn_add_training;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__page);
        txt_toolbar = findViewById(R.id.txt_toolbar);
        profile_image = findViewById(R.id.profile_image);
        firebaseAuth = FirebaseAuth.getInstance();
        btn_add_post = findViewById(R.id.btn_add_post);
        btn_add_training = findViewById(R.id.btn_add_training);


       /* if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }//            getActionBar().hide();*/


       ////this for making toolbar
        Toolbar toolbar = findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        ////this for making toolbar


        btn_add_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home_Page.this, Add_Post_Page.class));
            }
        });

        btn_add_training.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(Home_Page.this, Add_Training_Page.class));
            }
        });




    }

    // this for menu items

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.notification:
                Toast.makeText(this, "Welcome to notifications", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.logout:
                logout_account();
                return true;
            case R.id.profile:
                //startActivity(new Intent(getApplicationContext(), Profile_Page.class));
            default:
                return super.onOptionsItemSelected(item);

        }

    }


    ////////// For logout from the account
    private void logout_account() {

              firebaseAuth.signOut();
              startActivity(new Intent(getApplicationContext(), SignIn.class));


    }// end of method or function
///////////////////////////////////////////////////////////////////////////////////////////


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);

        //crate search view here



        return true;
    }



////////////////////////////////////////End Options\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\


}