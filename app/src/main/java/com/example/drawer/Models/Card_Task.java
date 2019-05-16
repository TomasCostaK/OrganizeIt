package com.example.drawer.Models;

import java.util.ArrayList;

public class Card_Task {
    private String Title ;
    private String Description ;
    private ArrayList<String> tasks;
    private int Thumbnail ;

    public Card_Task() {
    }


    public Card_Task(String title, String description, int thumbnail) {
        Title = title;
        Description = description;
        Thumbnail = thumbnail;
    }


    public String getTitle() {
        return Title;
    }

    public ArrayList<String> getTasks() {
        return tasks;
    }

    public String getDescription() {
        return Description;
    }

    public int getThumbnail() {
        return Thumbnail;
    }


    public void setTitle(String title) {
        Title = title;
    }

    public void setTasks(ArrayList<String> category) {
        tasks = category;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setThumbnail(int thumbnail) {
        Thumbnail = thumbnail;
    }
}
