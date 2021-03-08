package com.example.confessbuddy.UI.HomePageActivity.University;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.confessbuddy.Model.Confess;
import com.example.confessbuddy.R;
import com.example.confessbuddy.UI.HomePageActivity.University.UniversityWise.UniversityConfess;

import java.util.ArrayList;

public class UniversityConfessAdapter extends RecyclerView.Adapter<UniversityConfessAdapter.ConfessForAdapter>{

    private ArrayList<String> arrayList;
    private Context context;
    private RecyclerViewOnClickListener listener;
    private Activity activity;

    public UniversityConfessAdapter(Activity activity, Context context, ArrayList<String> arrayList, RecyclerViewOnClickListener listener) {
        this.arrayList = arrayList;
        this.context = context;
        this.listener = listener;
        this.activity = activity;
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
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String universityName = holder.university.getText().toString();
                openIndividualUniversityPage(universityName);
            }
        });
    }

    private void openIndividualUniversityPage(String universityName) {
        Intent universityConfessIntent = new Intent(context, UniversityConfess.class);
        universityConfessIntent.putExtra("UniversityName", universityName);
        context.startActivity(universityConfessIntent);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public interface RecyclerViewOnClickListener {
        void onClick(View v, int position);
    }

    public class ConfessViewHolder extends RecyclerView.ViewHolder {

        public ConfessViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public class ConfessForAdapter extends RecyclerView.ViewHolder{
        TextView university;

        public ConfessForAdapter(@NonNull View itemView) {
            super(itemView);
            this.university = itemView.findViewById(R.id.universityConfessViewUniversityText);
        }

    }

}