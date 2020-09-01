package com.example.application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.application.ui.mainnotification.MainnotificationFragment;

public class Mainnotification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainnotification_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainnotificationFragment.newInstance())
                    .commitNow();
        }
    }
}
