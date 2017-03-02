package com.example.andreavalenziano.samplejsonparser.adapters;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.andreavalenziano.samplejsonparser.R;
import com.example.andreavalenziano.samplejsonparser.models.Place;

import java.util.ArrayList;

/**
 * Created by AndreaValenziano on 02/03/17.
 */
public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.PlaceViewHolder>{


    private ArrayList<Place> dataSet = new ArrayList<>();

    public void setDataSet(ArrayList<Place> dataSet) {
        this.dataSet = dataSet;
        notifyDataSetChanged();
    }

    @Override
    public PlaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_place, parent, false);

        return new PlaceViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PlaceViewHolder holder, int position) {

        Place place = dataSet.get(position);
        holder.placeName.setText(place.getName());
        holder.placeAdress.setText(place.getAddress());

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    class PlaceViewHolder extends RecyclerView.ViewHolder {
        TextView placeName;
        TextView placeAdress;
        Button goMaps_Btn;

        PlaceViewHolder(View itemView) {
            super(itemView);
            placeName = (TextView) itemView.findViewById(R.id.place_name);
            placeAdress = (TextView) itemView.findViewById(R.id.place_address);
            goMaps_Btn=(Button)itemView.findViewById(R.id.go_maps_btn);

            goMaps_Btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(v.getId()==R.id.go_maps_btn){
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        Uri uri = Uri.parse("geo:0,0?q=" + dataSet.get(getAdapterPosition()).getAddress());
                        intent.setData(uri);
                        v.getContext().startActivity(intent);
                    }
                }
            });
        }
    }
}
