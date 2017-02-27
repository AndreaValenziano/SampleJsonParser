package com.example.andreavalenziano.samplejsonparser;

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
    private static final String COURSE_KEY = "corso";


    String name, email, github;
   Course course;

    public Student(JSONObject jsonStudent) {
        try {
            name = jsonStudent.getString(NAME_KEY);
            email = jsonStudent.getString(EMAIL_KEY);
            github = buildGithubUrl(jsonStudent.optString(GITHUB_KEY,""));
            course=new Course(jsonStudent.getJSONObject(COURSE_KEY));
            Log.d(ContentValues.TAG, "NAME: "+name);
        } catch (JSONException e) {
            e.printStackTrace();
        }


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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    private String buildGithubUrl(String username){
        username = username.replace("@","");
        return "https://github.com/" + username;
    }

}