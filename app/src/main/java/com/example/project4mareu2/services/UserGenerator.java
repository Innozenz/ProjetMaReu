package com.example.project4mareu2.services;

import com.example.project4mareu2.models.Meeting;
import com.example.project4mareu2.models.Participants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

abstract class UserGenerator {

    private static List<Meeting> meetingList = Arrays.asList(
            new Meeting("Mario","Coucou", "20h00", "21/04/2020", Arrays.asList(new Participants("mfitzjean@gmail.com"), new Participants("coucou@gmail.com"), new Participants("coucou2@gmail.com"))),
            new Meeting("Browser","Aero", "20h00", "21/04/2020", Arrays.asList(new Participants("mfitzjean@gmail.com"), new Participants("coucou@gmail.com"), new Participants("coucou2@gmail.com"))),
            new Meeting("Toad","Check", "20h00", "21/04/2020", Arrays.asList(new Participants("mfitzjean@gmail.com"), new Participants("coucou@gmail.com"), new Participants("coucou2@gmail.com")))
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
