package com.example.application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class signup_1 extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "this is tah";
    TextView txt_1;
    EditText sname, mail, pass, pass_1;
    Button btn;

    private FirebaseAuth mAuth;
    FirebaseFirestore db;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_1);

        sname = findViewById(R.id.fname);
        pass_1 = findViewById(R.id.repass);
        mail = findViewById(R.id.Addresss);
        pass = findViewById(R.id.Pass);
        btn = findViewById(R.id.buttons);
        txt_1 = findViewById(R.id.sign_t);
        mAuth = FirebaseAuth.getInstance();
        btn.setOnClickListener(this);
        txt_1.setOnClickListener(this);
        db = FirebaseFirestore.getInstance();

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
   //     FirebaseUser currentUser = mAuth.getCurrentUser();
   //     Toast.makeText(getApplicationContext(), "You are currently sign in", Toast.LENGTH_LONG).show();
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttons) {
            Toast.makeText(getApplicationContext(), "clicked", Toast.LENGTH_LONG).show();
             String email = mail.getText().toString().trim();
             String password = pass.getText().toString().trim();
            String check_password = pass_1.getText().toString().trim();
            final String name= sname.getText().toString().trim();
           final String email_to_store=email;
           final String password_to_store=password;
            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)|| TextUtils.isEmpty(check_password)||TextUtils.isEmpty(name)||(!TextUtils.equals(password,check_password))) {
                Toast.makeText(getApplicationContext(), "Please fill all the field", Toast.LENGTH_LONG).show();

            } else {
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    Log.d(TAG, "createUserWithEmail:success");
                                    String userid = mAuth.getUid();
                                    Toast.makeText(getApplicationContext(), "Account is created " + userid, Toast.LENGTH_LONG).show();
                                    Map<String, Object> users = new HashMap<>();
                                    users.put("username", name);
                                  users.put("email",email_to_store);
                                    users.put("password",password_to_store);


                                    DocumentReference userref=db.collection("users").document(String.valueOf(userid));
                                      userref.set(users);
                                      Intent intent = new Intent(signup_1.this,login_1.class);
                                      startActivity(intent);


                                } else {
                                    Toast.makeText(getApplicationContext(), "Fail to create account"+task.getException(), Toast.LENGTH_LONG).show();


                                }


                            }
                        });

            }


        }


    }
}