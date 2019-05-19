package com.example.drawer;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.drawer.Fragments.Account_fragment;
import com.example.drawer.Fragments.Board_Fragment;
import com.example.drawer.Fragments.Help_Fragment;
import com.example.drawer.Fragments.Notifications_fragment;
import com.example.drawer.Fragments.Stats_fragment;

public class NewMainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_main);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view_new);
        navigationView.setNavigationItemSelectedListener(this);

        toggle = new ActionBarDrawerToggle(this, drawer,
                R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Boards");

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_new,
                    new Board_Fragment()).commit();
            navigationView.setCheckedItem(R.id.new_board_dr);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.new_board_dr:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_new,
                        new Board_Fragment()).commit();
                getSupportActionBar().setTitle("Boards");
                break;

            case R.id.new_stats_dr:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_new,
                        new Stats_fragment()).commit();
                break;

            case R.id.new_account_dr:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_new,
                        new Account_fragment()).commit();
                getSupportActionBar().setTitle("Account Settings");
                break;

            case R.id.new_notf_dr:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_new,
                        new Notifications_fragment()).commit();
                getSupportActionBar().setTitle("Notifications Settings");
                break;

            case R.id.new_help_dr:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container_new,
                        new Help_Fragment()).commit();
                getSupportActionBar().setTitle("Help");
                break;

            case R.id.new_logout_dr:
                Intent logout = new Intent(NewMainActivity.this, Login.class);
                startActivity(logout);
                break;
        }

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
