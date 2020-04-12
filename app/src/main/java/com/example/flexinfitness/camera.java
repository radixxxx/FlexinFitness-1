package com.example.flexinfitness;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.*;

// region camera class =============================================================================
public class camera extends AppCompatActivity implements View.OnClickListener
{
    private static final int CAPTURE_REQUEST_CODE = 5;
    private static final int CAMERA_CAPTURE_CODE = 6;
    private static final int REQUEST_CAMERA_CODE = 7;

    // region DATA
    Bitmap  currentPicture;
    String  currentPhotoPath;
    String  name = "example";
    String  date = "example";
    String  time = "example";
    String  duration = "example";
    String  weight = "example";
    Vector<String> data = new Vector<>();
    // endregion DATA


    // region Views & ViewGroups
    Button btn_takePicture;
    Button btn_addExercise;
    Button btn_save;
    ImageView imgV_picture;
    LinearLayout scrollViewLinearLayout;

    EditText    edtxt_name;
    EditText    edtxt_date;
    EditText    edtxt_time;
    EditText    edtxt_duration;
    EditText    edtxt_weight;
    // endregion


    // region onCreate() ===========================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        // region connect & set onClicks()
        // connect views
        btn_takePicture = findViewById(R.id.btn_takePicture);
        btn_save = findViewById(R.id.btn_save);
        imgV_picture = findViewById(R.id.imgV_picture);
        btn_addExercise = findViewById(R.id.btn_addExercise);
        scrollViewLinearLayout = findViewById(R.id.scrollViewLinearLayout);

