package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {
    EditText email,password,rePassword;
    Button signIn,LogIn;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        email = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        rePassword = findViewById(R.id.editTextTextPasswordRe);
        signIn = findViewById(R.id.buttonSignIn);
        LogIn = findViewById(R.id.buttonLog);
        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() != null){
            startActivity(new Intent(this,MainActivity.class));
        }
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String e = email.getText().toString().trim();
                String p = password.getText().toString().trim();
                String re = rePassword.getText().toString().trim();
                if(TextUtils.isEmpty(e)){
                    email.setError("Email is Required");
                    return;
                }

                if(TextUtils.isEmpty(p)){
                    password.setError("password is Required");
                    return;
                }

                if(p.length() < 6){
                    password.setError("Password length should be more than 6 characters");
                    return;
                }
                if(!re.equals(p)){
                    password.setError("Your password and confirm password should match");
                    rePassword.setError("Your password and confirm password should match");
                    return;
                }
                firebaseAuth.createUserWithEmailAndPassword(e,p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Sign In Successful",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SignInActivity.this,MainActivity.class));
                        }else{
                            Toast.makeText(getApplicationContext(),"Error!"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });
    }
}