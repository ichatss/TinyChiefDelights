package com.tinychiefdelights.model;

import java.util.List;

public class Cook extends User {

    // Тип повара
     private enum CookType{

        CHEF{
            // Методы
            void createDish(){

            }

            void editDish(){

            }

            void removeDish(){

            }
        }, CONFECTIONER, FISH_COOK, MEAT_COOK;

        // Общие поля на все ENUM
        double startSalary;

        }

    // Поля
    private float rating;

    private boolean cookStatus;

    private List<Review> reviewList;

    private String aboutCook;

    // Getters and Setters
    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public boolean isCookStatus() {
        return cookStatus;
    }

    public void setCookStatus(boolean cookStatus) {
        this.cookStatus = cookStatus;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    public String getAboutCook() {
        return aboutCook;
    }

    public void setAboutCook(String aboutCook) {
        this.aboutCook = aboutCook;
    }

    // Методы
    //...
}