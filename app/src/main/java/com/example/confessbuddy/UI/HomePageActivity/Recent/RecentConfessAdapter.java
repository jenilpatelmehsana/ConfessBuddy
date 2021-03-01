package com.example.confessbuddy.UI.HomePageActivity.Recent;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.confessbuddy.Model.Confess;
import com.example.confessbuddy.R;

import java.util.ArrayList;

public class RecentConfessAdapter extends RecyclerView.Adapter<RecentConfessAdapter.ConfessForAdapter> {

    private ArrayList<Confess> list;
    private Context context;

    public RecentConfessAdapter(Context context, ArrayList<Confess> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ConfessForAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recent_confess_layout, parent, false);
        return new ConfessForAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConfessForAdapter holder, int position) {
        Confess confess = list.get(position);
        holder.confess.setText(confess.getConfess());
        holder.university.setText(confess.getUniversity());
        holder.date.setText(confess.getConfessDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ConfessForAdapter extends RecyclerView.ViewHolder {
        TextView confess, university, date;

        public ConfessForAdapter(@NonNull View itemView) {
            super(itemView);
            this.confess = itemView.findViewById(R.id.recentConfessViewConfessText);
            this.university = itemView.findViewById(R.id.recentConfessViewUniversityText);
            this.date = itemView.findViewById(R.id.recentConfessViewDateText);
        }
    }

}