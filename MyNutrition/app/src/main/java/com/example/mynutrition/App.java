package com.example.mynutrition;

import android.app.Application;

import com.example.mynutrition.data.User;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class App extends Application {
    private User user;
    private FirebaseFirestore db;

    public FirebaseUser getFbUser() {
        return fbUser;
    }

    public void setFbUser(FirebaseUser fbUser) {
        this.fbUser = fbUser;
    }

    private FirebaseUser fbUser;
    private static App instance;

    public FirebaseFirestore getDb() {
        return db;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        user = new User();
        instance = this;
        db = FirebaseFirestore.getInstance();
    }

    public User getUser() {
        return user;
    }

    public static App getInstance() {
        return instance;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static void setInstance(App instance) {
        App.instance = instance;
    }
}
