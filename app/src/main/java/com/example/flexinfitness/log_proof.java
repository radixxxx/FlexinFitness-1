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

        // connecting View & ViewGroups
        workoutName = findViewById(R.id.rightWorkoutNameTextView);
        date = findViewById(R.id.rightDateTextView);
        startTime = findViewById(R.id.rightStartTimeTextView);
        duration = findViewById(R.id.rightDurationTextView);
        bodyWeight = findViewById(R.id.rightBodyWeightTextView);

        // get the deets
        Intent intent = getIntent();
        String str_workoutName = intent.getExtras().getString("WORKOUT_NAME");
        String str_workoutDate = intent.getStringExtra("workoutDate");

        // set the information
        System.out.println(str_workoutName);
        System.out.println(str_workoutDate);
        workoutName.setText(str_workoutName);
        date.setText(str_workoutDate);
    } // end onCreate() ============================================================================
} // end log_proof class
