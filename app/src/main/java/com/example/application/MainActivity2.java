package com.example.application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        BottomNavigationView bottomnav =findViewById(R.id.bottom_navigation);
       bottomnav.setOnNavigationItemSelectedListener(navListener);
       getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new homrworkerfragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedfragment=null;
                    switch (menuItem.getItemId()){
                        case R.id.workernav_home:
                            selectedfragment = new homrworkerfragment();
                            break;
                        case R.id.workernav_dashboard:
                            selectedfragment = new dashboardworkerfragment();
                            break;
                        case R.id.workernav_account:
                            selectedfragment = new accountworkerfragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedfragment).commit();
                    return true;

                }
            };
}