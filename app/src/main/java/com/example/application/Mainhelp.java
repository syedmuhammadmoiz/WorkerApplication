package com.example.application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.application.ui.mainhelp.MainhelpFragment;

public class Mainhelp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainhelp_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainhelpFragment.newInstance())
                    .commitNow();
        }
    }
}
