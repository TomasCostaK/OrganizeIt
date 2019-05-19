package com.example.drawer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;


public class Task_Activity extends AppCompatActivity {
    private TextView tvtitle,tvdescription,tvtasks;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        tvtitle = (TextView) findViewById(R.id.cardtitle);
        tvdescription = (TextView) findViewById(R.id.cardDesc);
        tvtasks = (TextView) findViewById(R.id.cardTasks);
        img = (ImageView) findViewById(R.id.cardthumbnail);

        // Receive data
        Intent intent = getIntent();
        String Title = intent.getExtras().getString("Title");
        String Description = intent.getExtras().getString("Description");
        String Tasks = intent.getExtras().getString("Tasks");
        int image = intent.getExtras().getInt("Thumbnail") ;

        // Setting values
        tvtitle.setText(Title);
        tvdescription.setText(Description);
        tvtasks.setText(Tasks);
        img.setImageResource(image);

        //actionbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(Title);

    }
}
