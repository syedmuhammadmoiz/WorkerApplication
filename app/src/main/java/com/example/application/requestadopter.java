package com.example.application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application.ui.home.myadopter;
import com.google.firebase.firestore.FirebaseFirestore;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

public class requestadopter extends RecyclerView.Adapter<requestadopter.ItemViewHolder> {
    private String[] name;
    TextView status;
    private String[] location;
    private String[] jobs;
    private Context context;


    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public requestadopter(String[] name, String[] location,String []jobs) {
        this.name=name;
        this.location=location;
        this.jobs=jobs;
    }
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.request_list,parent,false);

        context = parent.getContext();
        final ItemViewHolder holder = new ItemViewHolder(view);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

        final String con = name[position];
        final String con2 = location[position];
        holder.name.setText(con);
        holder.location.setText(con2);

    }


    @Override
    public int getItemCount() {
        return name.length;
    }
    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView location;
        public Button accept,reject;
        public ItemViewHolder(View itemView) {
            super(itemView);
            name= itemView.findViewById(R.id.name_list);
            location= itemView.findViewById(R.id.location);
            accept=itemView.findViewById(R.id.accept);
            reject=itemView.findViewById(R.id.reject);
            accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    db.collection("jobs")
                            .document(jobs[0])
                            .update(
                                    "accepted", "true"
                            );


                }
            });
           reject.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   db.collection("jobs")
                           .document(jobs[0])
                           .update(
                                   "accepted", "not"
                           );

                   db.collection("worker")
                           .document(jobs[0])
                           .update(
                                   "jobsid", null
                           );

               }
           });

        }
    }
}
