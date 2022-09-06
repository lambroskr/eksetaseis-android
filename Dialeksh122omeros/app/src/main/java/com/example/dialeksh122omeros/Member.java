package com.example.dialeksh122omeros;

import org.json.JSONException;
import org.json.JSONObject;

public class Member {
    private String team_name;
    private String emblem;
    private String players;
    private String images;

    public Member(String team_name, JSONObject json) throws JSONException {
        this.emblem = json.getString("emblem");
        this.players = json.getString("players");
        this.images = json.getString("images");
        this.team_name = team_name;
    }

    public String getTeam_name() {
        return team_name;
    }

    public String getEmblem() {
        return emblem;
    }

    public String getPlayers() {
        return players;
    }

    public String getImages() {
        return images;
    }
}
