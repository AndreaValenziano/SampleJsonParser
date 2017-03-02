package com.example.andreavalenziano.samplejsonparser.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by AndreaValenziano on 02/03/17.
 */
public class Place {
    private int id;
    private String name;
    private String address;
    private Double latitiude;
    private Double longitude;
    private String phoneNumber;


    // KEYS
    private static final String NAME_KEY = "name";
    private static final String LOCATION_KEY = "location";
    private static final String ADDRESS_KEY = "address";
    private static final String LAT_KEY = "lat";
    private static final String LONG_KEY = "lng";
    private static final String CONTACT_KEY = "contact";
    private static final String PHONE_NUMBER_KEY = "phone";
    private static final String FORMATTED_PHONE_NUMBER_KEY = "formattedPhone";



    public Place(JSONObject jsonPlace) {

        try {
            name = jsonPlace.getString(NAME_KEY);
            address = jsonPlace.getJSONObject(LOCATION_KEY).optString(ADDRESS_KEY);
            latitiude = jsonPlace.getJSONObject(LOCATION_KEY).getDouble(LAT_KEY);
            longitude = jsonPlace.getJSONObject(LOCATION_KEY).getDouble(LONG_KEY);
            phoneNumber =jsonPlace.getJSONObject(CONTACT_KEY).optString(PHONE_NUMBER_KEY);

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLatitiude() {
        return latitiude;
    }

    public void setLatitiude(Double latitiude) {
        this.latitiude = latitiude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
