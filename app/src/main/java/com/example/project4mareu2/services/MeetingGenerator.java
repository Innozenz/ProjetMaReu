package com.example.project4mareu2.services;

import android.widget.Filter;

import com.example.project4mareu2.models.Meeting;
import com.example.project4mareu2.models.Participants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public abstract class MeetingGenerator {

    public static List<Meeting> meetingList = Arrays.asList(
            new Meeting("Mario","AERO", "20h00", "21/04/2020", Arrays.asList(new Participants("mfitzjean@gmail.com"), new Participants("test@gmail.com"), new Participants("test2@gmail.com"))),
            new Meeting("Browser","INFO", "20h00", "25/04/2020", Arrays.asList(new Participants("mfitzjean@gmail.com"), new Participants("test@gmail.com"), new Participants("test2@gmail.com"))),
            new Meeting("Toad","APP", "20h00", "29/04/2020", Arrays.asList(new Participants("mfitzjean@gmail.com"), new Participants("test@gmail.com"), new Participants("test2@gmail.com")))
    );

    static List<Meeting> generateMeeting() {
        return new ArrayList<>(meetingList);
    }

    private static List<Participants> participantsList = Arrays.asList(

    );

    static List<Participants> generateParticipants() {
        return new ArrayList<>(participantsList);
    }


}
