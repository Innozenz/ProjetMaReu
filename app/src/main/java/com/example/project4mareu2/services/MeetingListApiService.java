package com.example.project4mareu2.services;

import android.widget.Adapter;

import com.example.project4mareu2.models.Meeting;
import com.example.project4mareu2.models.Participants;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Filter;

public class MeetingListApiService implements MeetingApiService {

    private List<Meeting> meetings = MeetingGenerator.generateMeeting();

    public List<Meeting> getMeetings() {
        return new ArrayList<>(meetings);
    }


    @Override
    public void createMeetings(Meeting meeting) {
        meetings.add(meeting);
    }


    public void deleteMeetings(Meeting meeting) {
        meetings.remove(meeting);
    }

    @Override
    public List<Participants> getMeetingParticipants() {
        return mParticipants;
    }

    public List<Meeting> getFilterMeetings(String constraint) {
        List<Meeting> filteredList = new ArrayList<>();
        if (constraint == null || constraint.length() == 0) {
            filteredList.addAll(meetings);
        } else {
            String filterPattern = constraint.toString().toLowerCase().trim();
            for (Meeting item : meetings) {
                if (item.getMeetingLocation().toLowerCase().contains(filterPattern)) {
                    filteredList.add(item);
                } if (item.getMeetingDate().toLowerCase().contains(filterPattern)) {
                    filteredList.add(item);
                }
            }
        }
        return filteredList;
    }

    public List<Meeting> getDateFilterMeetings(String constraint) {
        List<Meeting> filteredList = new ArrayList<>();
        if (constraint == null || constraint.length() == 0) {
            filteredList.addAll(meetings);
        } else {
            String filterPattern = constraint.toString().toLowerCase().trim();
            for (Meeting item2 : meetings) {
                if (item2.getMeetingDate().toLowerCase().contains(filterPattern)) {
                    filteredList.add(item2);
                }
            }
        }
        return filteredList;
    }

    private List<Participants> mParticipants = MeetingGenerator.generateParticipants();


    public void createParticipants(Participants participants) {
        mParticipants.add(participants);
    }

}
