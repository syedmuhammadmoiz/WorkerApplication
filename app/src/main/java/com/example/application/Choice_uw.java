package com.example.application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Choice_uw extends AppCompatActivity {

    Button btn,btn_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_uw);
        btn =findViewById(R.id.button_1);
        btn_1 =findViewById(R.id.button_2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent u=new Intent(Choice_uw.this,login_1.class);
                startActivity(u);
            }
        });


        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent w=new Intent(Choice_uw.this,login_2.class);
                startActivity(w);
            }
        });

    }
}