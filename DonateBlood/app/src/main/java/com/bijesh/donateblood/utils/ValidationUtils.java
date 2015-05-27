package com.bijesh.donateblood.utils;

import android.content.Context;

import com.bijesh.donateblood.models.ui.Donor;
import com.bijesh.donateblood.models.ui.RequestDonor;
import com.bijesh.donateblood.models.ui.Validator;
import com.bijesh.donateblood.storage.DonateSharedPrefs;

/**
 * Created by Bijesh on 23-05-2015.
 */
public class ValidationUtils {


    private static final int ONE_MILLISECOND = 1000;
    private static final int ONE_MINUTE = 60;
    private static final long PUSH_REQUEST_TIME_INTERVAL = ONE_MILLISECOND * ONE_MINUTE * 3;


    public static boolean isUserAlreadyRegistered(Context context){
        String objectId = DonateSharedPrefs.getInstance(context).getStringData(DonateSharedPrefs.IS_REGISTERED_KEY,"");
        if(objectId != null && objectId.trim().length() > 0){
            return true;
        }else
            return false;
    }

    public static boolean hasAlreadySentRequest(long currentTime,long previousRequestTime){
        if(currentTime < previousRequestTime + PUSH_REQUEST_TIME_INTERVAL){
            return false;
        }else {
            return true;
        }
    }


    public static Validator validateRequestDonorScreen(RequestDonor requestDonor){
        Validator retFlag = new Validator();
        if(requestDonor != null) {
            boolean flagE = requestDonor.getPhone().trim().length() > 0;
            if (!flagE) {
                retFlag.setMessage("Phone is mandatory");
                retFlag.setFlag(flagE);
                return retFlag;
            }
            flagE = requestDonor.getCity().trim().length() > 0;
            if (!flagE) {
                retFlag.setMessage("City is mandatory");
                retFlag.setFlag(flagE);
                return retFlag;
            }
            flagE = requestDonor.getCountry().trim().length() > 0;
            if (!flagE) {
                retFlag.setMessage("Country is mandatory");
                retFlag.setFlag(flagE);
                return retFlag;
            }
        }

        return retFlag;
    }

    public static Validator validateRegisterScreen(Donor donor){
        Validator retFlag = new Validator();
        if(donor != null){
            boolean flagE = donor.getEmail().trim().length() > 0;
            if(!flagE){
                retFlag.setMessage("Email is mandatory");
                retFlag.setFlag(flagE);
                return retFlag;
            }
            flagE = validateBloodGroup(donor);
            if(!flagE){
                retFlag.setMessage("Phone number is mandatory for "+donor.getBloodGroup());
                retFlag.setFlag(flagE);
                return retFlag;
            }
            flagE = donor.getName().trim().length() > 0;
            if(!flagE){
                retFlag.setMessage("Name is mandatory");
                retFlag.setFlag(flagE);
                return retFlag;
            }
        }
        return retFlag;
    }

    private static boolean validateBloodGroup(Donor donor){
        if(donor.getPhone().trim().length() > 0)
            return true;
        else{
            if(donor.getBloodGroup().equals("AB-") || donor.getBloodGroup().equals("B-") ||
                    donor.getBloodGroup().equals("A-") || donor.getBloodGroup().equals("O-") ||
                    donor.getBloodGroup().equals("AB+")){
                return false;
            }else{
                return true;
            }
        }
    }



}
