package com.example.project4mareu2.services;

import com.example.project4mareu2.models.Meeting;
import com.example.project4mareu2.models.Participants;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Filter;

public class MeetingListApiService implements MeetingApiService {

    private List<Meeting> meetings = MeetingGenerator.generateMeeting();
    private Filter dateFilter = MeetingGenerator.generateDateFilter();

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

    @Override
    public Filter getFilter() {
        return dateFilter;
    }

    @Override
    public Filter getDateFilter() {
        return null;
    }

    private List<Participants> mParticipants = MeetingGenerator.generateParticipants();


    public void createParticipants(Participants participants) {
        mParticipants.add(participants);
    }


}
