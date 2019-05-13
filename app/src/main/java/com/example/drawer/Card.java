package com.example.drawer;

public class Card {
    private String mText;
    private String mItem1;
    private String mItem2;
    private String mItem3;

    public Card(String text, String item1, String item2, String item3) {
        mText = text;
        mItem1 = item1;
        mItem2 = item2;
        mItem3 = item3;

    }

    public Card(String text) {
        mText = text;
        mItem1 = null;
        mItem2 = null;
        mItem3 = null;
    }

    public String getmText() {
        return mText;
    }

    public String getmItem1() {
        return mItem1;
    }
    public String getmItem2() {
        return mItem2;
    }
    public String getmItem3() {
        return mItem3;
    }
}
