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

public class LoginActivity extends AppCompatActivity {
    EditText email,password;
    Button logIn,SignIn;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.editTextTextEmailAddressLog);
        password = findViewById(R.id.editTextTextPasswordLog);
        logIn = findViewById(R.id.buttonLogin);
        SignIn = findViewById(R.id.buttonSign);
        firebaseAuth = FirebaseAuth.getInstance();
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String e = email.getText().toString().trim();
                String p = password.getText().toString().trim();
                if(TextUtils.isEmpty(e)){
                    email.setError("Email is required!");
                    return;
                }
                if(TextUtils.isEmpty(p)){
                    password.setError("Password is required!");
                    return;
                }
                if(p.length() < 6){
                    password.setError("Password should be ");
                    return;
                }
                firebaseAuth.signInWithEmailAndPassword(e,p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        }else{
                            Toast.makeText(getApplicationContext(), "Error! "+ task.getResult(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SignInActivity.class));
            }
        });
    }
}
