package com.porject.cmu.mobileapplicationdesignandimplementation.pojo;

/**
 * Created by Manish Varma D on 14-08-2018.
 */

public class ShopDetails {

    private String Name;
    private String Phone;
    private String Email;
    private String Address;
    private String Details;
    private String Website;
    private String ImgSrc;
    private String WorkingHours;


    public ShopDetails(String name, String phone, String email, String address, String details, String website, String imgSrc, String workingHours) {
        Name = name;
        Phone = phone;
        Email = email;
        Address = address;
        Details = details;
        Website = website;
        ImgSrc = imgSrc;
        WorkingHours = workingHours;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getWebsite() {
        return Website;
    }

    public void setWebsite(String website) {
        Website = website;
    }

    public String getImgSrc() {
        return ImgSrc;
    }

    public void setImgSrc(String imgSrc) {
        ImgSrc = imgSrc;
    }

    public String getWorkingHours() {
        return WorkingHours;
    }

    public void setWorkingHours(String workingHours) {
        WorkingHours = workingHours;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }

    @Override
    public String toString() {
        return "ShopDetails{" +
                "Name='" + Name + '\'' +
                ", Phone='" + Phone + '\'' +
                ", Email='" + Email + '\'' +
                ", Address='" + Address + '\'' +
                ", Website='" + Website + '\'' +
                ", ImgSrc='" + ImgSrc + '\'' +
                ", WorkingHours='" + WorkingHours + '\'' +
                '}';
    }
}
