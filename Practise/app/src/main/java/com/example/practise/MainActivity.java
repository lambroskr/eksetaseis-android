package com.example.practise;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    ArrayList<Member> memberArrayList = new ArrayList<>();
    Member selected;
    ArrayList<String> team_namesArraylist = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button showButton = findViewById(R.id.showBtn);
        Spinner spinner = findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, team_namesArraylist);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        String url ="http://192.168.2.3/matches/getMembers.php";

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).get().build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.i("Test", e.toString());
            }
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                MemberList memberList = new MemberList(response.body().string());
                memberArrayList = memberList.getMembersList();
                for(int i=0; i < memberArrayList.size(); i++){
                    team_namesArraylist.add( memberArrayList.get(i).getTeam_name());
                }
                Log.e("members", String.valueOf(team_namesArraylist.get(0)));


                runOnUiThread( ()-> {
                    adapter.notifyDataSetChanged();
                });
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selected = memberArrayList.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("team_name", selected.getTeam_name());
                intent.putExtra("emblem", selected.getEmblem());
                intent.putExtra("players", selected.getPlayers());
                intent.putExtra("images", selected.getImages());
                startActivity(intent);
            }
        });





    }
}