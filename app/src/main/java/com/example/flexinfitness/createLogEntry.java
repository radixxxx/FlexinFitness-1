package com.example.flexinfitness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

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

        // set the 'TextViews' onClick's
        rightWorkoutNameTextView.setOnClickListener(this);
        rightDateTextView.setOnClickListener(this);
        rightStartTimeTextView.setOnClickListener(this);
        rightDurationTextView.setOnClickListener(this);
        rightBodyWeightTextView.setOnClickListener(this);

    } // end onCreate() ============================================================================

    // start onClick() =============================================================================
    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.rightWorkoutNameTextView:
                changeVisibility(rightWorkoutNameTextView, workoutNameEditText);
                String str_workoutName = getEditText(workoutNameEditText);
                rightWorkoutNameTextView.setText(str_workoutName);
                changeVisibility(rightWorkoutNameTextView, workoutNameEditText);
                break;
        }
    } // end onClick() =============================================================================

    // start changeVisibility() ====================================================================
    public void changeVisibility(TextView txtV, EditText edTxt)
    {
        if( txtV.getVisibility() == View.VISIBLE)
        {
            txtV.setVisibility(View.GONE);
            edTxt.setVisibility(View.VISIBLE);
        }
        else
        {
            txtV.setVisibility(View.VISIBLE);
            edTxt.setVisibility(View.GONE);
        }
    } // end changeVisibility() ====================================================================

    // start getEditText() =========================================================================
    public String getEditText(EditText edTxt) { return edTxt.getText().toString(); }
    // end getEditText() ===========================================================================
} // end class createLogEntry ======================================================================
