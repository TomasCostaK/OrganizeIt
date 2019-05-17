package com.example.drawer.Fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drawer.Adapters.Adapter;
import com.example.drawer.Adapters.Adapter_null;
import com.example.drawer.Models.Board_Model_Main;
import com.example.drawer.Models.Card_Model_Main;
import com.example.drawer.R;

import java.util.ArrayList;

public class Board_Fragment extends Fragment {

    private View v;

    // Recycler Boards
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Board_Model_Main> boards = new ArrayList<>();

    // Recycler Cards
    private ArrayList<Card_Model_Main> cards;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_board, container, false);

        buildRecyclerViewNull();

        FloatingActionButton fbtn = (FloatingActionButton) v.findViewById(R.id.ftbn_new);
        fbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Modal para criar boards e teams
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
                View mView = getLayoutInflater().inflate(R.layout.dialog_new_board, null);
                TextView create_board = (TextView) mView.findViewById(R.id.create_board);
                final TextView name_text = (TextView) mView.findViewById(R.id.name_text);
                TextView member_text = (TextView) mView.findViewById(R.id.member_text);
                final EditText name_edit = (EditText) mView.findViewById(R.id.name_edit);
                TextView member_edit = (TextView) mView.findViewById(R.id.member_edit);
                Button cancel_btn = (Button) mView.findViewById(R.id.cancel_btn);
                Button create_btn = (Button) mView.findViewById(R.id.create_btn);
                Button invite_btn = (Button) mView.findViewById(R.id.invite_btn);

                mBuilder.setView(mView);
                final AlertDialog creation_dialog = mBuilder.create();
                creation_dialog.show();

                cancel_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        creation_dialog.hide();
                    }
                });

                invite_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(), "Inivitation Sent", Toast.LENGTH_SHORT).show();
                    }
                });

                create_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        createBoardList(name_edit.getText().toString());
                        buildRecyclerView();
                        notify_adapter();

                        creation_dialog.hide();
                    }
                });
            }
        });

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void buildRecyclerViewNull() {
        mRecyclerView = v.findViewById(R.id.recyclerViewBoards_new);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new Adapter_null();
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void createBoardList(String title) {
        Board_Model_Main new_board = new Board_Model_Main();
        new_board.setTitle(title);
        insertBoard(new_board);

    }
    // recycler view boards
    public void buildRecyclerView() {
        mRecyclerView = v.findViewById(R.id.recyclerViewBoards_new);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new Adapter(getActivity(), boards);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    // create new board
    public void insertBoard(Board_Model_Main board) {
        boards.add(board);
    }

    // create new card
    public void insertCard(Card_Model_Main card) {
        cards.add(card);
    }

    public void notify_adapter() {
        mAdapter.notifyDataSetChanged();
    }
}
