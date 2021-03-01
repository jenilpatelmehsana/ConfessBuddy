package com.example.confessbuddy.UI.HomePageActivity.City;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.confessbuddy.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

import java.util.ArrayList;

import java.util.Iterator;

public class CityFragment extends Fragment {

    private RecyclerView cityConfessView;
    public ArrayList<String> arrayList;
    public CityConfessAdapter arrayAdapter;

    public CityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_city, container, false);
        cityConfessView = rootView.findViewById(R.id.cityConfessView);
        arrayList = new ArrayList<>();
        cityConfessView.setLayoutManager(new LinearLayoutManager(getContext()));
        arrayAdapter = new CityConfessAdapter(getContext(), arrayList);
        cityConfessView.setAdapter(arrayAdapter);
        //Fetching city names from database
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        db.getReference("/byCity")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        HashMap<String, Confess> hm = snapshot.getValue(HashMap.class);
                        Object rawObject = snapshot.getValue();
                        JSONObject cityJSONObject = (JSONObject) JSONObject.wrap(rawObject);
                        Iterator<String> hm = cityJSONObject.keys();
                        while(hm.hasNext()) {
                            arrayList.add(hm.next());
                        }
                        arrayAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        return rootView;
    }
}