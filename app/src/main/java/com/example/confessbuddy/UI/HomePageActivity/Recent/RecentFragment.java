package com.example.confessbuddy.UI.HomePageActivity.Recent;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.confessbuddy.Model.Confess;
import com.example.confessbuddy.R;
import com.example.confessbuddy.UI.Adapters.RecentConfessAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RecentFragment extends Fragment {

    private RecyclerView recentConfessView;
    public ArrayList<Confess> arrayList;
    public RecentConfessAdapter arrayAdapter;


    public RecentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_recent, container, false);
        recentConfessView = rootView.findViewById(R.id.recentConfessView);
        arrayList = new ArrayList<>();
        recentConfessView.setLayoutManager(new LinearLayoutManager(getContext()));
//        arrayList = FetchConfessByTime.getList(getContext(), recentConfessView);


        FirebaseDatabase db = FirebaseDatabase.getInstance();
        db.getReference("/byTime/lastIndex")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        int lastIndex = Integer.parseInt(String.valueOf(snapshot.getValue()));
                        for(int i = lastIndex; i > 0; --i) {
                            db.getReference("/byTime/" + String.valueOf(i))
                                    .addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            db.getReference(snapshot.getValue(String.class))
                                                    .addValueEventListener(new ValueEventListener() {
                                                        @Override
                                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                            arrayList.add(snapshot.getValue(Confess.class));
                                                            arrayAdapter.notifyDataSetChanged();
                                                        }

                                                        @Override
                                                        public void onCancelled(@NonNull DatabaseError error) {

                                                        }
                                                    });
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });
                        }
                        for(Confess x: arrayList) {
                            x.getConfess();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        arrayAdapter = new RecentConfessAdapter(getContext(), arrayList);
        recentConfessView.setAdapter(arrayAdapter);
        return rootView;
    }

}