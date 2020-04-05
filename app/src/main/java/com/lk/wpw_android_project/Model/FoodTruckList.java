package com.lk.wpw_android_project.Model;

public class FoodTruckList {
    private String Title;
    private String SubTitle;
    private String Description;
    private String ImageUrl;


    public FoodTruckList() {
    }

    public FoodTruckList(String title, String subTitle, String description, String imageUrl) {
        Title = title;
        SubTitle = subTitle;
        Description = description;
        ImageUrl = imageUrl;
    }

    // Title
    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    // SubTitle
    public String getSubTitle() {
        return SubTitle;
    }

    public void setSubTitle(String subTitle) {
        SubTitle = subTitle;
    }

    // Description
    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    // Imag Url
    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }
}
