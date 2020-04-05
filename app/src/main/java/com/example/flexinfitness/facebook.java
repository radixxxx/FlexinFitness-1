package com.example.flexinfitness;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
// region facebook class ===========================================================================
public class facebook extends AppCompatActivity implements View.OnClickListener
{
    // Views & ViewGroups
    Button btnShareLink;
    Button btnSharePhoto;
    Button btnShareVideo;

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

    int REQUEST_VIDEO_CODE = 1000;

    // region OnCreate() ==============================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook);

        FacebookSdk.sdkInitialize(facebook.this);

        //connecting views
        btnShareLink = findViewById(R.id.btnShareLink);
        btnSharePhoto = findViewById(R.id.btnSharePhoto);
        btnShareVideo = findViewById(R.id.btnShareVideo);

        // setting onclicks
        btnSharePhoto.setOnClickListener(this);
        btnShareLink.setOnClickListener(this);
        btnShareVideo.setOnClickListener(this);

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


            // region btnShareVideo
            case R.id.btnShareVideo:
                Intent idk =  new Intent();

                idk.setType("photo/*");
                idk.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(idk, "Select Video"), REQUEST_VIDEO_CODE);
                break;
            // endregion btnShareVideo
            default:
                System.out.println("FUCK YOU FRANCO");
                break;

        }
    }// endregion onClick() ========================================================================

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

} // endregion facebook class=======================================================================
