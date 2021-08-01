package com.progkhalifah.testingfinalproject;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignIn extends AppCompatActivity {

    ImageButton btn_back_to_main;
    ImageView image_signin;
    EditText edtxt_write_your_password, edtxt_write_your_email;
    CheckBox chk_remember_me;
    TextView txt_forget_password;
    Button btn_sign;
    TextView txt_dont_have;
    FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener fireAuthListener;
    ProgressDialog dialog;
    SharedPreferences shp_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        btn_back_to_main =findViewById(R.id.btn_back_to_main);
        image_signin = findViewById(R.id.image_signin);
        edtxt_write_your_password = findViewById(R.id.edtxt_write_your_password);
        edtxt_write_your_email = findViewById(R.id.edtxt_write_your_email);
        txt_forget_password = findViewById(R.id.chk_forget_password);
        chk_remember_me = findViewById(R.id.chk_Remember_Me);
        btn_sign = findViewById(R.id.btn_sgin);
        txt_dont_have = findViewById(R.id.txt_dont_have);

        //Firebase
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


        //////////////////////////////////////////
        if (shp_password != null){

            SharedPreferences get_password = getSharedPreferences("Remember_Password", Context.MODE_PRIVATE);

            edtxt_write_your_email.setText(get_password.getString("Email", null));
            edtxt_write_your_password.setText(get_password.getString("Password", null));


        }// end of if condition
        /////////////////////////////////////////


        txt_dont_have.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Create_New_Account.class));
            }
        });


        btn_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(), Home_Page.class));

                dialog = new ProgressDialog(getApplicationContext());
                dialog.setMessage("Checking...");

                String write_your_password = edtxt_write_your_password.getText().toString();
                String write_your_email = edtxt_write_your_email.getText().toString();

                if (TextUtils.isEmpty(write_your_password)){
                    Toast.makeText(SignIn.this, "You have to write your Password", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(write_your_email)){
                    Toast.makeText(SignIn.this, "You have to write your email", Toast.LENGTH_SHORT).show();

                }

                auth.signInWithEmailAndPassword(write_your_email, write_your_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {



                        if (task.isSuccessful()){
                            dialog.dismiss();
                            Intent i = new Intent(getApplicationContext(), Home_Page.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(i);
                            finish();
                        }else{
                            dialog.dismiss();
                            Toast.makeText(SignIn.this, "Authentication Failed", Toast.LENGTH_SHORT).show();

                        }


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(SignIn.this, "Check your internet or click on forget password and sign in", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });// end of on click listener


        if (chk_remember_me.isChecked()){
            shp_password = getSharedPreferences("Remember_Password", Context.MODE_PRIVATE);
            SharedPreferences.Editor rm_password = shp_password.edit();
            rm_password.putString("Email", edtxt_write_your_email.getText().toString());
            rm_password.putString("Password", edtxt_write_your_password.getText().toString());
            rm_password.commit();
            Toast.makeText(SignIn.this, "Don't worry we will remember you", Toast.LENGTH_SHORT).show();
        }//end of if condition



        txt_forget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ForgetPassword.class));
            }
        });



    }





}