package com.example.akshayramaswamy.cs193a_hw4_aramaswa;

import android.app.AlertDialog;
import android.os.Bundle;
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
    // array of all countries to display
    private static String[] friendsImages;

    // instance initializer
    // runs before any other code (on construction)
    {
        setTraceLifecycle(true);

    }

    /*
     * Called when the activity is created.
     * Adds the various flag stamps to the screen.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_users);
        downloadImageNames();

    }
    private void downloadImageNames() {
        Ion.with(this)
                .load("http://www.martystepp.com/friendr/friends/list")
                .asString()
                .setCallback(new FutureCallback<String>() {
                    public void onCompleted(Exception e, String result) {
                        // code to process the result when done downloading
                        // "abc.jpg \n xyz.jpg \n ..."
                        friendsImages = result.split("\n");
                        for (String name : friendsImages) {
                            addFriend(name);
                        }
                        //SimpleList.with(PuppyActivity.this)
                                //.setItems(R.id.puppy_spinner, allImages);
                    }
                });
    }
    /*
     * This method adds a new copy of the flag widget described in flag.xml
     * to the screen, with the given country name/image.
     */
    private void addFriend(final String friendName) {
        // inflate a copy of the flag.xml layout into actual Java widgets
        View friend = getLayoutInflater()
                .inflate(R.layout.friends, /* parent */ null);

        TextView tv = (TextView) friend.findViewById(R.id.friend_text);
        tv.setText(friendName);

        // this code is to convert a string like "United States" into
        // an integer resource ID like R.drawable.unitedstates
        final String friendName2 = friendName.replace(" ", "").toLowerCase();
        //int flagImageID = getResources().getIdentifier(
           //     countryName2, "drawable", getPackageName());

        // listen for click events on the flag image button
        ImageButton img = (ImageButton) friend.findViewById(R.id.friend_image);
        //img.setImageResource(flagImageID);
        String imageUrl = "http://www.martystepp.com/friendr/friends/" + friendName2 + ".jpg";
        Picasso.with(this)
                .load(imageUrl)
                .placeholder(R.drawable.loading)
                .into(img);
        img.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)  {

                //if (isPortrait()) {
                  //  // jump to DetailsActivity
                ImageButton button = (ImageButton) v;
                //String tag = button.getTag().toString();
                   startActivity(ProfileActivity.class, "friend_name", friendName2);
                //}
                //doTheDialog(friendName);
            }
        });

        // add the flag stamp to the screen
        GridLayout layout = (GridLayout) findViewById(R.id.activity_view_users);
        layout.canScrollVertically(1);
        layout.addView(friend);


    }


    private void doTheDialog(String countryName) {
        // version without Stanford library
//        AlertDialog.Builder builder = new AlertDialog.Builder(FlagsActivity.this);
//        builder.setTitle("My Dialog");
//        builder.setMessage("You clicked " + countryName);
//        builder.setPositiveButton("OK",
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int id) {
//                        // code to run when OK is pressed
//                        Toast.makeText(FlagsActivity.this, "You clicked OK",
//                                Toast.LENGTH_SHORT).show();
//                    }
//                });
//        AlertDialog dialog = builder.create();
//        dialog.show();

        // version with Stanford library
        // pop up a simple alert dialog
        SimpleDialog.with(this)
                .showAlertDialog("You REALLY clicked " + countryName);

        // pop up a dialog asking for input
        SimpleDialog.with(this)
                .showInputDialog("Type your name:", "Submit");
    }

    /*
     * This method is called when the showInputDialog call above is finished.
     * It "returns" the user's input to our code as the String input parameter below.
     * This input parameter represents the text that the user typed into the dialog.
     */
    @Override
    public void onInputDialogClose(AlertDialog dialog, String input) {
        toast("You typed: " + input);
    }
}
