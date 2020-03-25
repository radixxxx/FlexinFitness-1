package com.example.flexinfitness;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Vector;

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

        // connect View & ViewGroups to their onClicks
        doneButton.setOnClickListener(this);
        submitButton.setOnClickListener(this);

        workoutNameEditText.setOnClickListener(this);
        dateToPerformEditText.setOnClickListener(this);
        timeToPerformEditText.setOnClickListener(this);
        restBetweenSetsEditText.setOnClickListener(this);
    } // end onCreate() ============================================================================

    // start onClick() =============================================================================
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.submitButton:
                Vector<String> inputs = getInputs();
                addView(inputs);
                break;
        }

    } // end onClick() =============================================================================

    // start getInputs() ===========================================================================
    public Vector<String> getInputs()
    {
        // get inputs to Strings
        String str_workoutName = workoutNameEditText.getText().toString();
        String str_dateToPerform = dateToPerformEditText.getText().toString();
        String str_timeToPerform = timeToPerformEditText.getText().toString();
        String str_restBetweenSets = restBetweenSetsEditText.getText().toString();

        // place in vector of strings
        Vector<String> inputs = new Vector<>();
        inputs.add(str_workoutName.toUpperCase());
        inputs.add(str_dateToPerform.toUpperCase());
        inputs.add(str_timeToPerform.toUpperCase());
        inputs.add(str_restBetweenSets.toUpperCase());

        return inputs;
    } // end getInputs() ===========================================================================

    // start addView() =============================================================================
    @SuppressLint("SetTextI18n")
    public void addView(Vector<String> inputs)
    {
        // create workoutEntry
        TextView workoutEntry = new TextView(workoutPlanner.this);
        workoutEntry.setId(View.generateViewId());

        LinearLayout.LayoutParams llp_edittext = new
                LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        workoutEntry.setLayoutParams(llp_edittext);

        // text properties
        workoutEntry.setTextColor(Color.BLACK);
        workoutEntry.setTextSize(20);
        workoutEntry.setGravity(Gravity.CENTER);
        workoutEntry.setBackgroundColor(Color.LTGRAY);

        // set one line of text
        workoutEntry.setText(inputs.get(0) + "     " + inputs.get(1) + "\n" + "TIME " +
                            inputs.get(2) +"   REST " + inputs.get(3));
        // add the view to the layout
        linearLayout.addView(workoutEntry);
    } // end addView() =============================================================================
} // end workoutPlanner class ======================================================================
