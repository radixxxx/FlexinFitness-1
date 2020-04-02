package com.example.flexinfitness;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

//start logHomepage class ==========================================================================
public class log extends AppCompatActivity implements View.OnClickListener
{
    // Declare View & ViewGroup variables
    Button addLogButton;
    Button homeButton;

    LinearLayout linearLayout;

    ConstraintLayout constraintLayout;

    public static final int REQUEST_CODE = 1;

    // start onCreate() ============================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        // Connect View & ViewGroup variables to their XML id's
        constraintLayout = findViewById(R.id.constraintLayout);
        linearLayout = findViewById(R.id.linearLayout);
        addLogButton = findViewById(R.id.addLogButton);
        homeButton = findViewById(R.id.homeButton);

        // setting the onClick's for the buttons
        addLogButton.setOnClickListener(this);
        homeButton.setOnClickListener(this);
    } // end onCreate() ============================================================================

    // start onClick() =============================================================================
    @Override
    public void onClick(View view)
    {
        switch(view.getId())
        {
            // create a log entry
            case R.id.addLogButton:
                Intent createLogEntry = new Intent(getApplicationContext(), createLogEntry.class);
                startActivityForResult(createLogEntry, REQUEST_CODE);
                break;
            case R.id.homeButton:
                Intent goBackToDashboard = new Intent(getApplicationContext(), DashBoard.class);
                startActivity(goBackToDashboard);
                break;
        } // end switch(view.getId()
    } // end onClick() =============================================================================


    // start onActivityResult() ====================================================================
    // input: N/A
    // output: a bundle of information from the 'create log' functionality used to populate the text
    //         the log
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if( REQUEST_CODE == requestCode && resultCode == RESULT_OK)
        {
            // extract the stored data from the bundle
            String str_workoutName = data.getExtras().getString("WORKOUT_NAME");
            String str_workoutDate = data.getExtras().getString("WORKOUT_DATE");
            String str_startTime = data.getExtras().getString("START_TIME");
            String str_duration = data.getExtras().getString("DURATION");
            String str_bodyweight = data.getExtras().getString("BODY");

            if( null !=  str_workoutDate && null != str_workoutName)
            {
                addLog(str_workoutName.toUpperCase(), str_workoutDate.toUpperCase());
            }
            else
            {
                addLog(str_workoutName, str_workoutDate);
            }
        }
    } // end onActivityResult() ====================================================================

    // start addLog() ==============================================================================
    public void addLog(final String str_workoutName, final String str_workoutDate)
    {
        // create log entry & set properties
        TextView logEntry = new TextView(log.this);
        logEntry.setId(View.generateViewId());
        logEntry.setText(str_workoutName + "      " +str_workoutDate);
        logEntry.setTextColor(Color.BLACK);
        logEntry.setTextSize(25);
        logEntry.setBackgroundColor(Color.LTGRAY);


        LinearLayout.LayoutParams llp_textView = new
                LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
        logEntry.setLayoutParams(llp_textView);

        // add log entry to linear layout
        linearLayout.addView(logEntry);

        // set it's onClick
        logEntry.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // do to single log entry and populate it with the information we got earlier
                Bundle workoutData = new Bundle();
                workoutData.putString("WORKOUT_NAME",str_workoutName);
                workoutData.putString("WORKOUT_DATE", str_workoutDate);
                Intent goToLogProof = new Intent(getApplicationContext(), log_proof.class);
                goToLogProof.putExtras(workoutData);
                startActivityForResult(goToLogProof, REQUEST_CODE);
            }
        });
    } // end addLog() ==============================================================================
} // end logDiaryHomepage class ====================================================================
