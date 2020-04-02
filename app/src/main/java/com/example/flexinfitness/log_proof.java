package com.example.flexinfitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.SymbolTable;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

// start log_proof class ===========================================================================
public class log_proof extends AppCompatActivity
{
    // declaring Views & ViewGroups
    TextView workoutName;
    TextView date;
    TextView startTime;
    TextView duration;
    TextView bodyWeight;

    // start onCreate() ============================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_proof);

        connectViews();
        getWorkoutInformation();

    } // end onCreate() ============================================================================

    // start connectViews() ========================================================================
    public void connectViews()
    {
        // connecting View & ViewGroups
        workoutName = findViewById(R.id.rightWorkoutNameTextView);
        date = findViewById(R.id.rightDateTextView);
        startTime = findViewById(R.id.rightStartTimeTextView);
        duration = findViewById(R.id.rightDurationTextView);
        bodyWeight = findViewById(R.id.rightBodyWeightTextView);
    } // end connectViews() ========================================================================

    // start getWorkoutInformation() ===============================================================
    public void getWorkoutInformation()
    {
        String str_workoutName;
        String str_workoutDate;
        String str_duration;
        String str_startTime;
        String str_bodyWeight;

        Intent intent;                                                          // create intent
        intent = getIntent();                                                   // get intent received from other activity

        str_workoutName = intent.getExtras().getString("WORKOUT_NAME");    // get info
        str_workoutDate = intent.getExtras().getString("WORKOUT_DATE");
        str_duration  = intent.getExtras().getString("DURATION");
        str_startTime  = intent.getExtras().getString("START_TIME");
        str_bodyWeight = intent.getExtras().getString("BODYWEIGHT");

        workoutName.setText(str_workoutName);                                   // set info
        date.setText(str_workoutDate);
        duration.setText(str_duration);
        startTime.setText(str_startTime);
        bodyWeight.setText(str_bodyWeight);
    } // end getWorkoutInformation() ===============================================================
} // end log_proof class
