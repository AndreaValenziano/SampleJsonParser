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
public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.PlaceViewHolder> {


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
        holder.placeNameTV.setText(place.getName());
        holder.placeAdressTV.setText(place.getAddress());
        holder.phoneNumberTV.setText(place.getPhoneNumber());

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    class PlaceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView placeNameTV;
        TextView placeAdressTV, phoneNumberTV;
        Button goMapsBtn;

        PlaceViewHolder(View itemView) {
            super(itemView);
            placeNameTV = (TextView) itemView.findViewById(R.id.place_name);
            placeAdressTV = (TextView) itemView.findViewById(R.id.place_address);
            goMapsBtn =(Button)itemView.findViewById(R.id.go_maps_btn);
            phoneNumberTV=(TextView) itemView.findViewById(R.id.phone_number_tv);

            goMapsBtn.setOnClickListener(this);
            phoneNumberTV.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(v.getId()==R.id.go_maps_btn){
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                Uri uri = Uri.parse("geo:0,0?q=" + dataSet.get(getAdapterPosition()).getAddress());
                intent.setData(uri);
                v.getContext().startActivity(intent);
            }
            if(v.getId()==R.id.phone_number_tv){
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                Uri uri = Uri.parse("tel:" + dataSet.get(getAdapterPosition()).getPhoneNumber());
                intent.setData(uri);
                v.getContext().startActivity(intent);
            }


        }
    }
}
