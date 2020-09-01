package com.example.application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.application.R.drawable.invisiblebutton;

public class userdashboard extends AppCompatActivity {
    String workerid;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Button sendrequest, call, workerarrived, starttime, endtime;
    String dataname;
    String dataphoneno;
    String datalocation;
    TextView name, location, phoneno, bill, status, time;
    String userid;
    Timer myTimer;
    TimerTask doThis;
    RatingBar rate;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdashboard);
        Bundle extras = getIntent().getExtras();
        workerid = extras.getString("workerid");
        name = findViewById(R.id.name_list);
        location = findViewById(R.id.location);
        phoneno = findViewById(R.id.phoneno);
        sendrequest = findViewById(R.id.sendrequest);
        workerarrived = findViewById(R.id.workerarrived);
        starttime = findViewById(R.id.starttime);
        endtime = findViewById(R.id.endtime);
        call = findViewById(R.id.callbutton);
        bill = findViewById(R.id.bill);
        status = findViewById(R.id.workerstatus);
       rate = (RatingBar) findViewById(R.id.ratingBar);
        final Chronometer time = (Chronometer) findViewById(R.id.chrono);
        myTimer = new Timer();
        final int delay = 1000;
        final int period = 2000;
        try {
            FileInputStream fileIn = openFileInput("mytextfile.txt");
            InputStreamReader InputRead = new InputStreamReader(fileIn);
            char[] inputBuffer = new char[100];
            String s = "";
            int charRead;
            while ((charRead = InputRead.read(inputBuffer)) > 0) {
                String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                s += readstring;
                userid = s;
            }
            InputRead.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        endtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "this one is also click", Toast.LENGTH_LONG).show();
                myTimer.cancel();
            }
        });
        getdata(workerid);
        sendrequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String request = "Your request is set to the worker";
                final Map<String, Object> user = new HashMap<>();
                user.put("userid", userid);
                user.put("workerid", workerid);
                user.put("accepted", "false");
                user.put("worker_phone", "");
                user.put("coming", "not");
                user.put("arrived", "not");
                user.put("totalbill", "0");
                user.put("time", "0");
                db.collection("jobs")
                        .add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                String data;
                                data = documentReference.getId();
                                try {
                                    FileOutputStream fileout = openFileOutput("jobid.txt", MODE_PRIVATE);
                                    OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                                    outputWriter.write(data);
                                    outputWriter.close();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                Log.w("first", "here1");
                                db.collection("users")
                                        .document(userid)
                                        .update(
                                                "jobsid", data
                                        );
                                db.collection("worker")
                                        .document(workerid)
                                        .update(
                                                "jobsid", data
                                        );
                                final DocumentReference docRef = db.collection("worker").document(workerid);
                                final String finalS = data;
                                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                        if (task.isSuccessful()) {
                                            DocumentSnapshot document = task.getResult();
                                            if (document.exists()) {
                                                String phone;
                                                phone = (String) document.get("phone");
                                                db.collection("jobs")
                                                        .document(finalS)
                                                        .update(
                                                                "worker_phone", phone
                                                        );
                                            }
                                        }
                                    }
                                });
                            }
                        });
                status.setText(request);
                endtime.setEnabled(false);
                sendrequest.setEnabled(false);
                sendrequest.setBackgroundResource(invisiblebutton);
                doThis = new TimerTask() {
                    public void run() {
                        String jobid = "nothing here";
                        try {
                            FileInputStream fileIn = openFileInput("jobid.txt");
                            InputStreamReader InputRead = new InputStreamReader(fileIn);
                            char[] inputBuffer = new char[100];
                            String s = "";
                            int charRead;
                            while ((charRead = InputRead.read(inputBuffer)) > 0) {
                                String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                                s += readstring;
                            }
                            jobid = s;
                            InputRead.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        final DocumentReference docRef = db.collection("jobs").document(jobid);
                        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()) {
                                        String names = "nothing";
                                        String coming = "not";
                                        String arrived = "not";
                                        String phone ="";
                                        String rating ="";
                                        String available ="";
                                        names = (String) document.get("accepted");
                                        coming = (String) document.get("coming");
                                        arrived = (String) document.get("arrived");
                                        phone = (String) document.get("worker_phone");
                                       // available=(String) document.get("avaliable");
                                        String trues = "true";

                                            if (names.equals(trues)) {
                                                status.setText("worker accepted the job");
                                                phoneno.setText(phone);
                                                call.setEnabled(true);
                                                call.setBackgroundResource(R.drawable.button);
                                                if (coming.equals("yes")) {
                                                    status.setText("your worker coming");
                                                    if (arrived.equals("yes")) {
                                                        status.setText("Worker arrived!..click the arrived button to confirm ");
                                                        workerarrived.setEnabled(true);
                                                        workerarrived.setBackgroundResource(R.drawable.button);
                                                    }
                                                }
                                            }

                                    } else {
                                        Log.w("doucment", "No such document");
                                    }
                                } else {
                                    Log.w("document", "get failed with ", task.getException());
                                    Toast.makeText(getApplicationContext(), "data not 2 exits", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                };
                myTimer.scheduleAtFixedRate(doThis, delay, period);
            }
        });
        workerarrived.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doThis.cancel();
                workerarrived.setEnabled(false);
                workerarrived.setBackgroundResource(invisiblebutton);
                starttime.setEnabled(true);
                starttime.setBackgroundResource(R.drawable.button);

            }
        });
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String callphone = phoneno.getText().toString();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", callphone, null));
                startActivity(intent);
            }
        });
        starttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time.setBase(SystemClock.elapsedRealtime());
                time.start();
                endtime.setEnabled(true);
                endtime.setBackgroundResource(R.drawable.button);
            }
        });
        endtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String totalbill2="";
                time.stop();
                status.setText("Calculation bill");
                starttime.setEnabled(false);
                starttime.setBackgroundResource(invisiblebutton);
                long totaltime = SystemClock.elapsedRealtime() - time.getBase();
                long totalbill = totaltime * 25;
                totalbill2=Long.toString(totalbill);


                try {
                    FileInputStream fileIn = openFileInput("jobid.txt");
                    InputStreamReader InputRead = new InputStreamReader(fileIn);
                    char[] inputBuffer = new char[100];
                    String s = "";
                    int charRead;
                    while ((charRead = InputRead.read(inputBuffer)) > 0) {
                        String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                        s += readstring;
                    }
                    db.collection("jobs")
                            .document(s)
                            .update(
                                    "totalbill", totalbill2,
                                    "time", totaltime
                            );
                    InputRead.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
               String totalbills=Long.toString(totalbill);
                bill.setText(totalbills);
                status.setText("Bill Calculated");
            }
        });
    }

    public void getdata(String workerid) {

        final DocumentReference docRef = db.collection("worker").document(workerid);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        String rating;
                        dataname = (String) document.get("name");
                        datalocation = (String) document.get("location");
                        dataphoneno = (String) document.get("phone");
                        rating = (String) document.get("rating");
                        name.setText(dataname);
                        location.setText(datalocation);
                        rate.setRating(Float.parseFloat(rating));
                    } else {
                        Log.w("doucment", "No such document");
                        Toast.makeText(getApplicationContext(), "data not exits", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Log.w("document", "get failed with ", task.getException());
                    Toast.makeText(getApplicationContext(), "data not 2 exits", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}


