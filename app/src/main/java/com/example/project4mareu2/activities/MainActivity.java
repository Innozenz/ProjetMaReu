package com.example.project4mareu2.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project4mareu2.R;
import com.example.project4mareu2.di.DI;
import com.example.project4mareu2.models.Meeting;
import com.example.project4mareu2.models.Participants;
import com.example.project4mareu2.services.MeetingApiService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private RecyclerView mRecyclerView;
    private List<Meeting> meetings = new ArrayList<>();
    private List<Participants> participants;
    private MyAdapter mMyAdapter;
    private MeetingApiService mApiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab = findViewById(R.id.add);
        mApiService = DI.getUserApiService();
        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        mMyAdapter = new MyAdapter(meetings);
        initList();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mMyAdapter);

        //Meeting Activity creation

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
        MenuItem item2 = menu.findItem(R.id.clear);
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.filter:
                Toast.makeText(this, "DatePicker selected", Toast.LENGTH_SHORT);
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "dd/mm/aaaa");
            case R.id.clear:
                Toast.makeText(this, "Filter cleared", Toast.LENGTH_SHORT);
                meetings.clear();
                meetings.addAll(mApiService.getMeetings());
                mMyAdapter.notifyDataSetChanged();
                participants = mApiService.getMeetingParticipants();
        }return true;
    }


// Initialize the list

    private void initList() {
        meetings.clear();
        meetings.addAll(mApiService.getMeetings());
        mMyAdapter.notifyDataSetChanged();
        participants = mApiService.getMeetingParticipants();
    }

    @Override
    public void onResume() {
        super.onResume();
        initList();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = format.format(calendar.getTime());
        mMyAdapter.getDateFilter().filter(strDate);
    }
}
