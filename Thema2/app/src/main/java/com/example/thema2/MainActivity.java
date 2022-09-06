package com.example.thema2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    ArrayList<Animal> animalArrayList = new ArrayList<>();
    Animal selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        Button nextButton = findViewById(R.id.button2);

        String url = "http://195.251.211.64/animals/getAnimals.php";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).get().build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.i("Test", e.toString());
            }
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                AnimalList animalList = new AnimalList(response.body().string());
                animalArrayList = animalList.getAnimalList();

                runOnUiThread( ()-> {
                    for (int i=0; i<animalArrayList.size(); i++) {
                        RadioButton rb = new RadioButton(getApplicationContext());
                        rb.setText(animalArrayList.get(i).getName());
                        int finalI = i;
                        rb.setOnClickListener(v->{
                            selected = animalArrayList.get(finalI);
                        });
                        radioGroup.addView(rb);
                    }
                });
            }
        });

        nextButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("name", selected.getName());
            intent.putExtra("image", selected.getImage());
            intent.putExtra("type", selected.getType());
            intent.putExtra("voice", selected.getVoice());
            startActivity(intent);
        });

    }
}