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

public class CreateNotifications extends AppCompatActivity {
    private NotificationManagerCompat notificationManager;
    private EditText titleEditText;
    private EditText messageEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_notifications);

        notificationManager = NotificationManagerCompat.from(this);
        titleEditText.findViewById(R.id.titleEditText);
        messageEditText.findViewById(R.id.messageEditText);
    }

    public void sendOnChannel1(View view) {
        Notification notification = new NotificationCompat.Builder(this, testChannel1)
                .setSmallIcon()
    }

    public void sendOnChannel2(View view) {
    }
}
