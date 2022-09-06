package com.example.thema2;

import org.json.JSONException;
import org.json.JSONObject;

public class Animal {

    private String name;
    private String type;
    private String voice;
    private String image;

    public Animal(String name, String type, String voice, String image) {
        this.name = name;
        this.type = type;
        this.voice = voice;
        this.image = image;
    }

    public Animal(String name, JSONObject json) throws JSONException {
        this.name = name;
        this.type = json.getString("type");
        this.voice = json.getString("voice");
        this.image = json.getString("image");
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getVoice() {
        return voice;
    }

    public String getImage() {
        return image;
    }
}
