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

    VideoView videoView1, videoView2, videoView3, videoView4;

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

        videoView1 = findViewById(R.id.webView1);
        videoView2 = findViewById(R.id.webView2);
        videoView3 = findViewById(R.id.webView3);
        videoView4 = findViewById(R.id.webView4);

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

        String pathToVideo = "android.resource://com.example.flexinfitness/" + R.raw.software_engineering_video;
        Uri uri = Uri.parse(pathToVideo);
        videoView1.setVideoURI(uri);
        videoView1.requestFocus();
        videoView1.setMediaController(mediaController);
        mediaController.setAnchorView(videoView1);

        videoView1.start();
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

    // start playVideo() ===========================================================================
    public void playVideo3()
    {
        mediaController = new MediaController(this);

        String pathToVideo = "android.resource://com.example.flexinfitness/" + R.raw.software_engineering_video;
        Uri uri = Uri.parse(pathToVideo);
        videoView3.setVideoURI(uri);
        videoView3.requestFocus();
        videoView3.setMediaController(mediaController);
        mediaController.setAnchorView(videoView3);

        videoView3.start();
    } // end playVideo() ===========================================================================

    // start playVideo() ===========================================================================
    public void playVideo4()
    {
        mediaController = new MediaController(this);

        String pathToVideo = "android.resource://com.example.flexinfitness/" + R.raw.software_engineering_video;
        Uri uri = Uri.parse(pathToVideo);
        videoView4.setVideoURI(uri);
        videoView4.requestFocus();
        videoView4.setMediaController(mediaController);
        mediaController.setAnchorView(videoView4);

        videoView4.start();
    } // end playVideo() ===========================================================================

} // end tutorial class ============================================================================
