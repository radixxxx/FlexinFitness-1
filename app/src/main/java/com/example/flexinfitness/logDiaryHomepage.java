package com.example.flexinfitness;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class logDiaryHomepage extends AppCompatActivity
{
    // Declare view variables
    Button b_addLog;
    LinearLayout l_linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_diary_homepage);

        // Assign the views from the layout file to the view variables
        b_addLog = (Button) findViewById(R.id.buttonADDLOG);
        l_linearLayout = (LinearLayout) findViewById(R.id.linearLayout);

        // Setting the OnClickListener within 'onCreate' method
        b_addLog.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Create a new button
                Button b_logEntry1;
                b_logEntry1 = new Button(logDiaryHomepage.this);

                //Set properties
                int ENTRY_1 = 1;
                b_logEntry1.setText("ENTRY 1");
                b_logEntry1.setId(ENTRY_1);
                l_linearLayout.addView(b_logEntry1);
            }
        });
    }
}
