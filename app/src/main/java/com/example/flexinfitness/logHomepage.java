package com.example.flexinfitness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

//start logHomepage class =====================================================================
public class logHomepage extends AppCompatActivity implements View.OnClickListener
{
    // Declare View & ViewGroup variables
    Button addLogButton;
    Button goBackToDashboardButton;

    LinearLayout linearLayout;

    ConstraintLayout constraintLayout;

    // start onCreate() ============================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_diary_homepage);

        // Connect View & ViewGroup variables to their XML id's
        constraintLayout = findViewById(R.id.constraintLayout);

        linearLayout = findViewById(R.id.linearLayout);

        addLogButton = findViewById(R.id.buttonADDLOG);
        goBackToDashboardButton = findViewById(R.id.goBackToDashboardButton);

        // setting the onClick's for the buttons
        addLogButton.setOnClickListener(this);
        goBackToDashboardButton.setOnClickListener(this);
    } // end onCreate() ============================================================================

    // start onClick() =============================================================================
    @Override
    public void onClick(View view)
    {
        switch(view.getId())
        {
            // create a log entry
            case R.id.buttonADDLOG:
                Intent createLogEntry = new Intent(getApplicationContext(), createLogEntry.class);
                startActivity(createLogEntry);
                break;
            case R.id.goBackToDashboardButton:
                Intent goBackToDashboard = new Intent(getApplicationContext(), DashBoard.class);
                startActivity(goBackToDashboard);
                break;
        } // end switch(view.getId()
    } // end onClick() =============================================================================

} // end logDiaryHomepage class ====================================================================