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

    VideoView videoView;

    MediaController mediaController;

    // start onCreate() ============================================================================
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        // connect View & ViewGroups
        playButton = findViewById(R.id.playButton);

        videoView = findViewById(R.id.videoView);

        // setting their onClicks
        playButton.setOnClickListener(this);
    } // end onCreate() ============================================================================

    // start onClick() =============================================================================
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.playButton:
                playVideo();
                break;
        }

    } // end onClick() =============================================================================

    // start playVideo() ===========================================================================
    public void playVideo()
    {
        mediaController = new MediaController(this);

        String pathToVideo = "/home/radix/csuf_spring_2020/software_engineering/FlexinFitness-1/app/src/main/res/raw/software_engineering_video.MP4";

        Uri uri = Uri.parse(pathToVideo);

        videoView.setVideoURI(uri);

        videoView.start();
    } // end playVideo() ===========================================================================
} // end tutorial class ============================================================================
