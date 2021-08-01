package com.progkhalifah.testingfinalproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import de.hdodenhof.circleimageview.CircleImageView;

public class CR_Page extends AppCompatActivity {



    TextView textView;
    Button btn_upload_pdf, btn_complete;
    ProgressDialog dialog;
    Toolbar custom_toolbar_cr;
    CircleImageView profile_image_cr;
    TextView txt_toolbar_cr;

    private StorageReference folder;
    private static final int Pdfpicker = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_r__page);

        textView = findViewById(R.id.textView);
        btn_upload_pdf = findViewById(R.id.btn_upload_pdf);
        btn_complete = findViewById(R.id.btn_complete);
        folder = FirebaseStorage.getInstance().getReference().child("Company_Commercial_Registration");
        custom_toolbar_cr = findViewById(R.id.custom_toolbar_cr);
        profile_image_cr = findViewById(R.id.profile_image_cr);
        txt_toolbar_cr = findViewById(R.id.txt_toolbar_cr);


        ////////////////

        Intent intent_get_name_company = getIntent();
        String get_name_company = intent_get_name_company.getStringExtra("name_of_company");
        txt_toolbar_cr.setText(get_name_company);
        ///////////////

        btn_upload_pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);

                // We will be redirected to choose pdf
                galleryIntent.setType("application/pdf");
                startActivityForResult(galleryIntent, 1);


            }
        });// end of set on click listener.......


        btn_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent complete_intent = new Intent(getApplicationContext(), WaitingPage.class);
                complete_intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(complete_intent);
                finish();
            }
        });// end of set on click listener.......





    }//end of on create

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Pdfpicker) {
            if (resultCode == RESULT_OK) {
                Uri imageuri = imageuri = data.getData();
            // Here we are initialising the progress dialog box
            dialog = new ProgressDialog(this);
            dialog.setMessage("Uploading...");

            // this will show message uploading
            // while pdf is uploading
            dialog.show();


            final String timestamp = "" + System.currentTimeMillis();
            StorageReference storageReference = FirebaseStorage.getInstance().getReference();
            final String messagePushID = timestamp;
            Toast.makeText(getApplicationContext(), imageuri.toString(), Toast.LENGTH_SHORT).show();
            // Here we are uploading the pdf in firebase storage with the name of current time

            final StorageReference filepath = folder.child("Commercial_Registration/" + messagePushID + "." + "pdf");
            Toast.makeText(CR_Page.this, filepath.getName(), Toast.LENGTH_SHORT).show();
            filepath.putFile(imageuri).continueWithTask(new Continuation() {
                @Override
                public Object then(@NonNull Task task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }
                    return filepath.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {

                    if (task.isSuccessful()) {
                        // After uploading is done it progress
                        // dialog box will be dismissed
                        dialog.dismiss();
                        btn_complete.setVisibility(View.VISIBLE);
                        Uri uri = (Uri) task.getResult();
                        String myurl;
                        myurl = uri.toString();
                        Toast.makeText(CR_Page.this, "Uploaded Successfully", Toast.LENGTH_SHORT).show();
                        btn_upload_pdf.setVisibility(View.GONE);
                    } else {
                        dialog.dismiss();
                        Toast.makeText(CR_Page.this, "UploadedFailed", Toast.LENGTH_SHORT).show();
                    }

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(CR_Page.this, "Make sure you have Internet or you choose the correct file", Toast.LENGTH_SHORT).show();
                }
            });


        }//end if
    }//end if






    }
}