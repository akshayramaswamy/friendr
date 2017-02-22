package com.example.akshayramaswamy.cs193a_hw4_aramaswa;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.squareup.picasso.Picasso;

import stanford.androidlib.SimpleActivity;
import stanford.androidlib.SimpleDialog;

public class ViewUsersActivity extends SimpleActivity {
    // array of all friends to display
    private static String[] friendsImages;

    // instance initializer
    // runs before any other code (on construction)
    {
        setTraceLifecycle(true);

    }

    /* Method: onCreate()
     * Called when the activity is created.
     * Adds the various images to the screen.
     * Calls downloadImageNames using Ion library
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_users);
        downloadImageNames();

    }

    /* Method: downloadImageNames()
     * This method uses the Ion library to get the list of image names,
     * then puts them into the friendsImages array
     */
    private void downloadImageNames() {
        Ion.with(this)
                .load("http://www.martystepp.com/friendr/friends/list")
                .asString()
                .setCallback(new FutureCallback<String>() {
                    public void onCompleted(Exception e, String result) {
                        // code to process the result when done downloading
                        friendsImages = result.split("\n");
                        for (String name : friendsImages) {
                            addFriend(name);
                        }
                    }
                });
    }

    /* Method: addFriend()
     * This method adds a new friend widget described in friends.xml
     * to the screen, with the given friend name/image.
     */
    private void addFriend(final String friendName) {
        // inflate a copy of the flag.xml layout into actual Java widgets
        View friend = getLayoutInflater()
                .inflate(R.layout.friends, /* parent */ null);

        TextView tv = (TextView) friend.findViewById(R.id.friend_left_text);
        tv.setText(friendName);

        // this code is to convert a string like Chandler into chandler
        final String friendName2 = friendName.replace(" ", "").toLowerCase();

        // listen for click events on the friend image button
        ImageButton img = (ImageButton) friend.findViewById(R.id.friend_left_image);
        String imageUrl = "http://www.martystepp.com/friendr/friends/" + friendName2 + ".jpg";
        Picasso.with(this)
                .load(imageUrl)
                .placeholder(R.drawable.loading)
                .into(img);
        img.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)  {

                if (isPortrait()) {

                ImageButton button = (ImageButton) v;
                   startActivity(ProfileActivity.class, "friend_name", friendName2);
                } else {
                    // both fragments are in the same activity;
                    // set the friend to be displayed on the right side
                    ProfileFragment frag = (ProfileFragment) getFragmentManager().findFragmentById(R.id.frag_details);

                    frag.setFriendName(friendName2);
                    Log.d("friendcheck", friendName2);
                }
            }
        });

        // add  to the screen
        GridLayout layout = (GridLayout) findViewById(R.id.activity_view_users);
        layout.addView(friend);


    }





}
