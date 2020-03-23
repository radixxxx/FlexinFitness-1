package com.example.flexinfitness;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import java.sql.ClientInfoStatus;
import java.util.List;

public class BaseApplication extends Application {
    public static final String testChannel1 = "testing one";
    public static final String testChannel2 = "testing two";

    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannels();

    }
    private void createNotificationChannels(){
        // Checks if version is at least API 26
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel1 = new NotificationChannel(
                    testChannel1,
                    "Channel_1",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            channel1.setDescription("This is channel 1");

            NotificationChannel channel2 = new NotificationChannel(
            testChannel2,
            "Channel_2",
            NotificationManager.IMPORTANCE_HIGH
            );



            channel2.setDescription("This is channel 2");
            NotificationManager manager  = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
            manager.createNotificationChannel(channel2);

        }
        else{

        }
    }
}

