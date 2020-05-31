package com.example.project4mareu2.activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.project4mareu2.R;
import com.example.project4mareu2.di.DI;
import com.example.project4mareu2.models.Meeting;
import com.example.project4mareu2.models.Participants;
import com.example.project4mareu2.services.MeetingApiService;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddMeetingActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    // Member variables

    private MeetingApiService mApiService;
    private EditText participants;
    private ChipGroup chipGroup;
    private EditText subject;
    private TextView dateTextView;
    private TextView timeTextView;
    private Spinner mySpinner;
    private List<Participants> mParticipants = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);

        // Variables wire up

        dateTextView = (TextView) findViewById(R.id.dateTextView);
        timeTextView = (TextView) findViewById(R.id.timeTextView);
        mApiService = DI.getUserApiService();
        participants = (EditText) findViewById(R.id.participants);
        chipGroup = (ChipGroup) findViewById(R.id.chipGroup);
        subject = (EditText) findViewById(R.id.subject);
        mySpinner = (Spinner) findViewById(R.id.location);


        // Spinner set up


        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(AddMeetingActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.meeting_location));
        myAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);


        // TimePicker & DatePicker Click

        timeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        dateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "dd/mm/aaaa");
            }
        });
    }

    // Add participants button for the chip

    public void btnClick(View view) {
        final Chip chip = new Chip(this);
        ChipDrawable drawable = ChipDrawable.createFromAttributes(this, null, 0,R.style.Widget_MaterialComponents_Chip_Entry);
        chip.setChipDrawable(drawable);
        chip.setCheckable(false);
        chip.setClickable(false);
        chip.setChipIconResource(R.drawable.ic_email_black_24dp);
        chip.setIconStartPadding(3f);
        chip.setPadding(60, 10, 60, 10);
        chip.setText(participants.getText().toString());
        chip.setOnCloseIconClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chipGroup.removeView(chip);
            }
        });

        mParticipants.add(new Participants(chip.getText().toString()));
        chipGroup.addView(chip);
        participants.setText("");
    }
        // Add meeting button

    public void btnSave(View view) {
        Meeting meeting = new Meeting(
                        mySpinner.getSelectedItem().toString(),
                        subject.getText().toString(),
                        timeTextView.getText().toString(),
                        dateTextView.getText().toString(),
                        mParticipants
                );
                mApiService.createMeetings(meeting);
                finish();
            }


    // TimePicker & DatePicker set up

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        TextView timeTextView = (TextView) findViewById(R.id.timeTextView);
        timeTextView.setText(String.format("%02d:%02d", hourOfDay, minute));
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = format.format(calendar.getTime());
        dateTextView.setText(strDate);
    }
}
