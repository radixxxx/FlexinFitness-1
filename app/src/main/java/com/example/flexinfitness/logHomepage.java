package com.example.flexinfitness;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

// region logHomepage class ========================================================================
public class logHomepage extends AppCompatActivity implements View.OnClickListener
{
    // region Views & ViewGroups
    Button addLogButton;
    Button homeButton;
    LinearLayout linearLayout;
    ConstraintLayout constraintLayout;
    // endregion

    // region DATA
    String str_workoutName;
    String str_date;
    String str_startTime;
    String str_duration;
    String str_bodyweight;
    String currentPhotoPath;
    Bitmap picture;
    byte[] byteArray;
    String[] editTextData = new String[40];
    // endregion

    public static final int REQUEST_CODE = 1;

    // region onCreate() ===========================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        // region connect & set onClicks()
        constraintLayout = findViewById(R.id.constraintLayout);
        linearLayout = findViewById(R.id.linearLayout);
        addLogButton = findViewById(R.id.addLogButton);
        homeButton = findViewById(R.id.homeButton);

        addLogButton.setOnClickListener(this);
        homeButton.setOnClickListener(this);
        // endregion
    } // endregion onCreate() ======================================================================


    // region onClick() ============================================================================
    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            // create a log entry
            case R.id.addLogButton:
                Intent createLogEntry = new Intent(this, camera.class); // intent is to switch to 'camera'
                startActivityForResult(createLogEntry, REQUEST_CODE);
                break;
            // go back to dashboard
            case R.id.homeButton:
                Intent goBackToDashboard = new Intent(getApplicationContext(), DashBoard.class);
                startActivity(goBackToDashboard);
                break;
        }
    } // endregion onClick() =======================================================================


    // region onActivityResult() ===================================================================
    // program flow comes back from 'camera' class just after packing up the bundle
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST_CODE == requestCode && resultCode == RESULT_OK)
        {
            if(null != data)
            {
                // region extracting data from bundle
                str_workoutName = data.getExtras().getString("WORKOUT_NAME");
                str_date = data.getExtras().getString("WORKOUT_DATE");
                str_startTime = data.getExtras().getString("START_TIME");
                str_duration = data.getExtras().getString("DURATION");
                str_bodyweight = data.getExtras().getString("BODY_WEIGHT");
                currentPhotoPath = data.getExtras().getString("picturePath");
                editTextData = data.getExtras().getStringArray("data");

                str_workoutName = str_workoutName.toUpperCase();
            }
            if (null != str_date && null != str_workoutName && null != str_startTime && null != str_duration && null != str_bodyweight)
            {
                addLog(str_workoutName, str_date, str_startTime, str_duration, str_bodyweight);
            } else
            {
                System.out.println("else statement");
            }
        }
    } // endregion onActivityResult() ==============================================================


    // region addLog() =============================================================================
    public void addLog(final String str_workoutName, final String str_workoutDate, final String str_startTime, final String str_duration, final String str_bodyweight)
    {
        // create a TextView and add it to the layout
        TextView logEntry = createView();
        linearLayout.addView(logEntry);

        // sets the onClickListener to the newly created log entry within 'logHomepage'
        logEntry.setOnClickListener(new View.OnClickListener()
        {
            /* onClick for the new TextView that was defined, assigned workout values, and placed into the layout programmatically
                upon clicking the TextView, this onClick should create another bundle of workout data and pass it to the
                'log_proof' activity
            */
            @Override
            public void onClick(View v)
            {
                Bundle workoutData = new Bundle();
                workoutData.putString("WORKOUT_NAME", str_workoutName);
                workoutData.putString("WORKOUT_DATE", str_workoutDate);
                workoutData.putString("START_TIME", str_startTime);
                workoutData.putString("DURATION", str_duration);
                workoutData.putString("BODYWEIGHT", str_bodyweight);
                workoutData.putString("picturePath", currentPhotoPath);


                Intent goToLogProof = new Intent(logHomepage.this, log_proof.class);
                goToLogProof.putExtra("data", editTextData);
                goToLogProof.putExtras(workoutData);
                startActivity(goToLogProof);
            }
        });
    } // endregion addLog() ========================================================================


    // region createView() =========================================================================
    public TextView createView()
    {
        TextView logEntry = new TextView(logHomepage.this);        // creating the log entry
        logEntry.setId(View.generateViewId());
        logEntry.setText(str_workoutName + "      " + str_date);
        logEntry.setTextColor(Color.BLACK);
        logEntry.setTextSize(35);
        logEntry.setBackgroundColor(Color.LTGRAY);


        LinearLayout.LayoutParams llp_textView = new
                LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        logEntry.setLayoutParams(llp_textView);

        return logEntry;
    } // endregion createView() ====================================================================
} // endregion logDiaryHomepage class ====================================================================
