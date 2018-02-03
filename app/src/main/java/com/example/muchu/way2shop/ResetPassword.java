package com.example.muchu.way2shop;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassword extends AppCompatActivity {


    EditText etEmail;
    Button btResetPassword,btBack;
    FirebaseAuth mAuth;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        etEmail = (EditText) findViewById(R.id.et_email_reset_password);
        btResetPassword = (Button) findViewById(R.id.bt_Reset_password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar_Reset_password);
        btBack = (Button) findViewById(R.id.bt_back_reset);

        mAuth = FirebaseAuth.getInstance();

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        btResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = etEmail.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(),"please enter Email id",Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"check your Email",Toast.LENGTH_LONG).show();

                        }
                        else{
                            Toast.makeText(getApplicationContext(),"please enter registered Email id",Toast.LENGTH_LONG).show();
                        }
                        progressBar.setVisibility(View.GONE);
                        finish();
                    }
                });


            }
        });


    }
}
