package com.example.andreavalenziano.samplejsonparser.services;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by AndreaValenziano on 02/03/17.
 */
public class FoursquareAPI {
    private static final String BASE_URL = "https://api.foursquare.com/v2/venues/search?v=20161016&near=";
    private static final String TOKEN = "&client_id=PRNHPU011KKTDFQUMCP3BGHF3K0532MFRTN5VJAVD4KTVVDM&client_secret=0NRKK422MKHOAAI31C524G4LFV41ADGMKGOIF2MONVW4X2GB";
    private static final String AND_QUERY ="&query=";
    private static final String RESTAURANT_ID="&categoryId=4bf58dd8d48988d110941735";


    public JSONObject getJSONObjectFromURL(String urlString) throws IOException, JSONException {

        HttpURLConnection urlConnection = null;
        URL url = new URL(urlString);

        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setReadTimeout(10000);
        urlConnection.setConnectTimeout(15000);
        urlConnection.setDoOutput(true);
        urlConnection.connect();

        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        String jsonString;
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        br.close();
        jsonString = sb.toString();
        Log.d("JSON: ", jsonString);

        return new JSONObject(jsonString);
    }

    public String getUrlString( String query, String near) {
        return BASE_URL + near +AND_QUERY+ query + RESTAURANT_ID+ TOKEN;
    }
}
