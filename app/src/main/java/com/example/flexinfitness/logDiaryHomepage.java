package com.example.flexinfitness;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class logDiaryHomepage extends AppCompatActivity implements View.OnClickListener
{
    // Declare view variables
    Button addLogButton;
    LinearLayout l_linearLayout;

    // Static Identifier counter
    static int ID = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_diary_homepage);

        // Connect the view variables to XML id's
        l_linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        addLogButton = (Button) findViewById(R.id.buttonADDLOG);
        addLogButton.setOnClickListener(this);

    } // end onCreate


    @Override
    public void onClick(View view)
    {
        switch(view.getId())
        {
            // Adds a new entry to the scroll view
            case R.id.buttonADDLOG:
                // Create log entry
                ID += 1;
                String str_ID = Integer.toString(ID);
                Button createdLogEntry = new Button(this);
                createdLogEntry.setText(("Entry " + str_ID));
                l_linearLayout.addView(createdLogEntry);
                break;
        }
    } // end onClick

} // end logDiaryHomepage
