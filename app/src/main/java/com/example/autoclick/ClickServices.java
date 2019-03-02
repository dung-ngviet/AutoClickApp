package com.example.autoclick;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import org.opencv.android.OpenCVLoader;


public class ClickServices extends IntentService {

    private static final String TAG = ClickServices.class.getSimpleName();

    public static Context context;
    public ClickServices(){
        super(TAG);
    }
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        //TODO do all work here
        ClickUtils clickUtils = new ClickUtils();
        String [] args ={getResources().getDrawable(R.drawable.contain_fb_lite1).toString(), getResources().getDrawable(R.drawable.facebooklite).toString()};
        clickUtils.clickFBLite(args, this);
    }

    @Override
    public void onCreate() {
        context = this;
        super.onCreate();
        if (Build.VERSION.SDK_INT >= 26) {
            String CHANNEL_ID = "my_channel_01";
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);

            ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).createNotificationChannel(channel);

            Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setContentTitle("Service")
                    .setContentText("This is foreground service")
                    .build();

            startForeground(1, notification);
        }
    }


}
