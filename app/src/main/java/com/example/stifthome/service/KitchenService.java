package com.example.stifthome.service;

import android.content.Context;

public class KitchenService extends HomeService implements LightService {


    public KitchenService(Context context) {
        super(context);
    }


    @Override
    public void switchLight(String path) {
        switchIt(path);
    }

    @Override
    public void getLightState(String path, LightCallback stateCallback) {
        getState(path, stateCallback);
    }
}
