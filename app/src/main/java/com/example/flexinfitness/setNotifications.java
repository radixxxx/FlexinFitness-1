package com.example.flexinfitness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import static com.example.flexinfitness.BaseApplication.testChannel1;
import static com.example.flexinfitness.BaseApplication.testChannel2;
import static com.example.flexinfitness.BaseApplication.testChannel3;
import static com.example.flexinfitness.BaseApplication.testChannel4;

public class setNotifications extends AppCompatActivity {
    private NotificationManagerCompat notificationManager;
    private EditText titleEditText;
    private EditText messageEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_notifications);

        notificationManager = NotificationManagerCompat.from(this);
    }

    // Log notification
    public void sendOnChannel1(View view) {
        String title = "Logs";
        String message = "Remember to log your food daily!";

        Intent activityIntent = new Intent(this, createLogEntry.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, activityIntent,0);


        Notification notification = new NotificationCompat.Builder(this, testChannel1)
                .setSmallIcon(R.drawable.ic_one)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_EVENT)
                .setContentIntent(contentIntent)
                .build();

        notificationManager.notify(1, notification);
    }

    //Empty
    public void sendOnChannel2(View view) {
        String title = "title2";
        String message = "message2";

        Notification notification = new NotificationCompat.Builder(this, testChannel2)
                .setSmallIcon(R.drawable.ic_two)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_EVENT)
                .build();

        notificationManager.notify(2, notification);
    }

    //Empty
    public void sendOnChannel3(View view) {
        String title = "title3";
        String message = "message3";

        Notification notification = new NotificationCompat.Builder(this, testChannel3)
                .setSmallIcon(R.drawable.ic_three)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_EVENT)
                .build();

        notificationManager.notify(3, notification);
    }

    //Empty
    public void sendOnChannel4(View view) {
        String title = "title4";
        String message = "message4";

        Notification notification = new NotificationCompat.Builder(this, testChannel4)
                .setSmallIcon(R.drawable.ic_four)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_EVENT)
                .build();

        notificationManager.notify(4, notification);
    }
}
