package com.example.application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class login_2 extends AppCompatActivity {
    Button button2;
    TextView email2,password2;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_2);
        button2=findViewById(R.id.login_button_2);
        password2= (TextView)findViewById(R.id.pass_2);
        email2= (TextView) findViewById(R.id.Email_2);
        mAuth = FirebaseAuth.getInstance();

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email,passwords;
                email=email2.getText().toString().trim();
                passwords=password2.getText().toString().trim();
                mAuth.signInWithEmailAndPassword(email, passwords)
                        .addOnCompleteListener(login_2.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    String userid = mAuth.getUid();
                                    Intent intent = new Intent(login_2.this, MainActivity2.class);
                                    intent.putExtra("workerid",userid);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    //this is new file want to add
                                    startActivity(intent);

                                } else {// If sign in fails, display a message to the user.
                                    Toast.makeText(getApplicationContext(), "Email or password wrong", Toast.LENGTH_LONG).show();
                                }

                            }
                        });
            }

        });

}
}