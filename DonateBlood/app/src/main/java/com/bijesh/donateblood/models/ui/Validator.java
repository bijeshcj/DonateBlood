package com.bijesh.donateblood.models.ui;

/**
 * Created by Bijesh on 23-05-2015.
 */
public class Validator {

    private boolean flag = true;
    private String message;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
