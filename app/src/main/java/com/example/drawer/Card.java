package com.example.drawer;

public class Card {
    private String mText;
    private String mItem1;


    public Card(String text, String item1) {
        mText = text;
        mItem1 = item1;

    }

    public Card(String text) {
        mText = text;
        mItem1 = null;
    }



    public String getmText() {
        return mText;
    }

    public String getmItem1() {
        return mItem1;
    }
}
