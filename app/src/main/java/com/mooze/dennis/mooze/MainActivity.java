package com.mooze.dennis.mooze;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    private MediaPlayer soundPlayer[] = new MediaPlayer[6];

    private int soundResources[] = {R.raw.coffee_shop, R.raw.fireplace, R.raw.rain, R.raw.summer_night, R.raw.white_noise, R.raw.wind};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < soundPlayer.length; i++) {
            soundPlayer[i] = MediaPlayer.create(this, soundResources[i]);
            soundPlayer[i].seekTo(1000);
            soundPlayer[i].setLooping(true);
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
                break;
            default:
                break;
        }
    }

    public void but1(View view) {
        switch (view.getId()) {
            case R.id.Button1:
                soundPlayer[1].start();
                break;
            default:
                break;
        }
    }

    public void but4(View view) {
        switch (view.getId()) {
            case R.id.Button4:
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

                break;
            default:
                break;
        }
    }

    public void but2(View view) {
        switch (view.getId()) {
            case R.id.Button2:
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
            case R.id.Button5:
                soundPlayer[4].start();
                break;
            default:
                break;
        }
    }

    public void but6(View view) {
        switch (view.getId()) {
            case R.id.Button6:
                soundPlayer[5].start();
                break;
            default:
                break;
        }
    }
}

