package com.example.flexinfitness;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.model.ShareVideo;
import com.facebook.share.model.ShareVideoContent;
import com.facebook.share.widget.ShareDialog;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.util.ArrayList;

// region facebook class ===========================================================================
public class facebook extends AppCompatActivity implements View.OnClickListener
{
    // Views & ViewGroups
    Button btnShareLink;
    Button btnSharePhoto;
    Button btnShareVideo;

    // region image from camera
    Button btnTakePhoto;
    private static final int CAMERA_TAKE_REQUEST = 200;
    private final static int ALL_PERMISSIONS_RESULT = 101;
    private ImageView imageView;
    File file;
    Uri uri;
    private Context context;
    private Activity activity;
    ArrayList<String> permissions = new ArrayList<>();
    ArrayList<String> permissionsToRequest;
    ArrayList<String> permissionsRejected = new ArrayList<>();
    // endregion image from camera

    CallbackManager callbackManager;

    ShareDialog shareDialog;

    // region Target class definition
    Target target = new Target()
    {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from)
        {
            SharePhoto sharePhoto = new SharePhoto.Builder()
                    .setBitmap(bitmap)
                    .build();

            SharePhotoContent content = new SharePhotoContent.Builder()
                    .addPhoto(sharePhoto)
                    .build();
            shareDialog.show(content);
        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable)
        {

        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable)
        {

        }
    }; // endregion Target class definition

    //int REQUEST_VIDEO_CODE = 1000;

    // region OnCreate() ==============================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook);

        FacebookSdk.sdkInitialize(facebook.this);

        // region Connection Views & Setting onClicks
        btnShareLink = findViewById(R.id.btnShareLink);
        btnSharePhoto = findViewById(R.id.btnSharePhoto);
        btnShareVideo = findViewById(R.id.btnShareVideo);
        btnTakePhoto = findViewById(R.id.btnTakePhoto);

        btnSharePhoto.setOnClickListener(this);
        btnShareLink.setOnClickListener(this);
        btnShareVideo.setOnClickListener(this);
        // endregion Connection Views & Setting onClicks

        // region image from camera
        imageView = findViewById(R.id.ImageView);
        context = this;
        activity = facebook.this;
        permissions.add(Manifest.permission.CAMERA);
        permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        permissionsToRequest = findUnAskedPermissions(permissions);
        // endregion image from camera

        // initialize FB
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);
    }// endregion OnCreate() ==============================================================================


    //region onClick() =============================================================================
    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            // region btnShareLink
            case R.id.btnShareLink:
                shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>()
                {
                    @Override
                    public void onSuccess(Sharer.Result result)
                    {
                        Toast.makeText(facebook.this, "Share Successfull!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel()
                    {
                        Toast.makeText(facebook.this, "Share !(successfull)", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(FacebookException error)
                    {
                        Toast.makeText(facebook.this, error.getMessage(), Toast.LENGTH_SHORT).show();;
                    }
                });

                ShareLinkContent linkContent = new ShareLinkContent.Builder()
                        .setQuote("Test Link")
                        .setContentUrl(Uri.parse("https://youtube.com"))
                        .build();
                if(ShareDialog.canShow(ShareLinkContent.class))
                {
                    shareDialog.show(linkContent);
                }
            break;
            // endregion btnShareLink

            /*
            // region btnSharePhoto
            case R.id.btnSharePhoto:
                // region MY ATTEMPT
                Bitmap image = ;
                SharePhoto photo = new SharePhoto.Builder()
                        .setBitmap(image)
                        .build();
                SharePhotoContent content = new SharePhotoContent.Builder()
                        .addPhoto(photo)
                        .build();
                // endregion MY ATTEMPT

                // region YOUTUBE
                // create callback
                shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>()
                        {
                            @Override
                            public void onSuccess(Sharer.Result result)
                            {
                                Toast.makeText(facebook.this, "Share Successfull!", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCancel()
                            {
                                Toast.makeText(facebook.this, "Share !(successfull)", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(FacebookException error)
                            {
                                Toast.makeText(facebook.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                // fetching photo from link and converting to bitmap
                Picasso.with(this).load("https://www.lostlandsfestival.com/wp-content/uploads/2019/10/LostLands2019trex.jpg")
                        .into(target);
                // endregion YOUTUBE

            break;
            // endregion btnSharePhoto
            */
            /*
            // region btnShareVideo
            case R.id.btnShareVideo:
                Intent idk =  new Intent();

                idk.setType("photo/*");
                idk.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(idk, "Select Video"), REQUEST_VIDEO_CODE);
                break;
            // endregion btnShareVideo
             */
            default:
                System.out.println("FUCK YOU FRANCO");
                break;

        }
    }// endregion onClick() ========================================================================

    /*
    // region onActivityResult() ===================================================================
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_VIDEO_CODE)
        {
            Uri selectedVideo = data.getData();

            ShareVideo video = new ShareVideo.Builder()
                    .setLocalUrl(selectedVideo)
                    .build();

            ShareVideoContent   videoContent = new ShareVideoContent.Builder()
                    .setContentTitle("TESTING VIDEO")
                    .setContentDescription("TEST VIDEO")
                    .setVideo(video)
                    .build();

            if(ShareDialog.canShow(ShareVideoContent.class))
            {
                shareDialog.show(videoContent);
            }
        }
    }

    // endregion onActivityResult() ================================================================
     */

    // region image from camera
    // region take()
    @TargetApi(Build.VERSION_CODES.M)
    public void take(View v) {
        if(checkCameraExists()) {
            if (permissionsToRequest.size() > 0) {
                requestPermissions(permissionsToRequest.toArray(new String[permissionsToRequest.size()]),
                        ALL_PERMISSIONS_RESULT);
            } else {
                Toast.makeText(context,"Permissions already granted.", Toast.LENGTH_LONG).show();
                launchCamera();
            }
        } else {
            Toast.makeText(activity, "Camera not available.", Toast.LENGTH_SHORT).show();
        }
    }
    // endregion take()

    public boolean checkCameraExists() {
        return activity.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    // region launchCamera()
    private void launchCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

        file = new File(Environment.getExternalStorageDirectory(), String.valueOf(System.currentTimeMillis()) + ".jpg");
        uri = FileProvider.getUriForFile(activity, activity.getApplicationContext().getPackageName() + ".provider", file);
        intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, uri);

        startActivityForResult(intent, CAMERA_TAKE_REQUEST);
    }
    // endregion launchCamera()

    // region onActivityResult()
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case CAMERA_TAKE_REQUEST:
                imageView.setImageURI(Uri.parse(file.toURI().toString()));
                break;
        }
    }
    // endregion onActivityResult()

    // region findUnaskedPermissions
    private ArrayList findUnAskedPermissions(ArrayList<String> wanted) {
        ArrayList result = new ArrayList();

        for (String perm : wanted) {
            if (!hasPermission(perm)) {
                result.add(perm);
            }
        }

        return result;
    }
    // endregion findUnaskedPermissions

    private boolean hasPermission(String permission) {
        if (canAskPermission()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return (checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED);
            }
        }
        return true;
    }

    private boolean canAskPermission() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    // region onRequestPermissionResult
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case ALL_PERMISSIONS_RESULT:
                for (String perms : permissionsToRequest) {
                    if (!hasPermission(perms)) {
                        permissionsRejected.add(perms);
                    }
                }

                if (permissionsRejected.size() > 0) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (shouldShowRequestPermissionRationale(permissionsRejected.get(0))) {
                            String msg = "These permissions are mandatory for the application. Please allow access.";
                            showMessageOKCancel(msg,
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermissions(permissionsRejected.toArray(
                                                        new String[permissionsRejected.size()]), ALL_PERMISSIONS_RESULT);
                                            }
                                        }
                                    });
                            return;
                        }
                    }
                } else {
                    Toast.makeText(context, "Permissions garanted.", Toast.LENGTH_LONG).show();
                    launchCamera();
                }
                break;
        }
    }
    // endregion onRequestPermissionResult

    // region showMessageOKcancel
    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(activity)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }
    // endregion showMessageOKcancel
    // endregion image from camera

} // endregion facebook class=======================================================================
