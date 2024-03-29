package com.example.drawer.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.drawer.Models.Card_Task;
import com.example.drawer.R;
import com.example.drawer.Task_Activity;

import java.util.ArrayList;
import java.util.List;


public class Adapter_Tasks extends RecyclerView.Adapter<Adapter_Tasks.MyViewHolder>{
    private Context mContext ;
    private List<Card_Task> mData;
    private OnCardEditListener onCardEditListener;

    public Adapter_Tasks(Context mContext, List<Card_Task> mData, OnCardEditListener onCardEditListener) {
        this.mContext = mContext;
        this.mData = mData;
        this.onCardEditListener = onCardEditListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_item_task,parent,false);
        return new MyViewHolder(view, onCardEditListener);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.card_title.setText(mData.get(position).getTitle());
        holder.card_description.setText(mData.get(position).getDescription());
        holder.card_tasks.setText(mData.get(position).getTasks());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, Task_Activity.class);

                // passing data to the card activity
                intent.putExtra("Title",mData.get(position).getTitle());
                intent.putExtra("Description",mData.get(position).getDescription());
                intent.putExtra("Tasks",mData.get(position).getTasks());
                intent.putExtra("Thumbnail",mData.get(position).getThumbnail());
                // start the activity
                mContext.startActivity(intent);

            }
        });



    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView card_title;
        TextView card_description;
        ImageView card_thumbnail;
        TextView card_tasks;
        CardView cardView ;
        OnCardEditListener onCardEditListener;

        public MyViewHolder(View itemView, OnCardEditListener onCardEditListener) {
            super(itemView);

            card_title = (TextView) itemView.findViewById(R.id.card_name) ;
            card_description = (TextView) itemView.findViewById(R.id.card_description) ;
            card_thumbnail = (ImageView) itemView.findViewById(R.id.card_img);
            card_tasks = (TextView) itemView.findViewById(R.id.card_tasks) ;
            cardView = (CardView) itemView.findViewById(R.id.cardviewid);

            this.onCardEditListener = onCardEditListener;

            itemView.setOnClickListener(this);

        }

        public void onClick(View view){
            onCardEditListener.editCard(getAdapterPosition());
        }
    }

    public interface OnCardEditListener{
        void editCard(int position);
    }

}
