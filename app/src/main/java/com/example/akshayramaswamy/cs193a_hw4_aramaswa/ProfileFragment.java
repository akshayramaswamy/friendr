package com.example.akshayramaswamy.cs193a_hw4_aramaswa;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import com.squareup.picasso.Picasso;

import stanford.androidlib.SimpleActivity;
import stanford.androidlib.SimpleFragment;


public class ProfileFragment extends SimpleFragment {

    SharedPreferences friendsPreference;
    SharedPreferences.Editor editor;
    private String friendNameFragment;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /* Method: onCreateView()
     * This method sets the fragment profile as the layout
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    /* Method: onActivityCreated()
     * This method sets the initial name passed to the fragment and
     * creates an onClickListener for the ratings bar
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SimpleActivity activity = getSimpleActivity();
        RatingBar ratingBar = (RatingBar) activity.findViewById(R.id.ratingBar);
        friendNameFragment = getStringExtra("friend_name", "chandler");
        setFriendName(friendNameFragment);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                final float numStars = ratingBar.getRating();
                editor = friendsPreference.edit();
                editor.putFloat(friendNameFragment+"Rating", numStars);
                editor.commit();
            }
        });

    }

    /*  Method: ratings
     *  This method is called everytime we
     *  want to set the ratings to the user's preference
     */
    public void ratings(){
        SimpleActivity activity = getSimpleActivity();
        RatingBar ratingBar = (RatingBar) activity.findViewById(R.id.ratingBar);
        friendsPreference = PreferenceManager.getDefaultSharedPreferences(activity);
        if (friendsPreference.getFloat(friendNameFragment+"Rating", 0)!=0) {
            ratingBar.setRating(friendsPreference.getFloat(friendNameFragment+"Rating", 0));
        }
    }

    /* Method: setFriendName()
     * This method sets the image/text of the profile fragment
     * using the Picasso library
     */
    public void setFriendName(String friendName) {
        friendNameFragment = friendName;
        SimpleActivity activity = getSimpleActivity();
        String imageUrl = "http://www.martystepp.com/friendr/friends/" + friendName + ".jpg";
        Picasso.with(activity)
                .load(imageUrl)
                .placeholder(R.drawable.loading)
                .into(activity.$IV(R.id.friend_image));
        activity.$TV(R.id.friend_text).setText(friendNameFragment.substring(0, 1).toUpperCase() + friendNameFragment.substring(1));
        ratings();
    }

}
