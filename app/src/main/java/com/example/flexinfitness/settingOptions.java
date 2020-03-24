package com.example.flexinfitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class settingOptions extends AppCompatActivity {
    Button notifications;
    Button settings1;
    Button setting2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_options);
        notifications = findViewById(R.id.notificationButton);

        notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSettings = new Intent(getApplicationContext(), setNotifications.class);
                startActivity(goToSettings);

            }
        });
    }



}
