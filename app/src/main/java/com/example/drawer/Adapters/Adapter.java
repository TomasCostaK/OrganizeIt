package com.example.drawer.Adapters;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drawer.Board;
import com.example.drawer.Models.Board_Model_Main;
import com.example.drawer.Models.Card_Model_Main;
import com.example.drawer.R;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ExampleViewHolder> {

    ArrayList<Board_Model_Main> boardArray;
    ArrayList<Card_Model_Main> cardArray = new ArrayList<>();
    Context context;

    ImageView imageView;

    private onItemClickListener mListener;

    public interface onItemClickListener {
        void onItemClick(int position);
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        mListener = listener;
    };

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {

        RecyclerView recyclerView;
        public TextView textView;
        public ImageView imageView;

        public ExampleViewHolder(final View itemView, final onItemClickListener listener) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.favorite);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.recyclerView_card);
            imageView = itemView.findViewById(R.id.delete_board);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) {
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });
        }
    }

    public Adapter(Context context, ArrayList<Board_Model_Main> boards) {
        this.boardArray =  boards;
        this.context = context;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(final ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card, viewGroup, false);
        ExampleViewHolder evh = new ExampleViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(final ExampleViewHolder exampleViewHolder, int i) {
        Board_Model_Main current_board = boardArray.get(i);
        exampleViewHolder.textView.setText(current_board.getTitle());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        exampleViewHolder.recyclerView.setLayoutManager(layoutManager);
        exampleViewHolder.recyclerView.setHasFixedSize(true);
        cardArray.clear();
        Card_Model_Main default_card = new Card_Model_Main();
        default_card.setCard_name("-Welcome Card");
        cardArray.add(default_card);

        final Adapter_Card childAdapter = new Adapter_Card(cardArray);
        exampleViewHolder.recyclerView.setAdapter(childAdapter);
        childAdapter.notifyDataSetChanged();

        childAdapter.setOnItemClickListener(new Adapter_Card.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(context, Board.class);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return boardArray.size();
    }

}
