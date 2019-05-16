package com.example.drawer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.drawer.Adapters.Adapter_Tasks;
import com.example.drawer.Models.Board_Model_Main;
import com.example.drawer.Models.Card_Task;

import java.util.ArrayList;
import java.util.List;

public class Board extends AppCompatActivity {
    // Drawer Variables
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;

    List<Card_Task> lstCards ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tasks);

        lstCards = new ArrayList<>();
        lstCards.add(new Card_Task("Colors","Usar este guia visual",R.drawable.mountain));
        lstCards.add(new Card_Task("Compras","Comprar estas coisas",R.drawable.mountain));
        lstCards.add(new Card_Task("Projetos","Projetos Universidade",R.drawable.mountain));
        lstCards.add(new Card_Task("Colors","Usar este guia visual",R.drawable.mountain));
        lstCards.add(new Card_Task("Compras","Comprar estas coisas",R.drawable.mountain));



        RecyclerView myrv = (RecyclerView) findViewById(R.id.recyclercards);
        Adapter_Tasks myAdapter = new Adapter_Tasks(this,lstCards);
        myrv.setLayoutManager(new GridLayoutManager(this,2));
        myrv.setAdapter(myAdapter);

        //Plus Button

        FloatingActionButton fbtn = (FloatingActionButton) findViewById(R.id.ftbn3);
        /*fbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Modal para criar boards e teams
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Board.this);
                View mView = getLayoutInflater().inflate(R.layout.creating_card, null);

                TextView create_board = (TextView) mView.findViewById(R.id.creating_text);

                final TextView name_text = (TextView) mView.findViewById(R.id.card_text);
                final EditText name_edit = (EditText) mView.findViewById(R.id.card_edit);

                TextView member_text = (TextView) mView.findViewById(R.id.description_text);
                final EditText member_edit = (EditText) mView.findViewById(R.id.description_edit);

                Button cancel_btn = (Button) mView.findViewById(R.id.cancel_btn);
                Button create_btn = (Button) mView.findViewById(R.id.card_btn);

                mBuilder.setView(mView);
                final AlertDialog creation_dialog = mBuilder.create();
                creation_dialog.show();

                cancel_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        creation_dialog.hide();
                    }
                });

                //Fazer isto para criar nova task
                create_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        insertItem(new Board_Model_Main(name_edit.getText().toString(), "+ Add card", "+ Add card", "+ Add card"));
                        creation_dialog.hide();
                    }
                });
            }
        });
*/
        // DRAWER
        mDrawerlayout = (DrawerLayout) findViewById(R.id.drawer_tasks);
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
