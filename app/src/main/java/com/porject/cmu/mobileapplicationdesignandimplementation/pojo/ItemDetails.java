package com.porject.cmu.mobileapplicationdesignandimplementation.pojo;

/**
 * Created by Manish Varma D on 14-08-2018.
 */

public class ItemDetails {

     private String Name;
     private String Price;
     private String Sizes;
     private String ColorMaterial;
     private String Discount;

    public ItemDetails(String name, String price, String sizes, String colorMaterial, String discount) {
        Name = name;
        Price = price;
        Sizes = sizes;
        ColorMaterial = colorMaterial;
        Discount = discount;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getSizes() {
        return Sizes;
    }

    public void setSizes(String sizes) {
        Sizes = sizes;
    }

    public String getColorMaterial() {
        return ColorMaterial;
    }

    public void setColorMaterial(String colorMaterial) {
        ColorMaterial = colorMaterial;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String discount) {
        Discount = discount;
    }
}
