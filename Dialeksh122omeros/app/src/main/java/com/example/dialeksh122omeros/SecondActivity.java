package com.example.dialeksh122omeros;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();

        String team_name =intent.getStringExtra("team_name");
        String emblem = intent.getStringExtra("emblem");
        String players = intent.getStringExtra("players");
        String images = intent.getStringExtra("images");

        TextView teamnameTV = findViewById(R.id.teamnameTV);
        TextView playersTV = findViewById(R.id.playersTV);
        Button backButton = findViewById(R.id.backbutton);
        ImageView imageview = findViewById(R.id.imageView);

        teamnameTV.setText(team_name);
        playersTV.setText(players);
        Picasso.with(this).load(emblem).into(imageview);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent1);
            }
        });


    }
}