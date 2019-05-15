package com.example.drawer;

import android.app.AlertDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drawer.Adapters.Adapter;
import com.example.drawer.Adapters.Adapter_null;
import com.example.drawer.Models.Board_Model_Main;
import com.example.drawer.Models.Card_Model_Main;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // Recycler Reset control variable
    private boolean control_recycler = true;

    // Drawer Variables
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;

    // Recycler Boards
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Board_Model_Main> boards = new ArrayList<>();

    // Recycler Cards
    private ArrayList<Card_Model_Main> cards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boards = new ArrayList<>();
        //Plus Button

        FloatingActionButton fbtn = (FloatingActionButton) findViewById(R.id.ftbn);
        fbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Modal para criar boards e teams
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
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
                        Toast.makeText(MainActivity.this, "Inivitation Sent", Toast.LENGTH_SHORT).show();
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

        // Recycler
        buildRecyclerViewNull();

        // DRAWER
        mDrawerlayout = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout,R.string.open, R.string.close);
        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // END DRAWER

        //ACTION BAR
        getSupportActionBar().setTitle("OrganizeIt");

        //Favorite List view
        /*ListView fav_view = (ListView) findViewById(R.id.fav_view);
        ArrayList<String> fav_boards = new ArrayList<>();
        fav_boards.add("Colors");
        fav_boards.add("Finances");
        fav_boards.add("Training");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, fav_boards);
        fav_view.setAdapter(arrayAdapter);
        */

    }
    // List of boards
    public void createBoardList(String title) {
        Board_Model_Main new_board = new Board_Model_Main();
        new_board.setTitle(title);
        insertBoard(new_board);

    }
    // recycler view boards
    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerViewBoards);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new Adapter(this, boards);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
    // initial recycler view
    public void buildRecyclerViewNull() {
        mRecyclerView = findViewById(R.id.recyclerViewBoards);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new Adapter_null();
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
