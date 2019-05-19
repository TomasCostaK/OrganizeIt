package com.example.drawer.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.drawer.Adapters.Adapter;
import com.example.drawer.R;

public class Adapter_null extends RecyclerView.Adapter<Adapter.ExampleViewHolder> {

    Context context;

    private Adapter.onItemClickListener mListener;

    public interface onItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(Adapter.onItemClickListener listener) {
        mListener = listener;
    };

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {


        public TextView textView1;
        public ImageView image;
        public TextView textView2;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.no_board_1);
            image = itemView.findViewById(R.id.no_board_im);
            textView2 = itemView.findViewById(R.id.no_board_2);

        }
    }

    public Adapter_null(Context context) {
        this.context = context;
    }

    @Override
    public Adapter.ExampleViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.no_boards_defined, viewGroup, false);
        Adapter.ExampleViewHolder evh = new Adapter.ExampleViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(Adapter.ExampleViewHolder exampleViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }
}
