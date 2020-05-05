package com.example.project4mareu2.services;

import com.example.project4mareu2.models.Meeting;
import com.example.project4mareu2.models.Participants;

import java.util.ArrayList;
import java.util.List;

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

    private List<Participants> mParticipants = MeetingGenerator.generateParticipants();


    public void createParticipants(Participants participants) {
        mParticipants.add(participants);
    }


}
