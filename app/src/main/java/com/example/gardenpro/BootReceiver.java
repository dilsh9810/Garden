package com.example.gardenpro;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            Log.d("TAGStarted", "onReceive: booted");
            Intent serviceIntent = new Intent(context,Notifications.class);
            context.startService(serviceIntent);
        }

        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            Log.d("TAGStarted", "onReceive: booted");
        }
    }
}

