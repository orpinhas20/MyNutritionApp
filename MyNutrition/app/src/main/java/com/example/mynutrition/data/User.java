package com.example.mynutrition;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class User {
    private String uid;
    private String phone;
    private String creationTime;

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public User() { }

    public User(String uid, String phone, String food) {
        this.setUid(uid);
        this.setPhone(phone);
        this.setCreationTime(
                new SimpleDateFormat("yyyy-MM-dd_hh:mm:ss", Locale.getDefault())
                        .format(Calendar.getInstance().getTime()));
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
