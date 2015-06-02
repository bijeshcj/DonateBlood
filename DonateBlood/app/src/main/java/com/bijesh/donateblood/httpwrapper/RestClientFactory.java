package com.bijesh.donateblood.httpwrapper;


import android.util.Log;

import com.bijesh.donateblood.httpwrapper.impl.DonateBloodRestClient;


public class RestClientFactory {

    private static final String TAG = RestClientFactory.class.getCanonicalName();
    private RestClientFactory() {

    }

    public static synchronized RestClient getDefaultRestClient() {
        Log.d(TAG, "layer7 getDefaultRestClient ");
        return DonateBloodRestClient.getInstance();
    }
}
