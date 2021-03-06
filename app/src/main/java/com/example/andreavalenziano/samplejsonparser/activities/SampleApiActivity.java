package com.example.andreavalenziano.samplejsonparser.activities;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.andreavalenziano.samplejsonparser.R;
import com.example.andreavalenziano.samplejsonparser.adapters.PlacesAdapter;
import com.example.andreavalenziano.samplejsonparser.models.Place;
import com.example.andreavalenziano.samplejsonparser.services.FoursquareAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by AndreaValenziano on 02/03/17.
 */

public class SampleApiActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    PlacesAdapter adapter;

    EditText searchEt, searchCityET;
    Button searchBtn;
    ProgressBar loading;

    FoursquareAPI foursquareAPI;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_api);
        adapter=new PlacesAdapter();
        layoutManager=new LinearLayoutManager(this);
        recyclerView=(RecyclerView)findViewById(R.id.search_rv);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        searchEt=(EditText)findViewById(R.id.search_et);
        searchCityET=(EditText)findViewById(R.id.search_city_et);
        searchBtn=(Button)findViewById(R.id.search_btn);
        loading=(ProgressBar)findViewById(R.id.loading);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSearch();
            }
        });


    }

    private void doSearch() {
        String query = searchEt.getText().toString();
        Log.d(ContentValues.TAG,"QUERY: "+query);
        String near=searchCityET.getText().toString();
        if(query.isEmpty()||near.isEmpty()){
            showAlertDialog();

        }else{
            new FoursquareApiTask().execute(query, near);
        }

    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.dialog_message)
                .setTitle(R.string.dialog_title);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private class FoursquareApiTask extends AsyncTask<String, Void, ArrayList<Place>>{

        private static final String RESPONSE = "response";
        private static final String VENUES = "venues";


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading.setVisibility(View.VISIBLE);

        }


        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected ArrayList<Place> doInBackground(String... strings) {

            ArrayList<Place> placesArraylist = new ArrayList<>();
            try {
                foursquareAPI = new FoursquareAPI();
                String url = foursquareAPI.getUrlString(strings[0], strings[1]);



                JSONObject jsonResponse = foursquareAPI.getJSONObjectFromURL(url);
                JSONArray jsonPlaces = jsonResponse.getJSONObject(RESPONSE)
                        .getJSONArray(VENUES);


                for(int i = 0; i < jsonPlaces.length(); i++){
                    placesArraylist.add(new Place(jsonPlaces.getJSONObject(i)));

                }

                return placesArraylist;

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return placesArraylist;
        }

        @Override
        protected void onPostExecute(ArrayList<Place> places) {
            super.onPostExecute(places);
            adapter.setDataSet(places);
            loading.setVisibility(View.GONE);
        }
    }
}
