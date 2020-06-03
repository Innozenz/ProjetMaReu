package com.example.project4mareu2.services;

import com.example.project4mareu2.models.Meeting;
import com.example.project4mareu2.models.Participants;

import java.util.List;
import java.util.logging.Filter;

public interface MeetingApiService {

    List<Meeting> getMeetings();

    void createMeetings(Meeting meeting);

    void deleteMeetings(Meeting meeting);

    List<Participants> getMeetingParticipants();

    Filter getFilter();

    Filter getDateFilter();

}
