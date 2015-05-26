package com.bijesh.donateblood.storage;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Bijesh on 23-05-2015.
 */
public class DonateSharedPrefs {


    private static final String TAG = DonateSharedPrefs.class.getCanonicalName();
    private static final String DONATE_BLOOD_PREFS = "DonateBloodPrefs";

//     All keys goes here
    public static final String IS_REGISTERED_KEY = "isRegistered";
    public static final String IS_NAVIGATION_DRAWER_LEARNED = "isNavigationDrawerLearned";

    private static DonateSharedPrefs mInstance;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    private DonateSharedPrefs(Context context){
        mSharedPreferences = context.getSharedPreferences(DONATE_BLOOD_PREFS, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
    }

    public static DonateSharedPrefs getInstance(Context context){
        if(mInstance == null){
            mInstance = new DonateSharedPrefs(context);
        }
        return mInstance;
    }

    public void setStringData(String key, String value) {
        mEditor.putString(key, value);
        mEditor.commit();
    }

    public String getStringData(String key, String defaultValue) {
        return mSharedPreferences.getString(key, defaultValue);
    }

}
