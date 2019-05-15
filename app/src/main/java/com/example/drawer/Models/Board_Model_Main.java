package com.example.drawer.Models;

import com.example.drawer.Models.Card_Model_Main;

import java.util.ArrayList;

public class Board_Model_Main {

    private String title;
    private ArrayList<Card_Model_Main> arrayList;



    public Board_Model_Main() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Card_Model_Main> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<Card_Model_Main> arrayList) {
        this.arrayList = arrayList;
    }
}
