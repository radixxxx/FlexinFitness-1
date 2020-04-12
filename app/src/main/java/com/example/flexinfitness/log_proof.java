package com.example.flexinfitness;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

// start log_proof class ===========================================================================
public class log_proof extends AppCompatActivity
{
    // region Views & ViewGroups
    EditText    edtxt_workoutName;
    EditText    edtxt_workoutDate;
    EditText    edtxt_workoutTime;
    EditText    edtxt_workoutDuration;
    EditText    edtxt_userWeight;

    LinearLayout scrollViewLinearLayout;
    ImageView   imgV_picture;
    // endregion


    // region DATA
    String workoutName;
    String workoutDate;
    String workoutTime;
    String workoutDuration;
    String userWeight;

    Bitmap picture;
    String currentPhotoPath;
    String[] editTextdata = new String[40];
    // endregion DATA


    // region onCreate() ===========================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_proof);

        // region connecting views
        edtxt_workoutName = findViewById(R.id.txtV_workoutName);
        edtxt_workoutDate = findViewById(R.id.txtV_workoutDate);
        edtxt_workoutTime = findViewById(R.id.txtV_workoutTime);
        edtxt_workoutDuration = findViewById(R.id.txtV_workoutDuration);
        edtxt_userWeight = findViewById(R.id.txtV_userWeight);

        scrollViewLinearLayout = findViewById(R.id.scrollViewLinearLayout);
        imgV_picture = findViewById(R.id.imgV_picture);
        // endregion

        getWorkoutData();
        createAndSetEditTexts();
        setWorkoutData();
    } // endregion onCreate() ======================================================================


    // region getWorkoutData() =====================================================================
    public void getWorkoutData()
    {
        /* in order to get the bundle of information that we passed from 'log', after clicking the newly
            created TextView, we need to declare a new intent to capture the one that we passed in
         */
        Intent workoutData;
        workoutData = getIntent();

        workoutName = workoutData.getExtras().getString("WORKOUT_NAME");
        workoutDate = workoutData.getExtras().getString("WORKOUT_DATE");
        workoutDuration  = workoutData.getExtras().getString("DURATION");
        workoutTime = workoutData.getExtras().getString("START_TIME");
        userWeight = workoutData.getExtras().getString("BODYWEIGHT");
        currentPhotoPath = workoutData.getExtras().getString("picturePath");
        editTextdata = workoutData.getStringArrayExtra("data");

        picture = BitmapFactory.decodeFile(currentPhotoPath);
    } // endregion getWorkoutData() =========================================================


    // region setWorkoutData() =====================================================================
    public void setWorkoutData()
    {
        edtxt_workoutName.setText(workoutName);
        edtxt_workoutDate.setText(workoutDate);
        edtxt_workoutTime.setText(workoutTime);
        edtxt_workoutDuration.setText(workoutDuration);
        edtxt_userWeight.setText(userWeight);

        imgV_picture.setImageBitmap(picture);
    } // endregion setWorkoutData() ================================================================


    // region createEditTexts() ====================================================================
    public void createAndSetEditTexts()
    {
        for(int index=0; (null !=(editTextdata[index])) ;++index)
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

            // add the view to the layout
            scrollViewLinearLayout.addView(exerciseEntry);

            exerciseEntry.setText(editTextdata[index]);
        }
    }
    // endregion createEditTexts() =================================================================
} // end log_proof class ===========================================================================
