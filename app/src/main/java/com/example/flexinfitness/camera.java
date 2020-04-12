package com.example.flexinfitness;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.annotation.SuppressLint;
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

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

// region camera class =============================================================================
public class camera extends AppCompatActivity implements View.OnClickListener
{
    private static final int CAPTURE_REQUEST_CODE = 5;
    private static final int CAMERA_CAPTURE_CODE = 6;
    private static final int REQUEST_CAMERA_CODE = 7;

    // region Views & ViewGroups
    String currentPhotoPath;
    Button btn_takePicture;
    Button btn_addExercise;
    ImageView imgV_picture;
    LinearLayout scrollViewLinearLayout;
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
        imgV_picture = findViewById(R.id.imgV_picture);
        btn_addExercise = findViewById(R.id.btn_addExercise);
        scrollViewLinearLayout = findViewById(R.id.scrollViewLinearLayout);
        // set onClick()
        btn_takePicture.setOnClickListener(this);
        btn_addExercise.setOnClickListener(this);
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
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)  !=  PackageManager.PERMISSION_GRANTED)
                {
                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CAMERA_CODE);
                }
                else
                    // permissions already granted, so take the picture
                    takePicture();
                break;
            case R.id.btn_addExercise:
                    addExercise();
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
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }// endregion createImageFile() ================================================================


    //region onActivityResult() ====================================================================s
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
            Bitmap picture = BitmapFactory.decodeFile(currentPhotoPath);
            imgV_picture.setImageBitmap(picture);
        }
    }// endregion onActivityResult() ===============================================================
    // endregion camera shit

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
    }// endregion addExercise() =====================================================================
} // endregion camera class ==================================================================
