package com.example.andreavalenziano.samplejsonparser;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by AndreaValenziano on 27/02/17.
 */

public class Course {

    private String id;
    private String name;

    private static final String ID_KEY="id";
    private static final String NAME_KEY="nome";



    public Course(JSONObject jsonObject){
        try {
            this.id=jsonObject.getString(ID_KEY);
            this.name=jsonObject.getString(NAME_KEY);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
