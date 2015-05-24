package com.bijesh.donateblood.utils;

import com.bijesh.donateblood.models.ui.Donor;
import com.bijesh.donateblood.models.ui.Validator;

/**
 * Created by Bijesh on 23-05-2015.
 */
public class ValidationUtils {


    public static Validator validateDonateScreen(Donor donor){
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
