package com.example.application.ui.home;

import android.content.Intent;
import android.graphics.ColorSpace;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application.MapsActivityuser;
import com.example.application.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    FirebaseFirestore db ;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        View electrition = root.findViewById(R.id.electrition_img);
        View work = root.findViewById(R.id.works_img);
        View paint = root.findViewById(R.id.paint_img);
        View carpanter = root.findViewById(R.id.carpenter_img);
        View plumber = root.findViewById(R.id.plumber_img);
        View services = root.findViewById(R.id.service_img);
        RecyclerView contactlist = (RecyclerView) root.findViewById(R.id.contacts);
        contactlist.setLayoutManager(new LinearLayoutManager(getContext()));
        String[] contacts = {"shahzil","ALi","shahzil","Moiz","raja","shahzal chudary","kant","shoaib"};
        contactlist.setAdapter(new myadopter(contacts));

        electrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MapsActivityuser.class);
                intent.putExtra("field","electriction" );
                startActivity(intent);
            }
        });
        work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MapsActivityuser.class);
                intent.putExtra("field","work" );
                startActivity(intent);

            }
        });
        plumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MapsActivityuser.class);
                intent.putExtra("field","plumber" );
                startActivity(intent);

            }
        });
        paint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MapsActivityuser.class);
                intent.putExtra("field","paint" );
                startActivity(intent);

            }
        });
        carpanter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MapsActivityuser.class);
                intent.putExtra("field","carpanter" );
                startActivity(intent);

            }
        });
        services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MapsActivityuser.class);
                intent.putExtra("field","services" );
                startActivity(intent);

            }
        });


        return root;
    }




}
