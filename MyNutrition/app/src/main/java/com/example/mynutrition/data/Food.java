package com.example.mynutrition.data;

public class Food {

    private String foodName;
    private String foodType;
    private long timeStamp;


    public Food() {
    }

    public Food(String foodName, String foodType, long timeStamp) {
        this.foodName = foodName;
        this.foodType = foodType;
        this.timeStamp = timeStamp;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
