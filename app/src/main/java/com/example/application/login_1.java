package com.example.application;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class login_1 extends AppCompatActivity {

    TextView txt_s;
    Button btn_login1;
    private FirebaseAuth mAuth;
    TextView username,password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_1);

        txt_s=findViewById(R.id.sign_t);
        btn_login1=findViewById(R.id.login_button);
        username=findViewById(R.id.Email_1);
        password=findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();

       txt_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent r=new Intent(login_1.this,signup_1.class);
                startActivity(r);
            }
        });
        btn_login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email,passwords;
                email=username.getText().toString().trim();
                passwords=password.getText().toString().trim();
                mAuth.signInWithEmailAndPassword(email, passwords)
                        .addOnCompleteListener(login_1.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                  String userid=mAuth.getUid();
                                    try {
                                        FileOutputStream fileout=openFileOutput("mytextfile.txt", MODE_PRIVATE);
                                        OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
                                        outputWriter.write(userid);
                                        outputWriter.close();
                                        Toast.makeText(getBaseContext(), "File saved successfully!",
                                                Toast.LENGTH_SHORT).show();

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                  Intent intent=new Intent(login_1.this,MainActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                  startActivity(intent);

                                } else {// If sign in fails, display a message to the user.
                                    Toast.makeText(getApplicationContext(),"Email or password wrong",Toast.LENGTH_LONG).show();
                                }

                                // ...
                            }
                        });



            }
        });
    }
}



