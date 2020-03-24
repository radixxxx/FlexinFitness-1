package com.example.flexinfitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

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
    LinearLayout editTextLinearLayout;
    LinearLayout fuckLinearLayout;

    ScrollView scrollView;

    Button submitValuesButton;
    Button doneButton;
    Button addExerciseButton;

    // to be used to passes back when the doneButton is clicked
    String str_workoutName;
    String str_date;

    // to hold all of the exercises that they input
    Vector exercises = new Vector();

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
        addExerciseButton = findViewById(R.id.addExerciseButton);

        scrollView = findViewById(R.id.scrollView);

        // set the button's onClick's
        submitValuesButton.setOnClickListener(this);
        doneButton.setOnClickListener(this);
        addExerciseButton.setOnClickListener(this);

        // set the 'TextViews' onClick's
        rightWorkoutNameTextView.setOnClickListener(this);
        rightDateTextView.setOnClickListener(this);
        rightStartTimeTextView.setOnClickListener(this);
        rightDurationTextView.setOnClickListener(this);
        rightBodyWeightTextView.setOnClickListener(this);

        // set the layout's onClick's
        rightLinearLayout = findViewById(R.id.rightLinearLayout);
        editTextLinearLayout = findViewById(R.id.editTextLinearLayout);
        fuckLinearLayout = findViewById(R.id.fuckLinearLayout);

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
                editTextLinearLayout.setVisibility(View.VISIBLE);
                submitValuesButton.setVisibility(View.VISIBLE);
                break;
            case R.id.submitValuesButton:
                // get the input & display it on the TextViews
                submitValueButtonActionOne();
                // populate the scrollView log with a new entry
                submitValueButtonActionTwo();
                // makes the addExerciseButton visible
                addExerciseButton.setVisibility(View.VISIBLE);
                break;
            case R.id.doneButton:
                //prepare bundle of workout data
                Bundle workoutData = new Bundle();
                workoutData.putString("WORKOUT_NAME",str_workoutName);
                workoutData.putString("WORKOUT_DATE", str_date);
                // declare intent and pass back data
                Intent backToLogHomepage = new Intent(this, logHomepage.class);
                backToLogHomepage.putExtras(workoutData);
                // set result & finish
                setResult(RESULT_OK, backToLogHomepage);
                finish();
                break;
            case R.id.addExerciseButton:
                addEditTextToLinearLayout();
                break;
        }
    } // end onClick() =============================================================================

    // start submitValueButtonActionOne() ==========================================================
    public void submitValueButtonActionOne()
    {
        str_workoutName= workoutNameEditText.getText().toString();
        rightWorkoutNameTextView.setText(str_workoutName);

        str_date = dateEditText.getText().toString();
        rightDateTextView.setText(str_date);
        dateTextView.setText(str_date);

        String str_startTime = startTimeEditText.getText().toString();
        rightStartTimeTextView.setText((str_startTime + "pm"));

        String str_duration = durationEditText.getText().toString();
        rightDurationTextView.setText((str_duration + "mins"));

        String str_bodyWeight = bodyWeightEditText.getText().toString();
        rightBodyWeightTextView.setText((str_bodyWeight + "lbs"));

        dateTextView.setVisibility(View.VISIBLE);
        editTextLinearLayout.setVisibility(View.GONE);
        rightLinearLayout.setVisibility(View.VISIBLE);
        submitValuesButton.setVisibility(View.GONE);
    } // end submitValueButtonActionOne() ==========================================================

    // start submitValueButtonActionTwo() ==========================================================
    public void submitValueButtonActionTwo()
    {
        // create the EditText & set the properties
        EditText exerciseEntry = new EditText(createLogEntry.this);
        exerciseEntry.setId(View.generateViewId());

        exerciseEntry.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        exerciseEntry.setSingleLine(false);

        LinearLayout.LayoutParams llp_edittext = new
                LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        exerciseEntry.setLayoutParams(llp_edittext);

        // add the view to the layout
        fuckLinearLayout.addView(exerciseEntry);
    } //end submitValueButtonActionTwo() ===========================================================

    // start addEditTextToLinearLayout() ===========================================================
    public void addEditTextToLinearLayout()
    {
        EditText exerciseEntry = new EditText(createLogEntry.this);
        exerciseEntry.setId(View.generateViewId());

        exerciseEntry.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        exerciseEntry.setSingleLine(false);

        LinearLayout.LayoutParams llp_edittext = new
                LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        exerciseEntry.setLayoutParams(llp_edittext);

        // add the view to the layout
        fuckLinearLayout.addView(exerciseEntry);
    } // end addEditTextToLinearLayout() ===========================================================
} // end class createLogEntry ======================================================================