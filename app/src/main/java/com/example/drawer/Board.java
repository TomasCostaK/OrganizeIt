package com.example.drawer;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drawer.Adapters.Adapter_Tasks;
import com.example.drawer.Models.Board_Model_Main;
import com.example.drawer.Models.Card_Task;

import java.util.ArrayList;
import java.util.List;

public class Board extends AppCompatActivity {

    List<Card_Task> lstCards ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tasks);

        lstCards = new ArrayList<>();
        lstCards.add(new Card_Task("Colors","Usar este guia visual",R.drawable.mountain));
        lstCards.add(new Card_Task("Compras","Comprar estas coisas",R.drawable.mountain));
        lstCards.add(new Card_Task("Projetos","Projetos Universidade",R.drawable.mountain));
        lstCards.add(new Card_Task("Colors","Esta descriçao tem que ter um tamanho incrivelmente grande para testar o container da descriçao e ver se desformata a card",R.drawable.mountain));
        lstCards.add(new Card_Task("Compras","Comprar estas coisas",R.drawable.mountain));



        RecyclerView myrv = (RecyclerView) findViewById(R.id.recyclercards);
        Adapter_Tasks myAdapter = new Adapter_Tasks(this,lstCards);
        myrv.setLayoutManager(new GridLayoutManager(this,2));
        myrv.setAdapter(myAdapter);

        //Plus Button

        FloatingActionButton fbtn = (FloatingActionButton) findViewById(R.id.ftbn3);
        fbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Modal para criar boards e teams
                android.app.AlertDialog.Builder mBuilder = new android.app.AlertDialog.Builder(Board.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_new_board, null);
                TextView create_board = (TextView) mView.findViewById(R.id.create_board);
                final TextView name_text = (TextView) mView.findViewById(R.id.name_text);
                TextView member_text = (TextView) mView.findViewById(R.id.member_text);
                final EditText name_edit = (EditText) mView.findViewById(R.id.name_edit);
                TextView member_edit = (TextView) mView.findViewById(R.id.member_edit);
                Button cancel_btn = (Button) mView.findViewById(R.id.cancel_btn);
                Button create_btn = (Button) mView.findViewById(R.id.create_btn);


                mBuilder.setView(mView);
                final AlertDialog creation_dialog = mBuilder.create();
                creation_dialog.show();

                cancel_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        creation_dialog.hide();
                    }
                });

                create_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        lstCards.add(new Card_Task("Projetos","Projetos Universidade",R.drawable.mountain));
                        //createBoardList(name_edit.getText().toString());
                        //buildRecyclerView();
                        //notify_adapter();

                        creation_dialog.hide();
                    }

                    private void createBoardList(String toString) {
                    }
                });
            }
        });

        //ACTION BAR
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Personal Board");

    }

    public void btnGoSettings(View view){
        startActivity(new Intent(this, BoardMembers.class));
    }

    public void insertItem(Card_Task card){
        lstCards.add(card);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        ((MenuInflater) inflater).inflate(R.menu.drawertasks, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_settings:
                startActivity(new Intent(this, BoardMembers.class));
                return true;
            case R.id.menu_share:
                Toast.makeText(this,"Send your friends this link: OrganizeIt/I3nazx45",Toast.LENGTH_LONG).show();
                return true;
            case R.id.menu_delete:
                confirmDelete();
                return true;
            case R.id.home:
                this.finish();
                return super.onOptionsItemSelected(item);
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void confirmDelete() {
        android.app.AlertDialog.Builder mBuilder = new android.app.AlertDialog.Builder(Board.this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_new_board, null);
        TextView create_board = (TextView) mView.findViewById(R.id.create_board);
        final TextView name_text = (TextView) mView.findViewById(R.id.name_text);
        TextView member_text = (TextView) mView.findViewById(R.id.member_text);
        final EditText name_edit = (EditText) mView.findViewById(R.id.name_edit);
        TextView member_edit = (TextView) mView.findViewById(R.id.member_edit);
        Button cancel_btn = (Button) mView.findViewById(R.id.cancel_btn);
        Button create_btn = (Button) mView.findViewById(R.id.create_btn);

        mBuilder.setView(mView);
        final AlertDialog creation_dialog = mBuilder.create();
        creation_dialog.show();
    }
}
