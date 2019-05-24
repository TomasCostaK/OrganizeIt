package com.example.drawer;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drawer.Models.Card_Task;

import org.w3c.dom.Text;


public class Task_Activity extends AppCompatActivity {
    private TextView tvtitle, tvdescription, tvtasks;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        tvtitle = (TextView) findViewById(R.id.cardtitle);
        tvdescription = (TextView) findViewById(R.id.cardDesc);
        tvtasks = (TextView) findViewById(R.id.cardTasks);

        // Receive data
        Intent intent = getIntent();
        final String Title = intent.getExtras().getString("Title");
        final String Description = intent.getExtras().getString("Description");
        final String Tasks = intent.getExtras().getString("Tasks");

        // Setting values
        tvtitle.setText(Title);
        tvdescription.setText(Description);
        tvtasks.setText(Tasks);

        //actionbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(Title);


        FloatingActionButton fbtn = (FloatingActionButton) findViewById(R.id.ftbn6);
        fbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.AlertDialog.Builder mBuilder = new android.app.AlertDialog.Builder(Task_Activity.this);
                final View mView = getLayoutInflater().inflate(R.layout.editing_card, null);

                EditText cardTitle = (EditText) mView.findViewById(R.id.editcardtitle);
                EditText cardDesc = (EditText) mView.findViewById(R.id.editcarddescription);
                EditText cardTasks = (EditText) mView.findViewById(R.id.editcardtasks);

                cardTitle.setText(Title);
                cardDesc.setText(Description);
                cardTasks.setText(Tasks);

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

                        if (validatecardtitle(tmp1)) {

                            tvtitle.setText(tmp1.getText().toString());
                            tvdescription.setText(tmp2.getText().toString());
                            tvtasks.setText(tmp3.getText().toString());

                            Toast.makeText(Task_Activity.this, "Card edited!", Toast.LENGTH_SHORT).show();
                            creation_dialog.hide();
                        } else {
                            Toast.makeText(Task_Activity.this, "Insert a card title!", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
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
}
