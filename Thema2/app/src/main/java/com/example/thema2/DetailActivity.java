package com.example.thema2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String image = intent.getStringExtra("image");
        String type = intent.getStringExtra("type");
        String voice = intent.getStringExtra("voice");

        TextView textView = findViewById(R.id.textView);
        textView.setText("My name is "+name+", I am a "+type+" and I "+voice);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(v->{
            Intent intent1 = new Intent(this, MainActivity.class);
            startActivity(intent1);
        });

        ImageView imageView = findViewById(R.id.imageView);

        Picasso.with(this).load(image).into(imageView);
    }
}