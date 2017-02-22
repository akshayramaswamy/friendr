package com.example.akshayramaswamy.cs193a_hw4_aramaswa;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.RatingBar;

import com.squareup.picasso.Picasso;

import stanford.androidlib.SimpleActivity;

public class ProfileActivity extends SimpleActivity {

    /* Method: onCreate()
     * This method sets the layout in portrait mode when a specific profile is clicked.
     * A profile fragment is created, and we set the name of the fragment. The fragment then
     * sets the saved ratings for the selected profile and displays the profile picture.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        final String friendName = getStringExtra("friend_name", "chandler");

        ProfileFragment frag = (ProfileFragment) getFragmentManager().findFragmentById(R.id.frag_details);
        frag.setFriendName(friendName);
    }

}
