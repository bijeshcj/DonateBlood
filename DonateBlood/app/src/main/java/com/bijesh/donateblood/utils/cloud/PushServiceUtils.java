package com.bijesh.donateblood.utils.cloud;

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

}
