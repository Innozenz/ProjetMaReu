package com.example.project4mareu2.di;

import com.example.project4mareu2.services.UserApiService;
import com.example.project4mareu2.services.UserListApiService;

public class DI {

    public static UserApiService service = new UserListApiService();

    public static UserApiService getUserApiService() {
        return service;
    }

    public static UserApiService getNewInstanceApiService() {
        return new UserListApiService();
    }
}
