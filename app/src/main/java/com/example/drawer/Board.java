package com.example.drawer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class Board extends AppCompatActivity {
    // Drawer Variables
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tasks);

        //Plus Button

        FloatingActionButton fbtn = (FloatingActionButton) findViewById(R.id.ftbn3);
        fbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar snackbar = Snackbar.make(v, "Going to create task", Snackbar.LENGTH_SHORT);
                snackbar.show();
            }
        });

        // DRAWER
        mDrawerlayout = (DrawerLayout) findViewById(R.id.drawerTasks);
        mToggle = new ActionBarDrawerToggle(this, mDrawerlayout,R.string.open, R.string.close);
        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // END DRAWER

        //ACTION BAR
        getSupportActionBar().setTitle("Design Board");


    }

    public void btnGoSettings(View view){
        startActivity(new Intent(this, BoardMembers.class));
    }

}
