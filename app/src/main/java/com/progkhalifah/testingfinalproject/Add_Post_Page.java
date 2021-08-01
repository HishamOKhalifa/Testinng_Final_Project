package com.progkhalifah.testingfinalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;

public class Add_Post_Page extends AppCompatActivity {


    TextView txt_Required, txt_qualifications, txt_experience_level, txt_job_type;
    EditText edtxt_job_title, edtxt_job_description, edtxt_responsibilities, edtxt_requirements, edtxt_company_mission;
    CheckBox chk_bachelor, chk_master, chk_phd, chk_entry_level, chk_middle_level, chk_senior, chk_IT, chk_business, chk_account,chk_full_time,chk_contract,chk_part_time,chk_remote;
    Button btn_push_post;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    ProgressDialog dialog;
    private String job_type_radio_button = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__post__page);

        txt_Required = findViewById(R.id.txt_Required);
        txt_qualifications = findViewById(R.id.txt_Qualifications);
        txt_experience_level = findViewById(R.id.txt_Experience_level);
        txt_job_type = findViewById(R.id.txt_job_type);
        edtxt_job_title = findViewById(R.id.edtxt_job_title);
        edtxt_job_description = findViewById(R.id.edtxt_job_description);
        edtxt_responsibilities = findViewById(R.id.edtxt_responsibilities);
        edtxt_requirements = findViewById(R.id.edtxt_requirements);
        edtxt_company_mission = findViewById(R.id.edtxt_company_mission);
        chk_bachelor =findViewById(R.id.chk_Bachelor);
        chk_master = findViewById(R.id.chk_Master);
        chk_phd = findViewById(R.id.chk_PhD);
        chk_entry_level = findViewById(R.id.chk_Entry_level);
        chk_middle_level = findViewById(R.id.chk_Middle_level);
        chk_senior = findViewById(R.id.chk_Senior);
        chk_full_time = findViewById(R.id.chk_full_time);
        chk_remote = findViewById(R.id.chk_remote);
        chk_part_time = findViewById(R.id.chk_part_time);
        chk_contract = findViewById(R.id.chk_contract);
        btn_push_post = findViewById(R.id.btn_push_post);
        chk_IT = findViewById(R.id.chk_IT);
        chk_business = findViewById(R.id.chk_Business);
        chk_account = findViewById(R.id.chk_Account);


        btn_push_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new ProgressDialog(getApplicationContext());
                dialog.setMessage("Creating Post");
                push_post_to_database();
                dialog.dismiss();
                Toast.makeText(Add_Post_Page.this, "The Post Created Successfully", Toast.LENGTH_SHORT).show();

            }
        });





    }// end of on Create

    private void push_post_to_database() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();


        HashMap<String , String> hashMap = new HashMap<>();
        // hashMap.put("name_comopany", get from their name);
        hashMap.put("job_title", edtxt_job_title.getText().toString());
        hashMap.put("job_description", edtxt_job_description.getText().toString());
        hashMap.put("job_responsibilities", edtxt_responsibilities.getText().toString());
        hashMap.put("job_requirements", edtxt_requirements.getText().toString());
        hashMap.put("job_company_mission", edtxt_company_mission.getText().toString());
        hashMap.put("job_time",dateFormat.format(date));

        if (chk_bachelor.isChecked()){
            hashMap.put("job_degree", chk_bachelor.getText().toString());
        }else if (chk_master.isChecked()){
            hashMap.put("job_degree",chk_master.getText().toString());
        }else if (chk_phd.isChecked()){
            hashMap.put("job_degree", chk_phd.getText().toString());
        }

        if (chk_entry_level.isChecked()){
            hashMap.put("job_experience_level", chk_entry_level.getText().toString());
        }else if (chk_middle_level.isChecked()){
            hashMap.put("job_experience_level", chk_middle_level.getText().toString());
        }else if (chk_senior.isChecked()){
            hashMap.put("job_experience_level", chk_senior.getText().toString());
        }



        ////////////////////////////////[[[[[[[[[[[[[[[[[[ Radio Button ]]]]]]]]]]]]]]]]]]]]]]]]]]]/////////////////////////////
        /*rdg_one.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

               switch (checkedId){
                   case R.id.rd_full_time:
                       job_type_radio_button = "Full Time";
                       hashMap.put("job_type", job_type_radio_button);
                       break;
                   case R.id.rd_part_time:
                       job_type_radio_button = "Part Time";
                       hashMap.put("job_type", job_type_radio_button);
                       break;
                   case R.id.rd_contract:
                       job_type_radio_button = "Contract";
                       hashMap.put("job_type", job_type_radio_button);
                       break;
                   case R.id.rd_remote:
                       job_type_radio_button = "Remote";
                       hashMap.put("job_type", job_type_radio_button);
                       break;
                   default:
                       Toast.makeText(Add_Post_Page.this, "You have choose one of the Job Type", Toast.LENGTH_SHORT).show();
               }


                *//* String s_rd_full_time = rd_full_time.getText().toString();

                if (group.findViewById(R.id.rd_full_time).isClickable()){
                    hashMap.put("job_type", rd_full_time.getText().toString());
                }else if (group.findViewById(R.id.rd_part_time).isClickable()){
                    hashMap.put("job_type", rd_part_time.getText().toString());
                }else if (group.findViewById(R.id.rd_contract).isClickable()){
                    hashMap.put("job_type", rd_contract.getText().toString());
                }else if (group.findViewById(R.id.rd_remote).isClickable()){
                    hashMap.put("job_type", rd_remote.getText().toString());
                }*//*


            }
        });
*/


        if (chk_full_time.isChecked()) {
            hashMap.put("JobType", chk_full_time.getText().toString());
        } else if (chk_part_time.isChecked()) {
            hashMap.put("JobType", chk_part_time.getText().toString());
        } else if (chk_contract.isChecked()) {
            hashMap.put("JobType", chk_contract.getText().toString());
        } else if (chk_remote.isChecked()) {
            hashMap.put("JobType", chk_remote.getText().toString());
        }else {
            Toast.makeText(this, "You have to choose what is Job type you want", Toast.LENGTH_SHORT).show();
        }










        ////////////////////////////////[[[[[[[[[[[[[[[[[[ End Radio Button ]]]]]]]]]]]]]]]]]]]]]]]]]]]/////////////////////////////



        if (chk_IT.isChecked()){
            FirebaseDatabase.getInstance().getReference("Interest").child("IT").push().setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
//                    dialog.dismiss();

                    Toast.makeText(Add_Post_Page.this, "The Post has Created Successfully", Toast.LENGTH_SHORT).show();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Add_Post_Page.this, "Make sure you don't miss anything or you connect to network", Toast.LENGTH_SHORT).show();
                }
            });
        }else if (chk_business.isChecked()){

            FirebaseDatabase.getInstance().getReference("Interest").child("Business").push().setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
//                    dialog.dismiss();

                    Toast.makeText(Add_Post_Page.this, "The Post has Created Successfully", Toast.LENGTH_SHORT).show();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Add_Post_Page.this, "Make sure you don't miss anything or you connect to network", Toast.LENGTH_SHORT).show();
                }
            });


        }else if (chk_account.isChecked()){


            FirebaseDatabase.getInstance().getReference("Interest").child("Accounting").push().setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
//                    dialog.dismiss();

                    Toast.makeText(Add_Post_Page.this, "The Post has Created Successfully", Toast.LENGTH_SHORT).show();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Add_Post_Page.this, "Make sure you don't miss anything or you connect to network", Toast.LENGTH_SHORT).show();
                }
            });


        }





    }


}