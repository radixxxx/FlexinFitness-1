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

    LinearLayout textViewLinearLayout;
    LinearLayout editTextLinearLayout;
    LinearLayout scrollViewLinearLayout;

    ScrollView scrollView;

    Button submitValuesButton;
    Button doneButton;
    Button addExerciseButton;

    // to be used to passes back when the doneButton is clicked
    String str_workoutName;
    String str_date;
    String str_startTime;
    String str_duration;
    String str_bodyweight;

    // start onCreate() ============================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_log_entry);

       connectViews();
       setOnclicks();
    } // end onCreate() ========================================================================


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
                // make TextViews invisible, make EditTexts visible
                textViewLinearLayout.setVisibility(View.GONE);         // TextViews invisible
                editTextLinearLayout.setVisibility(View.VISIBLE);   // EditTexts visible

                submitValuesButton.setVisibility(View.VISIBLE);     // 'submit Values' button visible
                break;

            case R.id.submitValuesButton:
                getInputFromEditTexts();
                setInputOnTextViews();

                // make invisible EditTexts,submitValuesButton, make visible TextViews and 'addExerciseButton'
                dateTextView.setVisibility(View.VISIBLE);
                editTextLinearLayout.setVisibility(View.GONE);
                textViewLinearLayout.setVisibility(View.VISIBLE);
                submitValuesButton.setVisibility(View.GONE);
                addExerciseButton.setVisibility(View.VISIBLE);

                // populate the scrollView log with a new entry
                addExerciseToScrollView();
                break;

            case R.id.doneButton:
                    //prepare bundle of workout data
                    Bundle workoutData = new Bundle();
                    workoutData.putString("WORKOUT_NAME", str_workoutName);
                    workoutData.putString("WORKOUT_DATE", str_date);
                    workoutData.putString("START_TIME", str_startTime);
                    workoutData.putString("DURATION", str_duration);
                    workoutData.putString("BODY_WEIGHT", str_bodyweight);

                    // declare intent and put the bundle in it
                    Intent backToLogHomepage = new Intent(this, logHomepage.class);
                    backToLogHomepage.putExtras(workoutData);

                    // set result & finish
                    setResult(RESULT_OK, backToLogHomepage);
                    finish();
                break;

            case R.id.addExerciseButton:
                addExerciseToScrollView();
                break;
        }
    } // end onClick() =============================================================================

    // start addExerciseToScrollView() ======================================================================
    public void addExerciseToScrollView()
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
        scrollViewLinearLayout.addView(exerciseEntry);
    } //end addExerciseToScrollView() ===================================================================

    // start getInputFromEditTexts() ===============================================================
    public void getInputFromEditTexts()
    {
        // gets text to strings inside our member string variables
        str_workoutName = workoutNameEditText.getText().toString();
        str_date = dateEditText.getText().toString();
        str_startTime = startTimeEditText.getText().toString();
        str_duration = durationEditText.getText().toString();
        str_bodyweight = bodyWeightEditText.getText().toString();

        // debugging purposes
        System.out.println("getInput()");
        System.out.println(str_workoutName);
        System.out.println(str_date);
        System.out.println(str_startTime);
        System.out.println(str_duration);
        System.out.println(str_bodyweight);
    } // end getInputFromEditTexts() ===========================================================

    // start setInputOnTextViews() =================================================================
    public void setInputOnTextViews()
    {
        rightWorkoutNameTextView.setText(str_workoutName);
        rightDateTextView.setText(str_date);
        rightStartTimeTextView.setText((str_startTime + " pm"));
        rightDurationTextView.setText((str_duration + " mins"));
        rightBodyWeightTextView.setText((str_bodyweight + " lbs"));

        dateTextView.setText(str_date);
    } // end setInputOnTextViews() =============================================================

    // start setOnclicks() =========================================================================
    public void  setOnclicks()
    {
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
        textViewLinearLayout = findViewById(R.id.rightLinearLayout);
        editTextLinearLayout = findViewById(R.id.editTextLinearLayout);
        scrollViewLinearLayout = findViewById(R.id.fuckLinearLayout);
    } // end connectViews() ====================================================================

    // start connectViews() ========================================================================
    public void connectViews()
    {
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
    } // end connectViews() ====================================================================
} // end class createLogEntry ======================================================================

