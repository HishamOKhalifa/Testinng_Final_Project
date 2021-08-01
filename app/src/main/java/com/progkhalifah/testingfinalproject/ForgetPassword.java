package com.progkhalifah.testingfinalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPassword extends AppCompatActivity {


    TextView txt_reset_password, txt_description_password;
    EditText edtxt_reset_password;
    Button btn_send_new_password;
    ProgressDialog dialog;
    FirebaseAuth firebaseAuth  = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        txt_reset_password = findViewById(R.id.txt_reset_password);
        txt_description_password = findViewById(R.id.txt_description_password);
        edtxt_reset_password = findViewById(R.id.edtxt_reset_password);
        btn_send_new_password= findViewById(R.id.btn_send_new_password);

        btn_send_new_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new ProgressDialog(getApplicationContext());
                dialog.setMessage("Starting Reset...");

                String userEmail = edtxt_reset_password.getText().toString();
                firebaseAuth.sendPasswordResetEmail(userEmail)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(ForgetPassword.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(ForgetPassword.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                                }

                                dialog.dismiss();
                            }
                        });



            }
        });


    }
}