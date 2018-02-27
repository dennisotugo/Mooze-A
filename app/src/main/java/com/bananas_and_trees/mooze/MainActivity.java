package com.bananas_and_trees.mooze;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer soundPlayer [] = new MediaPlayer[6];

    private int soundResources [] = {R.raw.coffee_shop, R.raw.fireplace, R.raw.rain, R.raw.summer_night, R.raw.white_noise, R.raw.wind};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < soundPlayer.length; i++) {
            soundPlayer[i] = MediaPlayer.create(this, soundResources[i]);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void but(View view) {
        switch (view.getId()) {
            case R.id.Button:
                soundPlayer[0].start();
                soundPlayer[0].isLooping();
                break;
            default:
                break;
        }
    }
    public void but1(View view) {
        switch (view.getId()) {
            case R.id.Button5:
                soundPlayer[1].start();
                soundPlayer[1].isLooping();
                break;
            default:
                break;
        }
    }
    public void but2(View view) {
        switch (view.getId()) {
            case R.id.Button7:
                soundPlayer[2].start();
                break;
            default:
                break;
        }
    }
    public void but3(View view) {
        switch (view.getId()) {
            case R.id.Button3:
                soundPlayer[3].start();
                soundPlayer[3].isLooping();
                break;
            default:
                break;
        }
    }
    public void but4(View view) {
        switch (view.getId()) {
            case R.id.Button4:
                for (int s = 0; s < soundPlayer.length; s++) {
                    if (soundPlayer[s].isPlaying()) {
                        soundPlayer[s].pause();
                        soundPlayer[s].seekTo(0);
                    }
                }
                break;
            default:
                break;
        }
    }

    public void but5(View view) {
        switch (view.getId()) {
            case R.id.Button8:
                soundPlayer[4].start();
                break;
            default:
                break;
        }
    }
    public void but6(View view) {
        switch (view.getId()) {
            case R.id.Button2:
                soundPlayer[5].start();
                break;
            default:
                break;
        }
    }
    public void but7(View view) {
        switch (view.getId()) {
            case R.id.Button6:
                soundPlayer[6].start();
                break;
            default:
                break;
        }
    }
    public void but8(View view) {
        switch (view.getId()) {
            case R.id.Button9:
                soundPlayer[7].start();
                break;
            default:
                break;
        }
    }
}

