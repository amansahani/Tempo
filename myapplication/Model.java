package com.example.myapplication;

import androidx.annotation.NonNull;

public class Model {
    private int id, age;
    private String name;
    private boolean isActive;

    public Model(int id, String name, int age, boolean isActive) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.isActive = isActive;
    }
    public Model(){

    }

    @NonNull
    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", isActive=" + isActive +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
