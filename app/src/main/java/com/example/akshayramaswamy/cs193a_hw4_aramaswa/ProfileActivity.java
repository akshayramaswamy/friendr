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
    SharedPreferences wmbPreference1;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        final String friendName = getStringExtra("friend_name", "Chandler");
        setFriendName(friendName);

        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        //String prevRating = friendName + "Rating";
        wmbPreference1 = PreferenceManager.getDefaultSharedPreferences(this);
        if (wmbPreference1.getFloat(friendName+"Rating", 0)!=0) {
            Log.d("print", "going");
            ratingBar.setRating(wmbPreference1.getFloat(friendName+"Rating", 0));
        }
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                final float numStars = ratingBar.getRating();
                editor = wmbPreference1.edit();
                editor.putFloat(friendName+"Rating", numStars);
                editor.commit();
                Log.d("print", ""+ wmbPreference1.getFloat(friendName+"Rating", 0));
            }
        });


    }

    public void setFriendName(String friendName) {
        String imageUrl = "http://www.martystepp.com/friendr/friends/" + friendName + ".jpg";
        //ImageButton img = (ImageButton) friend.findViewById(R.id.friend_image);
        Picasso.with(this)
                .load(imageUrl)
                .placeholder(R.drawable.loading)
                .into($IV(R.id.friend_image));
        //int imageID = getResourceId(pokemonName.toLowerCase(), "drawable");
       // int fileID = getResourceId(pokemonName.toLowerCase(), "raw");
        //String fileText = readFileText(fileID);
        $TV(R.id.friend_text).setText(friendName.substring(0, 1).toUpperCase() + friendName.substring(1));
       //$IV(R.id.pokemon_image).setImageResource(imageID);
        //$TV(R.id.pokemon_details).setText(fileText);
    }
}
