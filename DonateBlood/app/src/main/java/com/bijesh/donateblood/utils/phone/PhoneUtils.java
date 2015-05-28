package com.bijesh.donateblood.utils.phone;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.util.Patterns;

import java.util.regex.Pattern;

/**
 * Created by bijesh on 5/21/2015.
 */
public class PhoneUtils {

    private static final String TAG = PhoneUtils.class.getCanonicalName();

//    public static String getPrimaryMobileNumber(Context context) {
//        String retNumber = null;
////        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
////        retNumber = telephonyManager.getLine1Number();
////        Log.d(TAG,"Primary number "+retNumber);
//        if (retNumber == null)
//            return "";
//        else
//            return retNumber;
//    }
//
//    public static String getPrimaryEmailAddress(Context context){
//        String retEmail = null;
//        Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
//        Account[] accounts = AccountManager.get(context).getAccounts();
//        for (Account account : accounts) {
//            if (emailPattern.matcher(account.name).matches()) {
//                retEmail = account.name;
//                Log.d(TAG,"email "+retEmail);
//                if(retEmail != null)
//                    break;
//            }
//        }
//        return retEmail;
//    }



}
