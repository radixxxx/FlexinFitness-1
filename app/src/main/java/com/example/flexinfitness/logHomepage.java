package com.example.flexinfitness;

import androidx.annotation.Nullable;
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

    public static final int REQUEST_CODE = 1;

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
                startActivityForResult(createLogEntry, REQUEST_CODE);
                break;
            case R.id.goBackToDashboardButton:
                Intent goBackToDashboard = new Intent(getApplicationContext(), DashBoard.class);
                startActivity(goBackToDashboard);
                break;
        } // end switch(view.getId()
    } // end onClick() =============================================================================

    // start onActivityResult() ====================================================================
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if( REQUEST_CODE == requestCode && resultCode == RESULT_OK)
        {
            // extract intent & bundle from the activity
            Intent createLogEntry = getIntent();
            Bundle workoutData = createLogEntry.getExtras();

            // extract the stored data from the bundle
            String str_workoutName = workoutData.getString("WORKOUT_NAME");
            String str_workoutDate = workoutData.getString("WORKOUT_DATE");



        }
    } // end onActivityResult() ====================================================================
} // end logDiaryHomepage class ====================================================================