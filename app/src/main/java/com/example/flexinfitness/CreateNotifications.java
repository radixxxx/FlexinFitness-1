package com.example.flexinfitness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


import android.app.Notification;
import android.app.NotificationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import static com.example.flexinfitness.BaseApplication.testChannel1;
import static com.example.flexinfitness.BaseApplication.testChannel2;

public class CreateNotifications extends AppCompatActivity {
    private NotificationManagerCompat notificationManager;
    private EditText titleEditText;
    private EditText messageEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_notifications);

        notificationManager = NotificationManagerCompat.from(this);
        titleEditText = findViewById(R.id.titleEditText);
        messageEditText = findViewById(R.id.messageEditText);
    }

    public void sendOnChannel1(View view) {
        String title = titleEditText.getText().toString();
        String message = messageEditText.getText().toString();

        Notification notification = new NotificationCompat.Builder(this, testChannel1)
                .setSmallIcon(R.drawable.ic_one)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_EVENT)
                .build();

        notificationManager.notify(1, notification);
    }

    public void sendOnChannel2(View view) {
        String title = titleEditText.getText().toString();
        String message = messageEditText.getText().toString();

        Notification notification = new NotificationCompat.Builder(this, testChannel2)
                .setSmallIcon(R.drawable.ic_two)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setCategory(NotificationCompat.CATEGORY_EVENT)
                .build();

        notificationManager.notify(2, notification);
    }
}
