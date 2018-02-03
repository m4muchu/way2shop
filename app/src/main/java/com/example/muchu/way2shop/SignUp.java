package com.example.muchu.way2shop;


import android.content.Intent;
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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {

    EditText etUserName,etEmail,etPassword;
    Button btRegister;
    FirebaseAuth mAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        etUserName = (EditText) findViewById(R.id.et_username_register);
        etEmail =  (EditText) findViewById(R.id.et_email_register);
        etPassword =  (EditText) findViewById(R.id.et_password_register);
        btRegister =  (Button) findViewById(R.id.bt_register);
        progressBar = (ProgressBar) findViewById(R.id.progressBar_register);

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = etUserName.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                final String password = etPassword.getText().toString().trim();

                if (TextUtils.isEmpty(userName)){
                    Toast.makeText(getApplicationContext(),"Please enter user name",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(),"Please enter email id",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(),"Please enter password",Toast.LENGTH_SHORT).show();

                }
                if (password.length() < 6){
                    Toast.makeText(getApplicationContext(),"password is too short, enetr minimum 6 character",Toast.LENGTH_LONG).show();
                }

                progressBar.setVisibility(View.VISIBLE);

                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(getApplicationContext(),"Registration completed",Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);

                        if (!task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Registeration failed,Email id already used",Toast.LENGTH_LONG).show();

                        }
                        else {
                            startActivity(new Intent(SignUp.this,LoginActivity.class));
                            finish();
                        }
                    }
                });

            }
        });



    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}
