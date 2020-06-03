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

    public static java.util.logging.Filter generateDateFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                meetingsFiltered = mApiService.getMeetings();;
                List<Meeting> filteredList = new ArrayList<>();
                if (constraint == null || constraint.length() == 0) {
                    filteredList.addAll(meetingsFiltered);
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();
                    for (Meeting item : meetingsFiltered) {
                        if (item.getMeetingLocation().toLowerCase().contains(filterPattern)) {
                            filteredList.add(item);
                        } if (item.getMeetingDate().toLowerCase().contains(filterPattern)) {
                            filteredList.add(item);
                        }
                    }
                }
                FilterResults results = new FilterResults();
                results.values = filteredList;

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                meetings.clear();
                meetings.addAll((Collection<? extends Meeting>) results.values);
                notifyDataSetChanged();
            }
        };

    }

}
