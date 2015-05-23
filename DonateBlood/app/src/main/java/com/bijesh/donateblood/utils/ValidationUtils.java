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
            flagE = donor.getPhone().trim().length() > 0;
            if(!flagE){
                retFlag.setMessage("Phone number is mandatory");
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

}
