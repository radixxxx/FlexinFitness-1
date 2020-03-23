package com.example.flexinfitness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.Vector;

public class createLogEntry extends AppCompatActivity implements View.OnClickListener
{
    // declare View & ViewGroup variables
    EditText workoutNameEditText;
    EditText dateEditText;
    EditText startTimeEditText;
    EditText durationEditText;
    EditText bodyWeightEditText;

    TextView rightWorkoutNameTextView;
    TextView rightDateTextView;
    TextView rightStartTimeTextView;
    TextView rightDurationTextView;
    TextView rightBodyWeightTextView;
    TextView dateTextView;

    LinearLayout rightLinearLayout;
    LinearLayout editTextLinearyLayout;

    ScrollView scrollView;

    Button submitValuesButton;
    Button doneButton;

    // start onCreate() ============================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_log_entry);

        // connect View & ViewGroups to their XML's
        workoutNameEditText = findViewById(R.id.workoutNameEditText);
        dateEditText = findViewById(R.id.dateEditText);
        startTimeEditText = findViewById(R.id.startTimeEditText);
        durationEditText = findViewById(R.id.durationEditText);
        bodyWeightEditText = findViewById(R.id.bodyWeightEditText);

        rightWorkoutNameTextView = findViewById(R.id.rightWorkoutNameTextView);
        rightDateTextView = findViewById(R.id.rightDateTextView);
        rightStartTimeTextView = findViewById(R.id.rightStartTimeTextView);
        rightDurationTextView = findViewById(R.id.rightDurationTextView);
        rightBodyWeightTextView = findViewById(R.id.rightBodyWeightTextView);
        dateTextView = findViewById(R.id.dateTextView);

        submitValuesButton = findViewById(R.id.submitValuesButton);
        doneButton = findViewById(R.id.doneButton);

        scrollView = findViewById(R.id.scrollView);

        // set the button's onClick's
        submitValuesButton.setOnClickListener(this);
        doneButton.setOnClickListener(this);

        // set the 'TextViews' onClick's
        rightWorkoutNameTextView.setOnClickListener(this);
        rightDateTextView.setOnClickListener(this);
        rightStartTimeTextView.setOnClickListener(this);
        rightDurationTextView.setOnClickListener(this);
        rightBodyWeightTextView.setOnClickListener(this);

        // set the layout's onClick's
        rightLinearLayout = findViewById(R.id.rightLinearLayout);
        editTextLinearyLayout = findViewById(R.id.editTextLinearLayout);

    } // end onCreate() ============================================================================

    // start onClick() =============================================================================
    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            // if they click anyone of the TextViews
            case R.id.rightWorkoutNameTextView:
            case R.id.rightDateTextView:
            case R.id.rightStartTimeTextView:
            case R.id.rightDurationTextView:
            case R.id.rightBodyWeightTextView:
                // hide TextViews and expose the EditTexts' and submitValuesButton
                rightLinearLayout.setVisibility(View.GONE);
                editTextLinearyLayout.setVisibility(View.VISIBLE);
                submitValuesButton.setVisibility(View.VISIBLE);
                break;
            case R.id.submitValuesButton:
                // get the input & display it on the TextViews
                submitValueButtonActionOne();
                // populate the scrollView log with a new entry
                submitValueButtonActionTwo();
                break;
            case R.id.doneButton:
                Intent backToLogHomepage = new Intent(getApplicationContext(), logHomepage.class);
                startActivity(backToLogHomepage);
                break;

        }
    } // end onClick() =============================================================================

    // start submitValueButtonActionOne() ==========================================================
    public void submitValueButtonActionOne()
    {
        String str_workoutName = workoutNameEditText.getText().toString();
        rightWorkoutNameTextView.setText(str_workoutName);

        String str_date = dateEditText.getText().toString();
        rightDateTextView.setText(str_date);
        dateTextView.setText(str_date);

        String str_startTime = startTimeEditText.getText().toString();
        rightStartTimeTextView.setText((str_startTime + "pm"));

        String str_duration = durationEditText.getText().toString();
        rightDurationTextView.setText((str_duration + "mins"));

        String str_bodyWeight = bodyWeightEditText.getText().toString();
        rightBodyWeightTextView.setText((str_bodyWeight + "lbs"));

        dateTextView.setVisibility(View.VISIBLE);
        editTextLinearyLayout.setVisibility(View.GONE);
        rightLinearLayout.setVisibility(View.VISIBLE);
        submitValuesButton.setVisibility(View.GONE);
    } // end submitValueButtonActionOne() ==========================================================

    // start submitValueButtonActionTwo() ==========================================================
    public void submitValueButtonActionTwo()
    {


    } //end submitValueButtonActionTwo() ===========================================================
} // end class createLogEntry ======================================================================
