package com.example.andreavalenziano.samplejsonparser.models;

import android.content.ContentValues;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by AndreaValenziano on 27/02/17.
 */

public class Student {

    private static final String NAME_KEY = "nome";
    private static final String EMAIL_KEY = "email";
    private static final String GITHUB_KEY = "github";
    private static final String AVATAR_KEY = "avatar";


    String name, email, github, avatar;


    public Student(JSONObject jsonStudent) {
        try {
            name = jsonStudent.getString(NAME_KEY);
            email = jsonStudent.getString(EMAIL_KEY);
            github = buildGithubUrl(jsonStudent.optString(GITHUB_KEY,""));
            avatar = jsonStudent.optString(AVATAR_KEY);

            Log.d(ContentValues.TAG, "NAME: "+name);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }



    private String buildGithubUrl(String username){
        username = username.replace("@","");
        return "https://github.com/" + username;
    }

}