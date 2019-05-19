package com.example.drawer;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BoardMembers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_members);

        //ACTION BAR
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Board Settings");
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) //end the activity
            this.finish();

        return super.onOptionsItemSelected(item);
    }

    public void changeBoardName(View view) {
        EditText boardtext = findViewById(R.id.board_text);
        //Aceder ao card para poder dar setTitle()
    }

    public void changeBoardDesc(View view) {
        EditText desctext = findViewById(R.id.desc_text);
        //Aceder ao card para poder dar setDescription()
    }

    public void deleteCollab(View view) {
            android.app.AlertDialog.Builder mBuilder = new android.app.AlertDialog.Builder(BoardMembers.this);
            View mView = getLayoutInflater().inflate(R.layout.deleting_collaborators, null);

            final TextView card1 = (TextView) mView.findViewById(R.id.deletecollabtitle);
            final TextView card2 = (TextView) mView.findViewById(R.id.deleteCollabText);

            Button cancel_btn = (Button) mView.findViewById(R.id.cancel_btn);
            Button delete_btn = (Button) mView.findViewById(R.id.deleteCollab_btn);

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
                    //Delete member
                    deleteInstanceCard(v);
                    creation_dialog.hide();
                }

                private void deleteInstanceCard(View v) {
                    Toast.makeText(BoardMembers.this, "Member deleted", Toast.LENGTH_SHORT).show();
                }
            });
    }
}
