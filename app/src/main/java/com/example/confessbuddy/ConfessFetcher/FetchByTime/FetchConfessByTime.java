package com.example.confessbuddy.ConfessFetcher.FetchByTime;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.confessbuddy.Model.Confess;
import com.example.confessbuddy.UI.HomePageActivity.Recent.RecentConfessAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FetchConfessByTime {
    public static ArrayList<Confess> confessArrayList;
    public static void updateList(Context context, RecyclerView view, int lastIndex) {
        if(lastIndex <= 0) {
            RecentConfessAdapter adapter = new RecentConfessAdapter(context, confessArrayList);
            view.setAdapter(adapter);
            return;
        }
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        db.getReference("/byTime/" + String.valueOf(lastIndex))
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        db.getReference(snapshot.getValue(String.class))
                                .addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        confessArrayList.add(snapshot.getValue(Confess.class));
                                        return;
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                        return;
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        updateList(context, view, lastIndex - 1);
    }


    public static ArrayList<Confess> getList(Context context, RecyclerView view) {
         confessArrayList = new ArrayList<>();
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        db.getReference("/byTime/lastIndex")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        int lastIndex = Integer.parseInt(String.valueOf(snapshot.getValue()));
                        FetchConfessByTime.updateList(context, view, lastIndex);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
        return confessArrayList;
    }
}

class LongCustom {
    public Long ans = 0L;
    public LongCustom() {

    }
    public LongCustom(Long ans) {
        this.ans = ans;
    }
    public LongCustom(String ans) {
        this.ans = Long.parseLong(ans);
    }

    public Long getAns() {
        return ans;
    }

    public void setAns(Long ans) {
        this.ans = ans;
    }
}
