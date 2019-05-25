package com.example.drawer.Fragments;

import android.app.AlertDialog;
import android.content.Intent;
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
import com.example.drawer.Board;
import com.example.drawer.Models.Board_Model_Main;
import com.example.drawer.Models.Card_Model_Main;
import com.example.drawer.R;

import java.util.ArrayList;
import java.util.Arrays;

public class Board_Fragment extends Fragment {

    private View v;

    // Recycler Boards
    private RecyclerView mRecyclerView;
    private Adapter_null mAdapter;
    private Adapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Board_Model_Main> boards = new ArrayList<>();
    private ArrayList<String> boards_created = new ArrayList<>();

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
                createBoard();
            }
        });

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void createBoard() {
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
                Toast.makeText(getActivity(), "The user will collaborate in the board!", Toast.LENGTH_LONG).show();
            }
        });

        create_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(name_edit.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getActivity(), "Board name can't be empty!", Toast.LENGTH_SHORT).show();
                }
                else if(boards_created.contains(name_edit.getText().toString().trim())) {
                    Toast.makeText(getActivity(), "A board with that name already exists!", Toast.LENGTH_SHORT).show();
                }
                else {
                    createBoardList(name_edit.getText().toString());
                    buildRecyclerView();
                    notify_adapter();
                    boards_created.add(name_edit.getText().toString().trim());

                    creation_dialog.hide();
                }
            }
        });
    }

    public void buildRecyclerViewNull() {
        mRecyclerView = v.findViewById(R.id.recyclerViewBoards_new);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new Adapter_null(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new Adapter_null.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                createBoard();
            }

        });

    }

    public void createBoardList(String title) {
        Board_Model_Main new_board = new Board_Model_Main();
        new_board.setTitle(title);
        boards.add(new_board);
    }

    // recycler view boards
    public void buildRecyclerView() {
        mRecyclerView = v.findViewById(R.id.recyclerViewBoards_new);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        adapter = new Adapter(getActivity(), boards);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new Adapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                boards.get(position);
                Intent intent = new Intent(getContext(), Board.class);
                startActivity(intent);
            }

            @Override
            public void onDeleteClick(int position) {
                deleteBoard(position);
            }
        });

    }

    // create new card
    public void insertCard(Card_Model_Main card) {
        cards.add(card);
    }

    public void notify_adapter() {
        mAdapter.notifyDataSetChanged();
    }

    public void removeItem(int position) {
        boards.remove(position);
    }

    public void deleteBoard(final int position) {
        AlertDialog.Builder deleteBuilder = new AlertDialog.Builder(getActivity());
        View deleteView = getLayoutInflater().inflate(R.layout.deleting_board, null);

        final TextView card1 = (TextView) deleteView.findViewById(R.id.deleteboardtitle);
        final TextView card2 = (TextView) deleteView.findViewById(R.id.deleteboardtext);

        Button cancel_btn = (Button) deleteView.findViewById(R.id.cancel_btn);
        Button delete_btn = (Button) deleteView.findViewById(R.id.deleteboard_btn);

        deleteBuilder.setView(deleteView);

        final AlertDialog delete_dialog = deleteBuilder.create();
        delete_dialog.show();

        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete_dialog.hide();
            }
        });

        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItem(position);
                adapter.notifyDataSetChanged();
                delete_dialog.hide();
                Toast.makeText(getContext(), "Board apagada com sucesso", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
