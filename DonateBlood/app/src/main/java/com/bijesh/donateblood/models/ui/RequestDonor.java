package com.bijesh.donateblood.models.ui;

/**
 * Created by Bijesh on 23-05-2015.
 */
public class RequestDonor {


    private String email;
    private String phone;
    private String name;
    private String bloodGroup;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RequestDonor(String email,String phone,String name,String bloodGroup,String description){
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.bloodGroup = bloodGroup;
        this.description = description;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }
}
