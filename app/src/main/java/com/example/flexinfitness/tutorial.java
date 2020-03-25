package com.example.flexinfitness;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;
// start tutorial class ============================================================================
public class tutorial extends AppCompatActivity
{
    // declare View & ViewGroups
    Button playButton;

    VideoView videoView;

    MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);



    }
} // end tutorial class ============================================================================
