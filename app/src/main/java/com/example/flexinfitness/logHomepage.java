package com.example.flexinfitness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

//start logHomepage class =====================================================================
public class logHomepage extends AppCompatActivity implements View.OnClickListener
{
    // Declare View & ViewGroup variables
    Button addLogButton;
    LinearLayout linearLayout;
    ConstraintLayout constraintLayout;

    static int ID = 0;

    // start onCreate() ============================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_diary_homepage);

        // Connect View & ViewGroup variables to their XML id's
        constraintLayout = findViewById(R.id.constraintLayout);

        linearLayout = findViewById(R.id.linearLayout);

        addLogButton = findViewById(R.id.buttonADDLOG);
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
                createdLogEntry.setOnClickListener(onEntryClick(createdLogEntry));
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
        linearLayout.addView(createdLogEntry);
        return createdLogEntry;
    } // end createLogEntry() ======================================================================

    // start getOnClickDoSomething() ===============================================================
    View.OnClickListener onEntryClick(final Button button) {
        return new View.OnClickListener() {
            public void onClick(View view) {

            }
        };
    } // end getOnClickDoSomething() ===============================================================
} // end logDiaryHomepage class ====================================================================