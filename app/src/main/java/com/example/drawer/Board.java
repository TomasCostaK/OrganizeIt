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
import android.util.Log;
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

public class Board extends AppCompatActivity implements Adapter_Tasks.OnCardEditListener {

    List<Card_Task> lstCards = new ArrayList<>();;
    Adapter_Tasks myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tasks);

        lstCards.add(new Card_Task("Welcome Card", "This card was generated automatically to help you navigate the app!","Edit this card\nAdd Members\nCreate another board\nDelete this card", R.drawable.mountain));

        RecyclerView myrv = (RecyclerView) findViewById(R.id.recyclercards);
        myAdapter = new Adapter_Tasks(this, lstCards,this);
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
                        if(validatecardtitle(card_edit)) {
                            //Criar nova card
                            createCard(card_edit.getText().toString(), description_edit.getText().toString(), tasks_edit.getText().toString());
                            myAdapter.notifyDataSetChanged();

                            creation_dialog.hide();
                        }
                        else{
                            Toast.makeText(Board.this, "The Card Title can't be empty!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    private void createCard(String name, String desc, String tasks) {
                        lstCards.add(new Card_Task(name, desc, tasks, R.drawable.mountain));
                    }
                });
            }
        });


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
                Toast.makeText(Board.this, "Card deleted!", Toast.LENGTH_SHORT).show();
                creation_dialog.hide();
            }

            private void deleteInstanceCard(View v) {
                lstCards.remove(0);
                myAdapter.notifyDataSetChanged();
            }
        });
    }

    public void editCard(View view){
        android.app.AlertDialog.Builder mBuilder = new android.app.AlertDialog.Builder(Board.this);
        final View mView = getLayoutInflater().inflate(R.layout.editing_card, null);

        final Card_Task c1 = lstCards.get(0);

        EditText cardTitle = (EditText) mView.findViewById(R.id.editcardtitle);
        EditText cardDesc = (EditText) mView.findViewById(R.id.editcarddescription);
        EditText cardTasks = (EditText) mView.findViewById(R.id.editcardtasks);

        cardTitle.setText(c1.getTitle());
        cardDesc.setText(c1.getDescription());
        cardTasks.setText(c1.getTasks());

        Button cancel_btn = (Button) mView.findViewById(R.id.cancel_card_btn);
        Button editcard_btn = (Button) mView.findViewById(R.id.editcard_btn);

        mBuilder.setView(mView);
        final AlertDialog creation_dialog = mBuilder.create();
        creation_dialog.show();



        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creation_dialog.hide();
            }
        });
        editcard_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //editar card
                final EditText tmp1 = (EditText) mView.findViewById(R.id.editcardtitle);
                final EditText tmp2 = (EditText) mView.findViewById(R.id.editcarddescription);
                final EditText tmp3 = (EditText) mView.findViewById(R.id.editcardtasks);

                if(validatecardtitle(tmp1)){
                    final Card_Task c1 = lstCards.get(0);

                    c1.setTitle(tmp1.getText().toString());
                    c1.setDescription(tmp2.getText().toString());
                    c1.setTasks(tmp3.getText().toString());

                    myAdapter.notifyDataSetChanged();
                    Toast.makeText(Board.this, "Card edited!", Toast.LENGTH_SHORT).show();
                    creation_dialog.hide();
                }
                else{
                    Toast.makeText(Board.this, "Insert a card title!", Toast.LENGTH_SHORT).show();
                }

        }
    });
    }

    private boolean validatecardtitle(EditText tmp1) {
        String a = tmp1.getText().toString().trim();
        if (a.isEmpty()){
            return false;
        }
        return true;
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
        final Intent actv = new Intent(this, NewMainActivity.class);
        android.app.AlertDialog.Builder mBuilder = new android.app.AlertDialog.Builder(Board.this);
        View mView = getLayoutInflater().inflate(R.layout.deleting_board, null);

        final TextView card1 = (TextView) mView.findViewById(R.id.deleteboardtitle);
        final TextView card2 = (TextView) mView.findViewById(R.id.deleteboardtext);

        Button cancel_btn = (Button) mView.findViewById(R.id.cancel_btn);
        Button delete_btn = (Button) mView.findViewById(R.id.deleteboard_btn);

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
                deleteInstanceBoard(v);
                Toast.makeText(Board.this, "Board apagada com sucesso", Toast.LENGTH_SHORT).show();
                startActivity(actv);
            }

            private void deleteInstanceBoard(View v) {
                lstCards.remove(v);
            }
        });
    }


    @Override
    public void editCard(int position) {

    }
}
