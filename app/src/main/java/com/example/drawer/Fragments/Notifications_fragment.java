package com.example.drawer.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.drawer.R;

public class Notifications_fragment extends Fragment {


    View v;
    SwitchCompat notf_inv, notf_dead, notf_colb;

    Button notf_off;

    boolean state_notf_inv, state_notf_dead, state_notf_colb;

    SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_notifications, container, false);

        sharedPreferences = getContext().getSharedPreferences("PREFS", 0);
        state_notf_inv = sharedPreferences.getBoolean("switch1", false);
        state_notf_dead = sharedPreferences.getBoolean("switch2", false);
        state_notf_colb = sharedPreferences.getBoolean("switch3", false);

        notf_inv = (SwitchCompat) v.findViewById(R.id.notf_inv);
        notf_dead = (SwitchCompat) v.findViewById(R.id.notf_dead);
        notf_colb = (SwitchCompat) v.findViewById(R.id.notf_colb);
        notf_off = (Button) v.findViewById(R.id.notf_off);

        notf_inv.setChecked(state_notf_inv);
        notf_dead.setChecked(state_notf_dead);
        notf_colb.setChecked(state_notf_colb);

        notf_inv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                state_notf_inv = !state_notf_inv;
                notf_inv.setChecked(state_notf_inv);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("switch1", state_notf_inv);
                editor.apply();
            }
        });

        notf_dead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                state_notf_dead = !state_notf_dead;
                notf_dead.setChecked(state_notf_dead);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("switch2", state_notf_dead);
                editor.apply();
            }
        });

        notf_colb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                state_notf_colb = !state_notf_colb;
                notf_colb.setChecked(state_notf_colb);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("switch3", state_notf_inv);
                editor.apply();
            }
        });

        notf_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(state_notf_inv) {
                    notf_inv.setChecked(false);
                    state_notf_inv = !state_notf_inv;
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("switch1", state_notf_inv);
                    editor.apply();
                }

                if(state_notf_dead) {
                    notf_dead.setChecked(false);
                    state_notf_dead = !state_notf_dead;
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("switch2", state_notf_dead);
                    editor.apply();
                }

                if(state_notf_colb) {
                    notf_colb.setChecked(false);
                    state_notf_colb = !state_notf_colb;
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("switch3", state_notf_colb);
                    editor.apply();
                }

            }
        });

        return v;
    }



}
