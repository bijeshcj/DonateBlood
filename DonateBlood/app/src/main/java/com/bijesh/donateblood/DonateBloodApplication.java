
package com.bijesh.donateblood;

import android.app.Application;

import com.bijesh.donateblood.activities.HomeActivity;
import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.PushService;

/**
 * Created by bijesh on 5/21/2015.
 */
public class DonateBloodApplication extends Application {

    private static DonateBloodApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;


        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "KJfdKjZnGbTBX1OzEcvIWIehgxsr2G46f0DLAGar", "o2zXEa0uO27sdSIwu9TXczWe2rEAfJ23wtuSBvVD");
        ParseInstallation.getCurrentInstallation().saveInBackground();


        PushService.setDefaultPushCallback(this, HomeActivity.class);

    }

    public synchronized static DonateBloodApplication getInstance(){
        return mInstance;
    }


}
