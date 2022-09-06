package com.example.dialeksh122omeros;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        Button nextButton = findViewById(R.id.nextBtn);

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
                Log.e("members", String.valueOf(memberArrayList.get(0).getTeam_name()));

                runOnUiThread( ()-> {
                    for (int i=0; i<memberArrayList.size(); i++) {
                        RadioButton rb = new RadioButton(getApplicationContext());
                        rb.setText(memberArrayList.get(i).getTeam_name());

                        int finalI = i;
                        rb.setOnClickListener(v->{
                            selected = memberArrayList.get(finalI);
                        });
                        radioGroup.addView(rb);
                    }
                });
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
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