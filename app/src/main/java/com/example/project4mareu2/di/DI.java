package com.example.project4mareu2.di;

import com.example.project4mareu2.services.MeetingApiService;
import com.example.project4mareu2.services.MeetingListApiService;

public class DI {

    public static MeetingApiService service = new MeetingListApiService();

    public static MeetingApiService getUserApiService() {
        return service;
    }

    public static MeetingApiService getNewInstanceApiService() {
        return new MeetingListApiService();
    }
}
