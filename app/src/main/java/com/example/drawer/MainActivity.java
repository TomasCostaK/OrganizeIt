package com.example.drawer;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // Drawer Variables
    private String ola;
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;

    // Recycler
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Plus Button

        FloatingActionButton fbtn = (FloatingActionButton) findViewById(R.id.ftbn);
        fbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar.make(v, "Clicked", Snackbar.LENGTH_SHORT);
                snackbar.show();
            }
        });

        // Recycler
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(new Card("Favorite Boards", "Colors", "Finances", "Training"));
        cards.add(new Card("Personal Boards", "House Needs", "Finances", "Training"));
        cards.add(new Card("Design Team", "Colors", "Fonts", "Assets"));

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new Adapter(cards);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
