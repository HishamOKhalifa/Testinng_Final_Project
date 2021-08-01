package com.progkhalifah.testingfinalproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class Create_New_Account extends AppCompatActivity {


    ImageButton btn_back_to_main;
    TextView txt_Required;
    EditText edtxt_company_name, edtxt_write_your_password, edtxt_contact_number, edtxt_E_mail, edtxt_address, edtxt_city, edtxt_website_url, edtxt_establish_year;
    Button btn_logo_company, btn_commercial_registration, btn_create_new_account;
    FirebaseAuth auth;
    DatabaseReference reference;
    private static final int Imagepicker = 1;
    private StorageReference folder;
    HashMap<String , String> hashMap = new HashMap<>();
    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__new__account);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        btn_back_to_main = findViewById(R.id.btn_back_to_main);
        txt_Required = findViewById(R.id.txt_Required);
        folder = FirebaseStorage.getInstance().getReference().child("Company_Image");
        edtxt_company_name = findViewById(R.id.edtxt_comapny_name);
        edtxt_write_your_password = findViewById(R.id.edtxt_write_your_password);
        edtxt_contact_number = findViewById(R.id.edtxt_contact_number);
        edtxt_E_mail = findViewById(R.id.edtxt_E_mail);
        edtxt_address = findViewById(R.id.edtxt_Address);
        edtxt_city = findViewById(R.id.edtxt_city);
        edtxt_website_url = findViewById(R.id.edtxt_website_url);
        edtxt_establish_year = findViewById(R.id.edtxt_establish_year);

        btn_logo_company = findViewById(R.id.btn_logo_company);
//        btn_commercial_registration = findViewById(R.id.btn_Commercial_registration);
        btn_create_new_account = findViewById(R.id.btn_create_new_account);


        auth = FirebaseAuth.getInstance();

        btn_create_new_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name_company = edtxt_company_name.getText().toString();
                Intent intent_name_company = new Intent(getApplicationContext(), CR_Page.class);
                intent_name_company.putExtra("name_of_company", name_company);
                startActivity(intent_name_company);


//                Toast.makeText(getApplicationContext(), "Your Account Have Created Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), CR_Page.class));


            }// end of on click
        });

        btn_logo_company.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setType("image/*");//application/pdf
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), Imagepicker);

                String company_name = edtxt_company_name.getText().toString();
                String write_your_password = edtxt_write_your_password.getText().toString();
                String contact_number = edtxt_contact_number.getText().toString();
                String E_mail = edtxt_E_mail.getText().toString();
                String address = edtxt_address.getText().toString();
                String city = edtxt_city.getText().toString();
                String website_url = edtxt_website_url.getText().toString();
                String establish_year = edtxt_establish_year.getText().toString();

                if (TextUtils.isEmpty(company_name) || TextUtils.isEmpty(write_your_password)  || TextUtils.isEmpty(contact_number) || TextUtils.isEmpty(E_mail) || TextUtils.isEmpty(address) || TextUtils.isEmpty(city) || TextUtils.isEmpty(website_url) || TextUtils.isEmpty(establish_year)  ){
                    Toast.makeText(Create_New_Account.this, "All Fields Required", Toast.LENGTH_SHORT).show();
                }else if (write_your_password.length()<6){
                    Toast.makeText(Create_New_Account.this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
                }else{
                    processinsert(company_name, write_your_password, contact_number, E_mail, address, city, website_url, establish_year);
                }

            }
        });


    }// End onCreate



    public void processinsert(final String company_name, final String write_your_password, final String contact_number, final String e_mail, final String address, final String city, final String website_url, final String establish_year){

        auth.createUserWithEmailAndPassword(e_mail, write_your_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    FirebaseUser firebaseUser = auth.getCurrentUser();
                    assert firebaseUser != null;


//        hashMap.put("id", userid);
                    /*hashMap.put("company_name", company_name);
                    hashMap.put("email", e_mail);
                    hashMap.put("address", address);
                    hashMap.put("city", city);
                    hashMap.put("website", website_url);
                    hashMap.put("imageURL", "default");
                    hashMap.put("password", write_your_password);
                    hashMap.put("contact_number", contact_number);
                    hashMap.put("year", establish_year);*/
                    /*FirebaseDatabase.getInstance().getReference("Company").child("Users").push().setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {

                            Toast.makeText(Create_New_Account.this, "The account created successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), Testimage.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(Create_New_Account.this, "You have to check the internet", Toast.LENGTH_SHORT).show();

                        }
                    });*/
                    ////////////////////////////////end of insert into database\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

                    


                }else {
                    Toast.makeText(Create_New_Account.this, "Enter your E-mail and password correctly", Toast.LENGTH_SHORT).show();
                }



            }
        });





    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Imagepicker){
            if (resultCode == RESULT_OK){

                dialog = new ProgressDialog(this);
                dialog.setMessage("Creating...");

                // this will show message uploading
                // while pdf is uploading
                dialog.show();
                Uri ImageData = data.getData();

                StorageReference Imagename = folder.child("image/"+ImageData.getLastPathSegment());
                Imagename.putFile(ImageData).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        Imagename.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
//                                HashMap<String , String> hashMap = new HashMap<>();
                                hashMap.put("image",String.valueOf(uri));
                                hashMap.put("company_name", edtxt_company_name.getText().toString());
                                hashMap.put("email", edtxt_E_mail.getText().toString());
                                hashMap.put("address", edtxt_address.getText().toString());
                                hashMap.put("city", edtxt_city.getText().toString());
                                hashMap.put("website", edtxt_website_url.getText().toString());
                                hashMap.put("password", edtxt_write_your_password.getText().toString());
                                hashMap.put("contact_number", edtxt_contact_number.getText().toString());
                                hashMap.put("year", edtxt_establish_year.getText().toString());
                                FirebaseDatabase.getInstance().getReference("Company").child("Users").push().setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        dialog.dismiss();
                                        btn_create_new_account.setVisibility(View.VISIBLE);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        dialog.dismiss();
                                        Toast.makeText(getApplicationContext(), "Could not insert to the database", Toast.LENGTH_SHORT).show();
                                    }
                                });



                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                dialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Could not upload the image", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });


            }
        }



    }











//    private void createNewAccount(final String company_name, final String write_your_password, final String contact_number, final String e_mail, final String address, final String city, final String website_url, final String establish_year) {
//
//        auth.createUserWithEmailAndPassword(e_mail, write_your_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//
//                if (task.isSuccessful()){
//                    FirebaseUser firebaseUser = auth.getCurrentUser();
//                    assert firebaseUser != null;
//                    String userid = firebaseUser.getUid();
//
//                    reference = FirebaseDatabase.getInstance().getReference("Company").child("userid");
//
//
//
//                    reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            if (task.isSuccessful()){
//                                Toast.makeText(Create_New_Account.this, "The account created successfully", Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(getApplicationContext(), WaitingPage.class);
//                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | intent.FLAG_ACTIVITY_NEW_TASK);
//                                startActivity(intent);
//                                finish();
//                            }
//                        }
//                    });
//
//
//
//
//                }else {
//                    Toast.makeText(Create_New_Account.this, "This E-mail and Password is already taken", Toast.LENGTH_SHORT).show();
//                }
//
//
//            }
//        });
//
//
//    }








}