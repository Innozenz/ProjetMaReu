package com.example.project4mareu2.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project4mareu2.R;
import com.example.project4mareu2.di.DI;
import com.example.project4mareu2.models.Meeting;
import com.example.project4mareu2.models.Participants;
import com.example.project4mareu2.services.UserApiService;
import com.example.project4mareu2.views.MyAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<Meeting> meetings;
    private List<Participants> participants;
    private MyAdapter mMyAdapter;
    private UserApiService mApiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab = findViewById(R.id.add);
        mApiService = DI.getUserApiService();


        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        initList();



        mMyAdapter = new MyAdapter(meetings);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mMyAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addMeetingActivity = new Intent(MainActivity.this, AddMeetingActivity.class);
                startActivity(addMeetingActivity);
            }
        });
    }

    // Manage the searchView

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mMyAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    // Initialize the list

    private void initList() {
        meetings = mApiService.getMeetings();
        participants = mApiService.getMeetingParticipants();
        mRecyclerView.setAdapter(new MyAdapter(meetings));
    }

    @Override
    public void onResume() {
        super.onResume();
        initList();
    }

}
