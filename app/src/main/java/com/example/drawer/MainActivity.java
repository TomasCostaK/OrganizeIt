package com.example.drawer;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drawer.Adapters.Adapter;
import com.example.drawer.Adapters.Adapter_null;
import com.example.drawer.Fragments.Account_fragment;
import com.example.drawer.Fragments.Help_Fragment;
import com.example.drawer.Fragments.Notifications_fragment;
import com.example.drawer.Fragments.Stats_fragment;
import com.example.drawer.Models.Board_Model_Main;
import com.example.drawer.Models.Card_Model_Main;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    // Recycler Reset control variable
    private boolean control_recycler = true;

    // Drawer Variables
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;

    //Relative layout main
    RelativeLayout relativeLayout;

    // Frame Layout
    FrameLayout frameLayout;


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
        relativeLayout = (RelativeLayout) findViewById(R.id.main_page);
        frameLayout = findViewById(R.id.fragment_container);

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

        //Navigation View
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {

            case R.id.board_dr:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Stats_fragment()).commit();
            
            case R.id.stats_dr:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Stats_fragment()).commit();
                break;

            case R.id.account_dr:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Account_fragment()).commit();
                break;

            case R.id.notf_dr:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Notifications_fragment()).commit();
                break;

            case R.id.help_dr:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Help_Fragment()).commit();
                break;

            case R.id.logout_dr:
                Intent logout = new Intent(MainActivity.this, Login.class);
                startActivity(logout);
                break;
        }

        mDrawerlayout.closeDrawer(GravityCompat.START);

        return true;
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
