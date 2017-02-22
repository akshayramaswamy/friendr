/*
 * Akshay Ramaswamy <aramaswa@stanford.edu>
 * CS 193A, Winter 2017
 * Homework Assignment 4
 * FRIENDR  - This app allows the user to rate their favorite characters from the popular show Friends
 * Ratings for each character are saved, and the user can also view characters in landscape mode
 */

package com.example.akshayramaswamy.cs193a_hw4_aramaswa;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.os.*;
import android.view.*;
import stanford.androidlib.*;

public class FriendrMainActivity extends SimpleActivity {
    public static final String BASE_URL = "http://www.martystepp.com/friendr/friends/";
    public static final String TEXT_URL = BASE_URL + "list";
    private MediaPlayer mp;

    /* Method: onCreate()
     * This method starts the Friends theme song on the opening screen and sets
     * the layout for the logo/buttons
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendr_main);
        mp = MediaPlayer.create(this, R.raw.friends_theme);
        mp.start();

    }
    /*
    * Called when the activity is stopped or the app jumps to another activity.
    * We pause the media player here to stop the music from playing.
    */
    @Override
    protected void onPause() {
        super.onPause();
        if (mp != null) {
            mp.pause();
        }
    }

    /*
     * Called when the app returns from jumping to another activity.
     * We resume the media player here to start the music playing again.
     */
    @Override
    protected void onResume() {
        super.onResume();
        if (mp != null) {
            mp.start();
        }
    }

    /* Method viewUsersClick()
     * This method goes to the view users activity when the user clicks on the view
     * button
     */
    public void viewUsersClick(View view) {
        startActivity(ViewUsersActivity.class);
    }


}
