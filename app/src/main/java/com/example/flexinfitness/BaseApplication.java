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
    public static final String testChannel3 = "testing three";
    public static final String testChannel4 = "testing four";

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

            NotificationChannel channel3 = new NotificationChannel(
                    testChannel3,
                    "Channel_3",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            channel1.setDescription("This is channel 3");

            NotificationChannel channel4 = new NotificationChannel(
                    testChannel4,
                    "Channel_4",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel2.setDescription("This is channel 4");


            NotificationManager manager  = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
            manager.createNotificationChannel(channel2);
            manager.createNotificationChannel(channel3);
            manager.createNotificationChannel(channel4);

        }
    }
}

