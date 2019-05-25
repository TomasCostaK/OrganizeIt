package com.example.drawer.Models;

import java.util.ArrayList;

public class Card_Task {
    private String Title, Description, Tasks ;
    private int Thumbnail ;

    public Card_Task() {
    }


    public Card_Task(String title, String description, String tasks, int thumbnail) {
        this.Title = title;
        this.Description = description;
        this.Tasks = tasks;
        this.Thumbnail = thumbnail;
    }


    public String getTitle() {
        return this.Title;
    }

    public String getTasks() {
        return this.Tasks;
    }

    public String getDescription() {
        return this.Description;
    }

    public int getThumbnail() {
        return this.Thumbnail;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public void setTasks(String category) {
        this.Tasks = category;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public void setThumbnail(int thumbnail) {
        this.Thumbnail = thumbnail;
    }
}
