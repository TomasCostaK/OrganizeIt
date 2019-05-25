package com.example.drawer.Adapters;
/*
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.drawer.Models.Card_Model_Main;
import com.example.drawer.R;

import java.util.ArrayList;

public class Adapter_Card  extends RecyclerView.Adapter<Adapter.ExampleViewHolder> {

    private ArrayList<Card_Model_Main> cardArray;
    private Context context;

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {


        public TextView textView;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.card1);
        }
    }

    public Adapter_Card(Context context, ArrayList<Card_Model_Main> cards) {
        this.cardArray =  cards;
        this.context = context;
    }

    @Override
    public Adapter.ExampleViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cards_list_recycler, viewGroup, false);
        Adapter.ExampleViewHolder evh = new Adapter.ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(Adapter.ExampleViewHolder exampleViewHolder, int i) {
        Card_Model_Main current_card = cardArray.get(i);
        exampleViewHolder.textView.setText(current_card.getCard_name());

    }

    @Override
    public int getItemCount() {
        return 1;
    }
}

*/


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.drawer.Models.Card_Model_Main;
import com.example.drawer.R;

import java.util.ArrayList;

public class Adapter_Card extends RecyclerView.Adapter<Adapter_Card.MyViewHolder>  {

    ArrayList<Card_Model_Main> arrayList;

    private onItemClickListener mListener;

    public interface onItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        mListener = listener;
    };

    public Adapter_Card(ArrayList<Card_Model_Main> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cards_list_recycler, viewGroup, false);
        return new MyViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.card.setText(arrayList.get(i).getCard_name());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView card;

        public MyViewHolder(View itemView, final onItemClickListener listener) {
            super(itemView);
            this.card = itemView.findViewById(R.id.card1);

            itemView.setOnClickListener( new View.OnClickListener(){
                public void onClick(View v) {
                    if(listener != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

}