package com.example.akshayramaswamy.cs193a_hw4_aramaswa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.os.*;
import android.view.*;
import stanford.androidlib.*;

public class FriendrMainActivity extends SimpleActivity {
    public static final String BASE_URL = "http://www.martystepp.com/friendr/friends/";
    public static final String TEXT_URL = BASE_URL + "list";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendr_main);
    }

    public void signUpClick(View view) {
        //startActivity(SignUpActivity.class);
    }

    public void swipeClick(View view) {
        //startActivity(SwipeUsersActivity.class);
    }

    public void viewUsersClick(View view) {
        startActivity(ViewUsersActivity.class);
    }

    // @TODO: play/pause background music

}
