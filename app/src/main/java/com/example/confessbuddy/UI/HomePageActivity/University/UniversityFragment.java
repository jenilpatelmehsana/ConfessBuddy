package com.example.confessbuddy.UI.HomePageActivity.University;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.confessbuddy.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.function.ToDoubleBiFunction;

public class UniversityFragment extends Fragment {

    private RecyclerView universityConfessView;
    private ArrayList<String> arrayList;
    private UniversityConfessAdapter arrayAdapter;
    private UniversityConfessAdapter.RecyclerViewOnClickListener listener;

    public UniversityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        listener = new UniversityConfessAdapter.RecyclerViewOnClickListener() {
            @Override
            public void onClick(View v, int position) {
                System.out.println(position);
            }
        };
        View view = inflater.inflate(R.layout.fragment_university, container, false);
        universityConfessView = view.findViewById(R.id.universityConfessView);
        arrayList = new ArrayList<>();
        universityConfessView.setLayoutManager(new LinearLayoutManager(getContext()));
        arrayAdapter = new UniversityConfessAdapter(getActivity(), getContext(), arrayList, listener);
        universityConfessView.setAdapter(arrayAdapter);

//        adding onItemClickListner

        //Fetching university names
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        db.getReference("/byUniversity")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Object rawObject = snapshot.getValue();
                        JSONObject universityJSONObject = (JSONObject) JSONObject.wrap(rawObject);
                        Iterator<String> hm = universityJSONObject.keys();
                        while(hm.hasNext()) {
                            Collections.sort(arrayList);
                            arrayList.add(0, hm.next());
                        }
                        arrayAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        return view;
    }

}