package com.example.flexinfitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Vector;

// start log_proof class ===========================================================================
public class log_proof extends AppCompatActivity
{
    // declaring Views & ViewGroups
    TextView workoutName_txtV;
    TextView date_txtV;
    TextView startTime_txtV;
    TextView duration_txtV;
    TextView bodyweight_txtV;
    TextView heading_txtV;

    String str_workoutName;
    String str_workoutDate;
    String str_duration;
    String str_startTime;
    String str_bodyWeight;

    LinearLayout scrollViewLinearLayout;

    ArrayList<String>   list = new ArrayList<>();
    Vector<EditText>    exercisesEditTextVector = new Vector<>();

    // start onCreate() ============================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_proof);

        connectViews();
        getWorkoutData();
        setWorkoutData();
        createTextViews();
        populateExercises();
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

        list = workoutData.getExtras().getStringArrayList("list");
        System.out.println(list);
    } // end getWorkoutInformation() ===============================================================

    // start setWorkoutData() ======================================================================
    public void setWorkoutData()
    {
        workoutName_txtV.setText(str_workoutName);
        date_txtV.setText(str_workoutDate);
        duration_txtV.setText(str_duration);
        startTime_txtV.setText(str_startTime);
        bodyweight_txtV.setText(str_bodyWeight);
        heading_txtV.setText(str_workoutName);


    } // end setWorkoutData() ==================================================================


    /// THIS FUCNTION IS NEW========================================================================
    // start populateExercises() ===================================================================
    public void createTextViews()
    {
        for(int index=0;index< list.size();++index)
        {
            // create the EditText & set the properties
            EditText exerciseEntry = new EditText(log_proof.this);
            exerciseEntry.setId(View.generateViewId());
            exerciseEntry.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
            exerciseEntry.setSingleLine(false);
            LinearLayout.LayoutParams llp_edittext = new
                    LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            exerciseEntry.setLayoutParams(llp_edittext);

            scrollViewLinearLayout.addView(exerciseEntry);
            exercisesEditTextVector.add(exerciseEntry);
        }
    } // end populateExercises() ===================================================================


    // start populateExercises() ===================================================================
    public void populateExercises()
    {

    } // end populateExercises() ===================================================================


    // start connectViews() ========================================================================
    public void connectViews()
    {
        // connecting View & ViewGroups
        workoutName_txtV = findViewById(R.id.rightWorkoutNameTextView);
        date_txtV = findViewById(R.id.rightDateTextView);
        startTime_txtV = findViewById(R.id.rightStartTimeTextView);
        duration_txtV = findViewById(R.id.rightDurationTextView);
        bodyweight_txtV = findViewById(R.id.rightBodyWeightTextView);
        heading_txtV = findViewById(R.id.heading_txtV);
        scrollViewLinearLayout = findViewById(R.id.fuckLinearLayout);
    } // end connectViews() ========================================================================
} // end log_proof class
