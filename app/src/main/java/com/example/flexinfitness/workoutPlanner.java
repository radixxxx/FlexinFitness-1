package com.example.flexinfitness;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

// start workoutPlanner class ======================================================================
public class workoutPlanner extends AppCompatActivity implements View.OnClickListener
{
    // declare View & ViewGroups
    Button doneButton;
    Button submitButton;

    EditText workoutNameEditText;
    EditText dateToPerformEditText;
    EditText timeToPerformEditText;
    EditText restBetweenSetsEditText;

    LinearLayout linearLayout;

    // start onCreate() ============================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_planner);

        // connect Views & ViewGroups to XMLs
        doneButton = findViewById(R.id.doneButton);
        submitButton = findViewById(R.id.submitButton);

        workoutNameEditText = findViewById(R.id.workoutNameEditText);
        dateToPerformEditText = findViewById(R.id.dateToPerformEditText);
        timeToPerformEditText = findViewById(R.id.timeToPerformEditText);
        restBetweenSetsEditText = findViewById(R.id.restBetweenSetsEditText);

        linearLayout = findViewById(R.id.linearLayoutInsideScrollView);
    } // end onCreate() ============================================================================

    // start onClick() =============================================================================
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            // case R.id.<nameOfViewID>
        }

    } // end onClick() =============================================================================
} // end workoutPlanner class ======================================================================
