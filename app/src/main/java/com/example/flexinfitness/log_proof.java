package com.example.flexinfitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

// start log_proof class ===========================================================================
public class log_proof extends AppCompatActivity
{
    // declaring Views & ViewGroups
    TextView workoutName_txtV;
    TextView date_txtV;
    TextView startTime_txtV;
    TextView duration_txtV;
    TextView bodyweight_txtV;

    String str_workoutName;
    String str_workoutDate;
    String str_duration;
    String str_startTime;
    String str_bodyWeight;

    // start onCreate() ============================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_proof);

        connectViews();
        getWorkoutData();
        setWorkoutData();
    } // end onCreate() ============================================================================

    // start getWorkoutInformation() ===============================================================
    public void getWorkoutData()
    {
        /* in order to get the bundle of information that we passed from 'log', after clicking the newly
            created TextView, we need to declare a new intent to capture the one that we passed in
         */
        Intent workoutData;
        workoutData = getIntent();

        str_workoutName = workoutData.getExtras().getString("WORKOUT_NAME");
        str_workoutDate = workoutData.getExtras().getString("WORKOUT_DATE");
        str_duration  = workoutData.getExtras().getString("DURATION");
        str_startTime  = workoutData.getExtras().getString("START_TIME");
        str_bodyWeight = workoutData.getExtras().getString("BODYWEIGHT");
    } // end getWorkoutInformation() ===============================================================

    // start setWorkoutData() ======================================================================
    public void setWorkoutData()
    {
        workoutName_txtV.setText(str_workoutName);
        date_txtV.setText(str_workoutDate);
        duration_txtV.setText(str_duration);
        startTime_txtV.setText(str_startTime);
        bodyweight_txtV.setText(str_bodyWeight);
    } // end setWorkoutData() ======================================================================

    // start connectViews() ========================================================================
    public void connectViews()
    {
        // connecting View & ViewGroups
        workoutName_txtV = findViewById(R.id.rightWorkoutNameTextView);
        date_txtV = findViewById(R.id.rightDateTextView);
        startTime_txtV = findViewById(R.id.rightStartTimeTextView);
        duration_txtV = findViewById(R.id.rightDurationTextView);
        bodyweight_txtV = findViewById(R.id.rightBodyWeightTextView);
    } // end connectViews() ========================================================================
} // end log_proof class
