package com.example.confessbuddy.UI.HomePageActivity.City;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.confessbuddy.R;

import java.util.ArrayList;

public class CityConfessAdapter extends RecyclerView.Adapter<CityConfessAdapter.ConfessForAdapter> {

    private ArrayList<String> list;
    private Context context;

    public CityConfessAdapter(Context context, ArrayList<String> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ConfessForAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.city_confess_layout, parent, false);
        return new ConfessForAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConfessForAdapter holder, int position) {
        String city = list.get(position);
        holder.city.setText(city);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ConfessForAdapter extends RecyclerView.ViewHolder {
        TextView city;

        public ConfessForAdapter(@NonNull View itemView) {
            super(itemView);
            this.city = itemView.findViewById(R.id.cityConfessViewCityText);
        }
    }

}
