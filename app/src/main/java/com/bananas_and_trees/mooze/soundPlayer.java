package com.bananas_and_trees.mooze;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.util.Log;

public class soundPlayer {


    private Context context;
    private Uri uri;
    private int resourceId;

    // which file is getting played
    public static final int URI_PLAYING = 1;
    public static final int RESOURCE_PLAYING = 2;
    private int filePlaying;

    // states of the media player
    public static final int STATE_PLAYING = 1;
    public static final int STATE_PAUSED = 2;
    public static final int STATE_STOP = 3;

    // current state
    private int state = STATE_STOP;

    // current mediaPlayer which is playing
    private int mediaPlayerIndex = -1;

    // 3 media players
    private MediaPlayer soundPlayer[] = new MediaPlayer[3];

    // current volume
    private float vol;


    public soundPlayer(Context context) {
        this.context = context;
    }

    /**
     * plays the provided uri
     * @param uri
     */
    public void play(Uri uri) {
        this.uri = uri;
        // current playing file
        filePlaying = URI_PLAYING;
        // stop any playing session
        stop();

        // initialize and set listener to three mediaplayers
        for (int i = 0; i < soundPlayer.length; i++) {
            soundPlayer[i] = MediaPlayer.create(context, uri);
            soundPlayer[i].setOnCompletionListener(completionListener);
        }

        // set nextMediaPlayers
        soundPlayer[0].setNextMediaPlayer(soundPlayer[1]);
        soundPlayer[1].setNextMediaPlayer(soundPlayer[2]);

        // start the first MediaPlayer
        soundPlayer[0].start();
        // set mediaplayer inex
        mediaPlayerIndex = 0;
        // set state
        state = STATE_PLAYING;
    }

    /**
     * play file from resource
     * @param resourceId
     */
    public void play(int resourceId) {
        this.resourceId = resourceId;
        filePlaying = RESOURCE_PLAYING;
        stop();
        for (int i = 0; i < soundPlayer.length; i++) {
            soundPlayer[i] = MediaPlayer.create(context, resourceId);
            soundPlayer[i].setOnCompletionListener(completionListener);
        }

        soundPlayer[0].setNextMediaPlayer(soundPlayer[1]);
        soundPlayer[1].setNextMediaPlayer(soundPlayer[2]);

        soundPlayer[0].start();
        mediaPlayerIndex = 0;
        state = STATE_PLAYING;
    }

    /**
     * play if the mediaplayer is pause
     */
    public void play() {
        if (state == STATE_PAUSED) {
            soundPlayer[mediaPlayerIndex].start();
            Log.d("soundPlayer", "playing");
            state = STATE_PLAYING;
        }
    }

    /**
     * pause current playing session
     */
    public void pause() {
        if (state == STATE_PLAYING) {
            soundPlayer[mediaPlayerIndex].pause();
            Log.d("soundPlayer", "pausing");
            state = STATE_PAUSED;
        }
    }

    /**
     * get current state
     * @return
     */
    public int getState() {
        return state;
    }

    /**
     * stop every mediaplayer
     */
    public void stop() {
        for(int i = 0 ; i < soundPlayer.length ; i++) {
            if (soundPlayer[i] != null) {
                soundPlayer[i].stop();

                if(soundPlayer[i].isPlaying()) {
                    soundPlayer[i].release();
                }
            }
        }
        state = STATE_STOP;
    }

    /**
     * set vol for every mediaplayer
     * @param vol
     */
    public void setVol(float vol) {
        this.vol = vol;
        for(int i = 0 ; i < soundPlayer.length ; i++) {
            if (soundPlayer[i] != null && soundPlayer[i].isPlaying()) {
                soundPlayer[i].setVolume(vol, vol);
            }
        }
    }

    /**
     * internal listener which handles looping thing
     */
    private MediaPlayer.OnCompletionListener completionListener = new OnCompletionListener() {

        @Override
        public void onCompletion(MediaPlayer curmp) {
            int mpEnds = 0;
            int mpPlaying = 0;
            int mpNext = 0;
            if(curmp == soundPlayer[0]) {
                mpEnds = 0;
                mpPlaying = 1;
                mpNext = 2;
            }
            else if(curmp == soundPlayer[1]) {
                mpEnds = 1;
                mpPlaying = 2;
                mpNext = 0;  // corrected, else index out of range
            }
            else if(curmp == soundPlayer[2]) {
                mpEnds = 2;
                mpPlaying = 0; // corrected, else index out of range
                mpNext = 1; // corrected, else index out of range
            }

            // as we have set mp2 mp1's next, so index will be 1
            mediaPlayerIndex = mpPlaying;
            Log.d("soundPlayer", "Media Player " + mpEnds);
            try {
                // mp3 is already playing release it
                if (soundPlayer[mpNext] != null) {
                    soundPlayer[mpNext].release();
                }
                // if we are playing uri
                if (filePlaying == URI_PLAYING) {
                    soundPlayer[mpNext] = MediaPlayer.create(context, uri);
                } else {
                    soundPlayer[mpNext] = MediaPlayer.create(context, resourceId);
                }
                // at listener to mp3
                soundPlayer[mpNext].setOnCompletionListener(this);
                // set vol
                soundPlayer[mpNext].setVolume(vol, vol);
                // set nextMediaPlayer
                soundPlayer[mpPlaying].setNextMediaPlayer(soundPlayer[mpNext]);
                // set nextMediaPlayer vol
                soundPlayer[mpPlaying].setVolume(vol, vol);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

}
