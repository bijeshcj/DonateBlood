package com.bijesh.donateblood.models.ui;

/**
 * Created by Bijesh on 23-05-2015.
 */
public class Donor {

    private String email;
    private String phone;
    private String name;
    private String gender;
    private String above18;
    private String bloodGroup;

    public Donor(String email,String phone,String name,String gender,String above18,String bloodGroup){
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.gender = gender;
        this.above18 = above18;
        this.bloodGroup = bloodGroup;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAbove18() {
        return above18;
    }

    public void setAbove18(String above18) {
        this.above18 = above18;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }
}
