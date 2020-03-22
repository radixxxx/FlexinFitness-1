package com.example.flexinfitness;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

//start logDiaryHomepage class =====================================================================
public class logDiaryHomepage extends AppCompatActivity implements View.OnClickListener
{
    // Declare View & ViewGroup variables
    Button addLogButton;
    LinearLayout l_linearLayout;

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
            public void onClick(View view)
            {
                button.setText("FUCK DOES THIS WORK");
            }
        };
    } // end getOnClickDoSomething() ===============================================================


} // end logDiaryHomepage class ====================================================================