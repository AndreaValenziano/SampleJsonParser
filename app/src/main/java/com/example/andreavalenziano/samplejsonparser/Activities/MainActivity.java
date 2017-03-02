package com.example.andreavalenziano.samplejsonparser.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.andreavalenziano.samplejsonparser.R;

/**
 * Created by AndreaValenziano on 02/03/17.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button sampleApiBtn, sampleJasonBtn;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sampleApiBtn=(Button)findViewById(R.id.sample_api_btn);
        sampleJasonBtn=(Button) findViewById(R.id.list_user_btn);

        sampleApiBtn.setOnClickListener(this);
        sampleJasonBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        int id=v.getId();

        if(id==R.id.sample_api_btn){
            startActivity(new Intent(this, SampleApiActivity.class));
        }
        if(id==R.id.list_user_btn){
            startActivity(new Intent(this,StudentsActivity.class));
        }

    }
}
