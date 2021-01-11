package com.example.stifthome.service;

import android.content.Context;

public class FloorService extends HomeService {


    public FloorService(Context context) {
        super(context);
    }


    public void allLightsOff() {
        switchIt("floor/all/light/off");
    }

}
