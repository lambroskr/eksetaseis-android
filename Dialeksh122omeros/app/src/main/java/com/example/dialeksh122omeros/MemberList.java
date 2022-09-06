package com.example.dialeksh122omeros;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class MemberList {
    private ArrayList<Member> membersList = new ArrayList<>();

    public MemberList(String response){
        try {
            JSONObject object = new JSONObject(response);



            Iterator<String> keys = object.keys();
            membersList.clear();
            while (keys.hasNext()) {
                String team_name = keys.next();
                membersList.add(new Member(team_name, object.getJSONObject(team_name) ));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    public ArrayList<Member> getMembersList() {
        return membersList;
    }



}
