package com.example.mdconline;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginpage extends AppCompatActivity {

    TextInputEditText txtEmail, txtpassword;

    Button signIn;
    TextView signUp;

    FirebaseAuth firebaseAuth =  FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_loginpage);

        txtEmail = findViewById(R.id.email);
        txtpassword =  findViewById((R.id.password));
        signIn = findViewById(R.id.signinbtn);
        signUp = findViewById(R.id.signuptxt);


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginpage.this,signup.class);
                startActivity(intent);
                finish();

            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, password;

                email = String.valueOf(txtEmail.getText());
                password = String.valueOf(txtpassword.getText());

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(loginpage.this, "Enter An Email", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(loginpage.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(loginpage.this, "Login Successfull!!", Toast.LENGTH_SHORT).show();
                                    Intent intent =  new Intent(loginpage.this,signup.class);
                                    startActivity(intent);
                                    finish();

                                }
                                else{
                                    Toast.makeText(loginpage.this, "Authentication Failed!!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


            }
        });

        }

    }