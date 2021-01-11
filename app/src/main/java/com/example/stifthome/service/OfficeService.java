package com.example.stifthome.service;

import android.content.Context;

public class OfficeService extends HomeService implements LightService {

    private Context context;

    public OfficeService(Context context) {
        super(context);
    }

    @Override
    public void switchLight(String path) {
        switchIt("room/office/light/main");
    }

    @Override
    public void getLightState(String path, LightCallback stateCallback) {
        super.getState("room/office/light/main", stateCallback);
    }
}
