package com.example.project4mareu2.services;

import com.example.project4mareu2.models.Meeting;
import com.example.project4mareu2.models.Participants;

import java.util.List;

public interface UserApiService {

    List<Meeting> getMeetings();

    void createMeetings(Meeting meeting);

    void deleteMeetings(Meeting meeting);

    List<Participants> getMeetingParticipants();

}
