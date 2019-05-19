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
        lstCards.add(new Card_Task("Colors", "Usar este guia visual","Amarelo e cinzento\nFont montserrat\nComida", R.drawable.mountain));
        lstCards.add(new Card_Task("Compras", "Comprar estas coisas","atum\nmassa\nCenas", R.drawable.mountain));
        lstCards.add(new Card_Task("Projetos", "Projetos Universidade","IHC\nBD", R.drawable.mountain));
        lstCards.add(new Card_Task("Cenas", "Esta descriçao tem que ter um tamanho incrivelmente grande para testar o container da descriçao e ver se desformata a card","Amarelo e cinzento\n Font montserrat\n Comida", R.drawable.mountain));
        lstCards.add(new Card_Task("Coisas", "Comprar estas coisas","IHC\nBD", R.drawable.mountain));

        RecyclerView myrv = (RecyclerView) findViewById(R.id.recyclercards);
        final Adapter_Tasks myAdapter = new Adapter_Tasks(this, lstCards);
        myrv.setLayoutManager(new GridLayoutManager(this, 2));
        myrv.setAdapter(myAdapter);


        //Plus Button
        FloatingActionButton fbtn = (FloatingActionButton) findViewById(R.id.ftbn3);
        fbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Modal para criar boards e teams
                android.app.AlertDialog.Builder mBuilder = new android.app.AlertDialog.Builder(Board.this);
                View mView = getLayoutInflater().inflate(R.layout.creating_card, null);
                TextView create_card = (TextView) mView.findViewById(R.id.creating_text);

                final TextView card_text = (TextView) mView.findViewById(R.id.card_text);
                final EditText card_edit = (EditText) mView.findViewById(R.id.card_edit);

                TextView description_text = (TextView) mView.findViewById(R.id.description_text);
                final EditText description_edit = (EditText) mView.findViewById(R.id.description_edit);

                TextView tasks_text = (TextView) mView.findViewById(R.id.tasks_text);
                final EditText tasks_edit = (EditText) mView.findViewById(R.id.tasks_edit);

                Button cancel_btn = (Button) mView.findViewById(R.id.cancel_card_btn);
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

                create_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Criar nova card
                        createCard(card_edit.getText().toString(), description_edit.getText().toString(),tasks_edit.getText().toString());
                        myAdapter.notifyDataSetChanged();

                        creation_dialog.hide();
                    }

                    private void createCard(String name, String desc, String tasks) {
                        lstCards.add(new Card_Task(name, desc, tasks, R.drawable.mountain));
                    }
                });
            }
        });

        //

        //ACTION BAR
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Personal Board");

    }

    public void deleteCard(View view){
        android.app.AlertDialog.Builder mBuilder = new android.app.AlertDialog.Builder(Board.this);
        View mView = getLayoutInflater().inflate(R.layout.deleting_card, null);

        final TextView card1 = (TextView) mView.findViewById(R.id.deletecardtitle);
        final TextView card2 = (TextView) mView.findViewById(R.id.deletecardtext);

        Button cancel_btn = (Button) mView.findViewById(R.id.cancel_btn);
        Button delete_btn = (Button) mView.findViewById(R.id.deletecard_btn);

        mBuilder.setView(mView);
        final AlertDialog creation_dialog = mBuilder.create();
        creation_dialog.show();

        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creation_dialog.hide();
            }
        });

        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Criar nova card
                deleteInstanceCard(v);
                creation_dialog.hide();
            }

            private void deleteInstanceCard(View v) {
                lstCards.remove(v);
            }
        });
    }

    public void editCard(View view){
        android.app.AlertDialog.Builder mBuilder = new android.app.AlertDialog.Builder(Board.this);
        View mView = getLayoutInflater().inflate(R.layout.creating_card, null);
        TextView create_card = (TextView) mView.findViewById(R.id.creating_text);

        final TextView card_text = (TextView) mView.findViewById(R.id.card_text);
        final EditText card_edit = (EditText) mView.findViewById(R.id.card_edit);

        TextView description_text = (TextView) mView.findViewById(R.id.description_text);
        final EditText description_edit = (EditText) mView.findViewById(R.id.description_edit);

        TextView tasks_text = (TextView) mView.findViewById(R.id.tasks_text);
        final EditText tasks_edit = (EditText) mView.findViewById(R.id.tasks_edit);

        Button cancel_btn = (Button) mView.findViewById(R.id.cancel_card_btn);
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
                //Criar nova card
                createCard(card_edit.getText().toString(), description_edit.getText().toString(), tasks_edit.getText().toString());
                creation_dialog.hide();
            }

            private void createCard(String name, String desc, String tasks) {
                lstCards.add(new Card_Task(name, desc, tasks, R.drawable.mountain));
            }
        });
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
