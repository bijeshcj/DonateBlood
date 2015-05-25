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
    private String city;
    private String country;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RequestDonor(String email,String phone,String name,String bloodGroup,String description,String city,String country){
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.bloodGroup = bloodGroup;
        this.description = description;
        this.city = city;
        this.country = country;
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
