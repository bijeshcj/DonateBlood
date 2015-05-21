package com.bijesh.donateblood.utils.phone;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * Created by bijesh on 5/21/2015.
 */
public class PhoneUtils {

    private static final String TAG = PhoneUtils.class.getCanonicalName();

    public static String getPrimaryMobileNumber(Context context) {
        String retNumber;
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        retNumber = telephonyManager.getLine1Number();
        Log.d(TAG,"Primary number "+retNumber);
        if (retNumber == null)
            return "";
        else
            return retNumber;
    }

}
