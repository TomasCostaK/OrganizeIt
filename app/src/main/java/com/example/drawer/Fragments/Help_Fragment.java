package com.example.drawer.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.drawer.R;

public class Help_Fragment extends Fragment {

    private View v;
    String[] faqs = {"Creating Boards", "Creating Cards", "How to delete Cards", "How to remove Collaborators"};
    ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v =  inflater.inflate(R.layout.fragment_help, container, false);

        listView =  v.findViewById(R.id.list_view_help);
        ListAdapter adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, faqs);
        listView.setAdapter(adapter);

        return v;
    }
}
