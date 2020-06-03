package com.example.project4mareu2;

import com.example.project4mareu2.controller.adapter.MyAdapter;
import com.example.project4mareu2.di.DI;
import com.example.project4mareu2.models.Meeting;
import com.example.project4mareu2.services.MeetingApiService;
import com.example.project4mareu2.services.MeetingGenerator;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.List;

import static org.junit.Assert.*;

public class MaReuUnitTest {

    private MeetingApiService service;
    private MyAdapter mMyAdapter;
    private List<Meeting> meetings;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getMeetingWithSuccess() {
        List<Meeting> meetings = service.getMeetings();
        List<Meeting> expectedMeetings = MeetingGenerator.meetingList;
        assertThat(meetings, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedMeetings.toArray()));
    }

    @Test
    public void addMeetingWithSuccess() {
        Meeting meetingToAdd = service.getMeetings().get(0);
        service.createMeetings(meetingToAdd);
        assertEquals(service.getMeetings().size(), 4);
    }

    @Test
    public void deleteMeetingWithSuccess() {
        Meeting meetingToDelete = service.getMeetings().get(0);
        service.deleteMeetings(meetingToDelete);
        assertFalse(service.getMeetings().contains(meetingToDelete));
    }

    @Test
    public void filterByDate() {
        service.getDateFilterMeetings("21/04/2020");
        assertEquals(1, service.getDateFilterMeetings("21/04/2020").size());
        service.getDateFilterMeetings("30/03/2020");
        assertEquals(0, service.getDateFilterMeetings("30/03/2020").size());
    }

    @Test
    public void filterByLocation() {
        service.getFilterMeetings("Mario");
        assertEquals(1, service.getFilterMeetings("Mario").size());
        service.getFilterMeetings("Browser");
        assertEquals(1, service.getFilterMeetings("Browser").size());
        service.getFilterMeetings("Luigi");
        assertEquals(0, service.getDateFilterMeetings("Luigi").size());
    }

}