package com.cse486.ece.myapplication;

public class upload {

    private String fooditemdetails;
    private String fooditemprice;
    private String imageUrl;

    public upload(String fooditemdetails, String fooditemprice, String imageUrl) {
        this.fooditemdetails = fooditemdetails;
        this.fooditemprice = fooditemprice;
        this.imageUrl = imageUrl;
    }

    public String getFooditemdetails() {
        return fooditemdetails;
    }

    public void setFooditemdetails(String fooditemdetails) {
        this.fooditemdetails = fooditemdetails;
    }

    public String getFooditemprice() {
        return fooditemprice;
    }

    public void setFooditemprice(String fooditemprice) {
        this.fooditemprice = fooditemprice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
