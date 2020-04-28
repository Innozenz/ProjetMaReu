package com.example.project4mareu2.models;

import java.util.List;

public class Meeting {

    private String meetingLocation;
    private String meetingSubject;
    private String meetingHour;
    private String meetingDate;
    private List<Participants> meetingParticipants;

    public Meeting(String meetingLocation, String meetingSubject, String meetingHour, String meetingDate, List<Participants> meetingParticipants) {
        this.meetingLocation = meetingLocation;
        this.meetingSubject = meetingSubject;
        this.meetingHour = meetingHour;
        this.meetingDate = meetingDate;
        this.meetingParticipants = meetingParticipants;
    }

    public String getMeetingLocation() {
        return meetingLocation;
    }

    public void setMeetingLocation(String meetingLocation) {
        this.meetingLocation = meetingLocation;
    }

    public String getMeetingSubject() {
        return meetingSubject;
    }

    public void setMeetingSubject(String meetingSubject) {
        this.meetingSubject = meetingSubject;
    }

    public String getMeetingHour() {
        return meetingHour;
    }

    public void setMeetingHour(String meetingHour) {
        this.meetingHour = meetingHour;
    }

    public String getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(String meetingDate) {
        this.meetingDate = meetingDate;
    }

    public List<Participants> getMeetingParticipants() {
        return meetingParticipants;
    }

    public void setMeetingParticipants(List<Participants> meetingParticipants) {
        this.meetingParticipants = meetingParticipants;
    }


}
