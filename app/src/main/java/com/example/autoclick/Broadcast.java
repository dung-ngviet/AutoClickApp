package com.example.autoclick;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

public class Broadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)){
            openService(context);
        }
    }

    private void openService(Context context) {
        Intent openSv = new Intent(context, ClickServices.class);
        context.startForegroundService(openSv);
        Log.d("Broadcast", "Open services");

    }
}
