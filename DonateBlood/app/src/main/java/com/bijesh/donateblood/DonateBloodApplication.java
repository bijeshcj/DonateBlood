package com.bijesh.donateblood;

import android.app.Application;

import com.parse.Parse;

/**
 * Created by bijesh on 5/21/2015.
 */
public class DonateBloodApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();


        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "KJfdKjZnGbTBX1OzEcvIWIehgxsr2G46f0DLAGar", "o2zXEa0uO27sdSIwu9TXczWe2rEAfJ23wtuSBvVD");


    }
}
