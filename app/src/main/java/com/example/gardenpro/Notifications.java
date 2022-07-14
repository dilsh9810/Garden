package com.example.gardenpro;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Notifications extends Service {
    private static final String TAG = "notificationchecking";
    private DatabaseReference referance;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        notification();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    //set the notification channel for motor status

    private void notification(){

            referance = FirebaseDatabase.getInstance().getReference();
            referance.child("SensorRealtime").child("Water_Pump_Status").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Log.d(TAG, "onDataChange: "+snapshot.getValue());
                    if (snapshot.getValue().toString() == "1") {
                            Log.d(TAG, "onChildChanged: changed");
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                NotificationChannel channel = new NotificationChannel("n", "n", NotificationManager.IMPORTANCE_DEFAULT);

                                NotificationManager manager = getSystemService(NotificationManager.class);
                                manager.createNotificationChannel(channel);
                            }

                            NotificationCompat.Builder builder = new NotificationCompat.Builder(Notifications.this, "n")
                                    .setVibrate((new long[]{0, 1000, 500, 2000}))
                                    .setContentTitle("Motor Status")
                                    .setContentText("the motor has started...")
                                    .setSmallIcon(R.drawable.ic_notification)
                                    .setAutoCancel(true);

                            NotificationManagerCompat managerCompat = NotificationManagerCompat.from(Notifications.this);
                            managerCompat.notify(999, builder.build());

                        }else if (snapshot.getValue().toString() == "0") {
                        Log.d(TAG, "onChildChanged: changed");
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            NotificationChannel channel = new NotificationChannel("n", "n", NotificationManager.IMPORTANCE_DEFAULT);

                            NotificationManager manager = getSystemService(NotificationManager.class);
                            manager.createNotificationChannel(channel);
                        }

                        NotificationCompat.Builder builder = new NotificationCompat.Builder(Notifications.this, "n")
                                .setVibrate((new long[]{0, 1000, 500, 2000}))
                                .setContentTitle("Motor Status")
                                .setContentText("the motor has stopped...")
                                .setSmallIcon(R.drawable.ic_notification)
                                .setAutoCancel(true);

                        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(Notifications.this);
                        managerCompat.notify(999, builder.build());

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(Notifications.this, "error occurred"+error.getCode(), Toast.LENGTH_SHORT).show();
                }
            });

                    }








}
