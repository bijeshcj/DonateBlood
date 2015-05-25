package com.bijesh.donateblood.utils.cloud;

import com.bijesh.donateblood.models.ui.RequestDonor;
import com.parse.ParsePush;

/**
 * Created by Bijesh on 23-05-2015.
 */
public class PushServiceUtils {


    public static void sendPush(){
        ParsePush push = new ParsePush();
        push.setChannel("Donate");
        push.setMessage("Need B+ blood, please help");
        push.sendInBackground();
    }

    public static void sendPush(RequestDonor requestDonor){
        ParsePush push = new ParsePush();

        push.setChannel("Donate");
        push.setMessage("Please help, need "+requestDonor.getBloodGroup()+" blood, contact person "+
                requestDonor.getName()+" number "+requestDonor.getPhone()+" city "+requestDonor.getCity()+" country "+requestDonor.getCountry());
        push.sendInBackground();
    }



}