        edtxt_name = findViewById(R.id.txtV_workoutName);
        edtxt_date = findViewById(R.id.txtV_workoutDate);
        edtxt_time = findViewById(R.id.txtV_workoutTime);
        edtxt_duration = findViewById(R.id.txtV_workoutDuration);
        edtxt_weight = findViewById(R.id.txtV_userWeight);
        // set onClick()
        btn_takePicture.setOnClickListener(this);
        btn_addExercise.setOnClickListener(this);
        btn_save.setOnClickListener(this);
        // endregion connect & set onClicks()
    } // endregion onCreate() ======================================================================


    // region onClick() ============================================================================
    // When the 'Take Picture' button is pressed, first check if we have permissions to
    // access the camera. If not, then request permission, else take the picture.
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.btn_takePicture:
                // if we don't have permission to write to storage, request it
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                {
                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CAMERA_CODE);
                } else
                    // permissions already granted, so take the picture
                    takePicture();
                break;
            case R.id.btn_addExercise:
                addExercise();
                break;
            case R.id.btn_save:
                getWorkoutData();
                getEditTextData();

                //prepare bundle of workout data
                Bundle workoutData = new Bundle();
                workoutData.putString("WORKOUT_NAME", name);
                workoutData.putString("WORKOUT_DATE", date);
                workoutData.putString("START_TIME", time);
                workoutData.putString("DURATION", duration);
                workoutData.putString("BODY_WEIGHT", weight);
                workoutData.putString("picturePath", currentPhotoPath);

                // converting the vector into an String[] - string array so that I can pass all of
                // of the information back to the activity
                String[] data_v2 = new String[40];
                for (int index=0;index < data.size(); ++index)
                {
                    data_v2[index] = data.get(index);
                }

                // declare intent and put the bundle in it
                Intent backToLogHomepage = new Intent(getApplicationContext(), logHomepage.class);
                backToLogHomepage.putExtra("data", data_v2);
                backToLogHomepage.putExtras(workoutData);

                // region Toast
                Context context = this;
                CharSequence text = "Just before I'm about to extract the picture from the bundle of workout data";
                int toastDuration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, toastDuration);
                toast.show();

                // set result & finish
                setResult(RESULT_OK, backToLogHomepage);
                finish();
                break;
            default:
                break;
        }
    }// endregion onClick() ========================================================================


    // region camera shit
    // region onRequestPermissionsResult() =========================================================
    // Execution begins here only after returning from a call to 'requestPermissions()' in the onClick()
    // if execution continues here, then we know that we did not have the permissions needed to take
    // the picture the first time so we had to request them. now we reattempt to take the picture
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == CAPTURE_REQUEST_CODE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)   // permissions granted
                takePicture();
            else {
                // since they weren't granted we'll request them again
                ActivityCompat.requestPermissions(camera.this, new String[]{android.Manifest.permission.CAMERA,
                                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                android.Manifest.permission.READ_EXTERNAL_STORAGE},
                        CAPTURE_REQUEST_CODE);
                Toast.makeText(camera.this, "Permission Not Granted", Toast.LENGTH_LONG).show();
            }
        }
    }// endregion onRequestPermissionsResult() =====================================================


    // region takePicture()=========================================================================
    private void takePicture() {
        // create a new intent that will go to another activity to actually take the picture
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Creates the file for where the picture should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // error creating the file
            }
            // continue only if the file was created
            if (photoFile != null) {
                Uri pictureUri = FileProvider.getUriForFile(camera.this,
                        "com.example.flexinfitness.fileprovider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, pictureUri);

                // take the picture, finally
                startActivityForResult(takePictureIntent, CAMERA_CAPTURE_CODE);
            }
        }
    }// endregion  takePicture() ===================================================================


    // region createImageFile() ====================================================================
    private File createImageFile() throws IOException {
        // Create an image file name
        @SuppressLint("SimpleDateFormat") String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".png",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }// endregion createImageFile() ================================================================


    //region onActivityResult() ====================================================================
    // Once the image has been captured and accepted, then program execution resumes here
    // This is where we finally can do whatever it is that we want to with the picture since we will
    // have it as a Bitmap variable.
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_CAPTURE_CODE && resultCode == RESULT_OK)
        {
            // this portion is where you do what you want with the image you just took
            currentPicture = BitmapFactory.decodeFile(currentPhotoPath);
            imgV_picture.setImageBitmap(currentPicture);
        }
    }// endregion onActivityResult() ===============================================================
    // endregion camera shit


    // region getWorkoutData() =====================================================================
    public void getWorkoutData()
    {
        /*
        // region Toast
        Context context = this;
        CharSequence text = "getWorkoutData()";
        int toastDuration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, toastDuration);
        toast.show();
        // endregion Toast
         */

        name = edtxt_name.getText().toString();
        date = edtxt_date.getText().toString();
        time = edtxt_time.getText().toString();
        duration = edtxt_duration.getText().toString();
        weight = edtxt_weight.getText().toString();
    }// endregion getWorkoutData() =================================================================


    // region getEditTextData() ====================================================================
    public void getEditTextData()
    {   // index through the linearLayout
        for(int index=0;index < scrollViewLinearLayout.getChildCount();++index)
        {   // if the ViewGroup not empty
            if(null != scrollViewLinearLayout.getChildAt(index))
            {   // then, get the first child
                View tempView = scrollViewLinearLayout.getChildAt(index);
                if(tempView instanceof EditText)
                {
                    EditText edtxt_temp = (EditText) tempView;
                    if(edtxt_temp != null)
                    {
                        data.add("adding this so that the vector isn't empty when I try to assign the first element in the next line something. It would crash otherwise.");
                        data.set(index, edtxt_temp.getText().toString());
                    }
                }
            }
        }
    }// endregion getEditTextData() ================================================================


    // region addExercise() ========================================================================
    private void addExercise()
    {
        // create the EditText & set the properties
        EditText exerciseEntry = new EditText(camera.this);
        exerciseEntry.setId(View.generateViewId());
        exerciseEntry.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        exerciseEntry.setSingleLine(false);
        LinearLayout.LayoutParams llp_edittext = new
                LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        exerciseEntry.setLayoutParams(llp_edittext);

        // add the view to the layout
        scrollViewLinearLayout.addView(exerciseEntry);
    }// endregion addExercise() ====================================================================
} // endregion camera class ==================================================================
