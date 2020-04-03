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
    Button playPushUps, playCrunches, playSquats, playLunges;

    VideoView pushups_video, crunches_video, squats_video, lunges_video;

    MediaController mediaController;

    // start onCreate() ============================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        // connect View & ViewGroups
        playPushUps = findViewById(R.id.pushUpsButton);
        playCrunches = findViewById(R.id.crunchesButton);
        playSquats = findViewById(R.id.squatsButton);
        playLunges = findViewById(R.id.lungesButton);

        pushups_video = findViewById(R.id.webView1);
        crunches_video = findViewById(R.id.webView2);
        squats_video = findViewById(R.id.webView3);
        lunges_video = findViewById(R.id.webView4);

        // setting their onClicks
        playPushUps.setOnClickListener(this);
        playCrunches.setOnClickListener(this);
        playSquats.setOnClickListener(this);
        playLunges.setOnClickListener(this);

    } // end onCreate() ============================================================================

    // start onClick() =============================================================================
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.pushUpsButton:
                playVideo1();
                break;
            case R.id.crunchesButton:
                playVideo2();
                break;
            case R.id.squatsButton:
                playVideo3();
                break;
            case R.id.lungesButton:
                playVideo4();
                break;

        }
    } // end onClick() =============================================================================

    // start playVideo() ===========================================================================
    public void playVideo1()
    {
        mediaController = new MediaController(this);

        String pathToVideo = "android.resource://com.example.flexinfitness/" + R.raw.pushups_video_final;
        Uri uri = Uri.parse(pathToVideo);
        pushups_video.setVideoURI(uri);
        pushups_video.requestFocus();
        pushups_video.setMediaController(mediaController);
        mediaController.setAnchorView(pushups_video);

        pushups_video.start();
    } // end playVideo() ===========================================================================

    // start playVideo() ===========================================================================
    public void playVideo2()
    {
        mediaController = new MediaController(this);

        String pathToVideo = "android.resource://com.example.flexinfitness/" + R.raw.crunches_video_final;
        Uri uri = Uri.parse(pathToVideo);
        crunches_video.setVideoURI(uri);
        crunches_video.requestFocus();
        crunches_video.setMediaController(mediaController);
        mediaController.setAnchorView(crunches_video);

        crunches_video.start();
    } // end playVideo() ===========================================================================

    // start playVideo() ===========================================================================
    public void playVideo3()
    {
        mediaController = new MediaController(this);

        String pathToVideo = "android.resource://com.example.flexinfitness/" + R.raw.squat_video_final;
        Uri uri = Uri.parse(pathToVideo);
        squats_video.setVideoURI(uri);
        squats_video.requestFocus();
        squats_video.setMediaController(mediaController);
        mediaController.setAnchorView(squats_video);

        squats_video.start();
    } // end playVideo() ===========================================================================

    // start playVideo() ===========================================================================
    public void playVideo4()
    {
        mediaController = new MediaController(this);

        String pathToVideo = "android.resource://com.example.flexinfitness/" + R.raw.lunges_video_final;
        Uri uri = Uri.parse(pathToVideo);
        lunges_video.setVideoURI(uri);
        lunges_video.requestFocus();
        lunges_video.setMediaController(mediaController);
        mediaController.setAnchorView(lunges_video);

        lunges_video.start();
    } // end playVideo() ===========================================================================

} // end tutorial class ============================================================================
