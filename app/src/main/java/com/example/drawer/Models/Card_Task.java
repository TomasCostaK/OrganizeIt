package com.example.drawer.Models;

import java.util.ArrayList;

public class Card_Task {
    private String Title, Description, Tasks ;
    private int Thumbnail ;

    public Card_Task() {
    }


    public Card_Task(String title, String description, String tasks, int thumbnail) {
        Title = title;
        Description = description;
        Tasks = tasks;
        Thumbnail = thumbnail;
    }


    public String getTitle() {
        return Title;
    }

    public String getTasks() {
        return Tasks;
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

    public void setTasks(String category) {
        Tasks = category;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setThumbnail(int thumbnail) {
        Thumbnail = thumbnail;
    }
}
