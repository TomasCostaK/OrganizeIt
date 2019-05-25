package com.example.drawer.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.drawer.R;

public class Adapter_null extends RecyclerView.Adapter<Adapter_null.ExampleViewHolder> {

    Context context;

    private Adapter_null.onItemClickListener mListener;

    public interface onItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(Adapter_null.onItemClickListener listener) {
        mListener = listener;
    };

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {


        public TextView textView1;
        public ImageView image;
        public TextView textView2;

        public ExampleViewHolder(View itemView, final onItemClickListener mListener) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.no_board_1);
            image = itemView.findViewById(R.id.no_board_im);
            textView2 = itemView.findViewById(R.id.no_board_2);

            itemView.setOnClickListener( new View.OnClickListener(){
                public void onClick(View v) {
                    if(mListener != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }

    public Adapter_null(Context context) {
        this.context = context;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.no_boards_defined, viewGroup, false);

        return new ExampleViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder exampleViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }
}
