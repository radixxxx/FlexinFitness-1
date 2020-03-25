package com.example.flexinfitness;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;
// start tutorial class ============================================================================
public class tutorial extends AppCompatActivity implements View.OnClickListener
{
    // declare View & ViewGroups
    Button playButton;
    Button playButton2;

    VideoView videoView;
    VideoView videoView2;

    MediaController mediaController;

    // start onCreate() ============================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        // connect View & ViewGroups
        playButton = findViewById(R.id.playPushUpTutorial);
        playButton2 = findViewById(R.id.playPushUpTutorialButton_2);

        videoView = findViewById(R.id.videoView);
        videoView2 = findViewById(R.id.videoView2);

        // setting their onClicks
        playButton.setOnClickListener(this);
        playButton2.setOnClickListener(this);
    } // end onCreate() ============================================================================

    // start onClick() =============================================================================
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.playPushUpTutorial:
                playVideo();
                break;
            case R.id.playPushUpTutorialButton_2:
                playVideo2();
                break;
        }
    } // end onClick() =============================================================================

    // start playVideo() ===========================================================================
    public void playVideo()
    {
        mediaController = new MediaController(this);

        String pathToVideo = "android.resource://com.example.flexinfitness/" + R.raw.software_engineering_video;
        Uri uri = Uri.parse(pathToVideo);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);

        videoView.start();
    } // end playVideo() ===========================================================================

    // start playVideo() ===========================================================================
    public void playVideo2()
    {
        mediaController = new MediaController(this);

        String pathToVideo = "android.resource://com.example.flexinfitness/" + R.raw.software_engineering_video;
        Uri uri = Uri.parse(pathToVideo);
        videoView2.setVideoURI(uri);
        videoView2.requestFocus();
        videoView2.setMediaController(mediaController);
        mediaController.setAnchorView(videoView2);

        videoView2.start();
    } // end playVideo() ===========================================================================
} // end tutorial class ============================================================================
