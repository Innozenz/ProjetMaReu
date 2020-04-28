package com.example.project4mareu2.services;

import com.example.project4mareu2.models.Meeting;
import com.example.project4mareu2.models.Participants;

import java.util.List;

public class UserListApiService implements UserApiService {

    private List<Meeting> meetings = UserGenerator.generateMeeting();

    public List<Meeting> getMeetings() {
        return meetings;
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

    private List<Participants> mParticipants = UserGenerator.generateParticipants();


    public void createParticipants(Participants participants) {
        mParticipants.add(participants);
    }


}
