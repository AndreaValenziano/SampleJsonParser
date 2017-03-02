package com.example.andreavalenziano.samplejsonparser.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.andreavalenziano.samplejsonparser.callbacks.SimpleItemTouchHelperCallback;
import com.example.andreavalenziano.samplejsonparser.models.Student;
import com.example.andreavalenziano.samplejsonparser.R;
import com.example.andreavalenziano.samplejsonparser.adapters.StudentsAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Created by AndreaValenziano on 27/02/17.
 */

public class StudentsActivity extends AppCompatActivity {

    RecyclerView studentsRv;
    LinearLayoutManager layoutManager;
    StudentsAdapter adapter;
    ItemTouchHelper mItemTouchHelper;

    private static final String STUDENTS_KEY="students";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);

        studentsRv=(RecyclerView)findViewById(R.id.students_rv);
        layoutManager=new LinearLayoutManager(this);
        adapter=new StudentsAdapter();

        studentsRv.setAdapter(adapter);
        studentsRv.setLayoutManager(layoutManager);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(studentsRv);
        fetchStudentsFromJSON();


    }


    private void fetchStudentsFromJSON() {
        ArrayList<Student> students = new ArrayList<>();
        try {

            JSONArray studentsJsonArray = new JSONArray(readLocalJson());
            for (int i = 0; i < studentsJsonArray.length(); i++) {
                JSONObject jsonStudent = studentsJsonArray.getJSONObject(i);
                students.add(new Student(jsonStudent));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        // add dataset to adapter
        adapter.setDataSet(students);

    }


    private String readLocalJson() {

        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try (InputStream is = getResources().openRawResource(R.raw.students)) {

            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return writer.toString();
    }



}
