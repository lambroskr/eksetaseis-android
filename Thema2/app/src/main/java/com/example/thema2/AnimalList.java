package com.example.thema2;

import android.util.Log;

import androidx.annotation.NonNull;

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

public class AnimalList {

    private ArrayList<Animal> animalList = new ArrayList<>();

    public AnimalList(String response) {
        try {
            JSONObject object = new JSONObject(response);







            Iterator<String> keys = object.keys();
            animalList.clear();
            while (keys.hasNext()) {
                String name = keys.next();
                animalList.add(new Animal(name, object.getJSONObject(name)));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Animal> getAnimalList() {
        return animalList;
    }
}
