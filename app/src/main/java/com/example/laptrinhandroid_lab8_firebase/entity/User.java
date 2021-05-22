package com.example.laptrinhandroid_lab8_firebase.entity;

import java.util.Date;

public class User {

    public String id;
     public String email;
     public String name;
     public int happy;
     public int unhappy;
     public int nothing;

    public User() {
    }

    public User(String name, String email) {
        this.name = name;
        happy =0;
        unhappy =0;
        nothing = 0;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHappy() {
        return happy;
    }

    public void setHappy(int happy) {
        this.happy = happy;
    }

    public int getUnhappy() {
        return unhappy;
    }

    public void setUnhappy(int unhappy) {
        this.unhappy = unhappy;
    }

    public int getNothing() {
        return nothing;
    }

    public void setNothing(int nothing) {
        this.nothing = nothing;
    }

    @Override
    public String toString() {
        return "User{" +
                "happy=" + happy +
                ", unhappy=" + unhappy +
                ", nothing=" + nothing +
                '}';
    }
}
