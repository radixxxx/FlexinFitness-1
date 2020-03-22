package com.example.flexinfitness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

//start logDiaryHomepage class =====================================================================
public class logDiaryHomepage extends AppCompatActivity implements View.OnClickListener
{
    // Declare View & ViewGroup variables
    Button addLogButton;
    LinearLayout l_linearLayout;
    ConstraintLayout c_constraintLayout;


    // counter for entries(temprarily here until I implement a view that asks them for more info
    // for the entry)
    static int ID = 0;

    // start onCreate() ============================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_diary_homepage);

        // Connect View & ViewGroup variables to their XML id's
        c_constraintLayout = (ConstraintLayout) findViewById(R.id.constraintLayout);
        l_linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        addLogButton = (Button) findViewById(R.id.buttonADDLOG);
        addLogButton.setOnClickListener(this);

    } // end onCreate() ============================================================================

    // start onClick() =============================================================================
    @Override
    public void onClick(View view)
    {
        switch(view.getId())
        {
            // Adds a new entry to the scroll view
            case R.id.buttonADDLOG:
                // Create log entry
                Button createdLogEntry = createLogEntry();
                // Set a click listener to it
                createdLogEntry.setOnClickListener(getOnClickDoSomething(createdLogEntry));
                break;
        } // end switch(view.getId()

    } // end onClick() =============================================================================

    // start createLogEntry() ======================================================================
    public Button createLogEntry()
    {
        ID += 1;
        int firstEntry = ID;
        String str_ID = Integer.toString(ID);
        // declare and initialize log entry variable
        Button createdLogEntry = new Button(this);
        createdLogEntry.setText(("Entry " + str_ID));
        createdLogEntry.setId(firstEntry);
        l_linearLayout.addView(createdLogEntry);
        return createdLogEntry;
    } // end createLogEntry() ======================================================================

    // start getOnClickDoSomething() ===============================================================
    View.OnClickListener getOnClickDoSomething(final Button button)
    {
        return new View.OnClickListener()
        {
            // there will be an edittext that appears when a log entry is pressed
            public void onClick(View view)
            {
                // declare edit text
                EditText logEntryName = new EditText(logDiaryHomepage.this);

                // set properties of edit text
                int ID = 0;
                logEntryName.setId(ID);
                logEntryName.setHint("ENTER WORKOUT NAME");
                ConstraintLayout.LayoutParams clp_logEntryName = new ConstraintLayout.LayoutParams
                        (ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                logEntryName.setLayoutParams(clp_logEntryName);
                c_constraintLayout.addView(logEntryName);

                //logEntryName.setHint("ENTER WORKOUT NAME");
               // logEntryName.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT));

                // add constraints to the log entry name so that it's just in the middle
                // c_constraintLayout is the root ViewGroup so I'm centering it in there
                ConstraintSet constraintSet = new ConstraintSet();
                constraintSet.clone(c_constraintLayout);

                constraintSet.connect(logEntryName.getId(), ConstraintSet.LEFT, c_constraintLayout.getId(), ConstraintSet.LEFT);
                constraintSet.connect(logEntryName.getId(), ConstraintSet.RIGHT, c_constraintLayout.getId(), ConstraintSet.RIGHT);
                constraintSet.connect(logEntryName.getId(), ConstraintSet.TOP, c_constraintLayout.getId(), ConstraintSet.TOP);
                constraintSet.connect(logEntryName.getId(), ConstraintSet.BOTTOM, c_constraintLayout.getId(), ConstraintSet.BOTTOM);

                constraintSet.applyTo(c_constraintLayout);
            }
        };
    } // end getOnClickDoSomething() ===============================================================


} // end logDiaryHomepage class ====================================================================