package com.example.mynutrition.data;

public class User {
    private String uid;
    private String phone;

    public User() { }

    public User(String uid, String phone, String food) {
        this.setUid(uid);
        this.setPhone(phone);
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
