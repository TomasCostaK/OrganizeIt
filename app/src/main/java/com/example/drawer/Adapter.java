package com.example.drawer;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ExampleViewHolder> {

    private ArrayList<Card> cardArrayList;

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {


        public TextView textView;
        public TextView item1;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.favorite);
            item1 = itemView.findViewById(R.id.item1);
        }
    }

    public Adapter(ArrayList<Card> cards) {
        cardArrayList =  cards;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card, viewGroup, false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder exampleViewHolder, int i) {
        Card current_card = cardArrayList.get(i);

        exampleViewHolder.textView.setText(current_card.getmText());
        exampleViewHolder.item1.setText(current_card.getmItem1());
    }

    @Override
    public int getItemCount() {
        return cardArrayList.size();
    }
}
