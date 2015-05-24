
package com.bijesh.donateblood;

import android.app.Application;

import com.bijesh.donateblood.activities.HomeActivity;
import com.parse.Parse;
import com.parse.ParseACL;
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


        ParseACL defaultAcl = new ParseACL();
        defaultAcl.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultAcl, true);

        PushService.setDefaultPushCallback(this, HomeActivity.class);
        ParseInstallation.getCurrentInstallation().getInstallationId();

        ParseInstallation.getCurrentInstallation().saveEventually();


    }

    public synchronized static DonateBloodApplication getInstance(){
        return mInstance;
    }


}
