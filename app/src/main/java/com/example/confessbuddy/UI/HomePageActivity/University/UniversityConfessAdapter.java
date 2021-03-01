package com.example.confessbuddy.UI.HomePageActivity.University;

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

public class UniversityConfessAdapter extends RecyclerView.Adapter<UniversityConfessAdapter.ConfessForAdapter>{

    private ArrayList<String> arrayList;
    private Context context;

    public UniversityConfessAdapter(Context context, ArrayList<String> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ConfessForAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.university_confess_layout, parent, false);
        return new ConfessForAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConfessForAdapter holder, int position) {
        String city = arrayList.get(position);
        holder.university.setText(city);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ConfessForAdapter extends RecyclerView.ViewHolder {
        TextView university;

        public ConfessForAdapter(@NonNull View itemView) {
            super(itemView);
            this.university = itemView.findViewById(R.id.universityConfessViewUniversityText);
        }
    }

}
