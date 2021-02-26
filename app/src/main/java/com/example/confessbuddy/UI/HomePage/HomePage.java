package com.example.confessbuddy.UI.HomePage;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.confessbuddy.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {

//    ListView optionView;
//    ArrayAdapter<String> arrayAdapter;

    private BottomNavigationView bottomNavigationView;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        setTitle("View Confess");
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        navController = Navigation.findNavController(this, R.id.fragment_layout);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
//        optionView = findViewById(R.id.optionView);
//        ArrayList<String> options = new ArrayList<>();
//        options.add("Recent");
//        options.add("By City");
//        options.add("By University");
//        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, options);
//        optionView.setAdapter(arrayAdapter);
//        optionView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                switch (position) {
//                    case 0: {
//                        gotoRecent();
//                        break;
//                    }
//                    case 1: {
//                        gotoCity();
//                        break;
//                    }
//                    case 2: {
//                        gotoUniversity();
//                        break;
//                    }
//                }
//            }
//        });
    }

//    private void gotoUniversity() {
//    }
//
//    private void gotoCity() {
//    }
//
//    private void gotoRecent() {
//        Intent recentActivityIntent = new Intent(this, ConfessByCity.class);
//        startActivity(recentActivityIntent);
//        this.finish();
//    }

}