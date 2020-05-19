package com.example.project4mareu2;

import com.example.project4mareu2.activities.MainActivity;
import com.example.project4mareu2.activities.MyAdapter;
import com.example.project4mareu2.di.DI;
import com.example.project4mareu2.models.Meeting;
import com.example.project4mareu2.services.MeetingApiService;
import com.example.project4mareu2.services.MeetingGenerator;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.util.List;
import java.util.logging.Filter;

import static org.junit.Assert.*;


@RunWith(RobolectricTestRunner.class)
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
        assertEquals(service.getMeetings().size(), 2);
    }

    @Test
    public void deleteMeetingWithSuccess() {
        Meeting meetingToDelete = service.getMeetings().get(0);
        service.deleteMeetings(meetingToDelete);
        assertFalse(service.getMeetings().contains(meetingToDelete));
    }

    @Test
    public void filterByDate() {
        MyAdapter mMyAdapter = new MyAdapter(meetings);
        mMyAdapter.getItemCount();
    }

}