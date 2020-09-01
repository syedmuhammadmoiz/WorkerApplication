package com.example.application.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.application.R;

public class myadopter extends  RecyclerView.Adapter<myadopter.myholder> {
    private Context context ;
    private String[] data;

    public myadopter(String[] data){
        this.data =data;
    }
    @Override
    public myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.contact_list_layout,parent,false);
        context = parent.getContext();
        final myholder holder = new myholder(view);
        return new myholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final myholder holder, int position) {
        final String con = data[position];
        holder.contact.setText(con);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //    Intent it = new Intent(context,second.class);

//                it.putExtra("name",con);
                //              context.startActivity(it);

            }
        });




    }


    @Override
    public int getItemCount() {
        return data.length;
    }

    public class myholder extends RecyclerView.ViewHolder{
        public ImageView imgcon;
        public TextView contact;
        public myholder(@NonNull View itemView)
        {
            super(itemView);
            imgcon=itemView.findViewById(R.id.imagecontact);
            contact= itemView.findViewById(R.id.firebasename);
        }
    }
}
