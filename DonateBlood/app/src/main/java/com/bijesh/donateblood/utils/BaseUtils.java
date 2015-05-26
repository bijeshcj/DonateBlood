package com.bijesh.donateblood.utils;

import android.content.Context;
import android.content.Intent;

/**
 * Created by bijesh on 5/21/2015.
 */
public class BaseUtils {


    public static void shareApplication(Context context){
        String url = "https://play.google.com/store/apps/details?id=com.bijesh.donateblood";

        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Donate Blood");
        sharingIntent.putExtra(Intent.EXTRA_TEXT,url);
        context.startActivity(Intent.createChooser(sharingIntent,"Share Via"));
    }

}
