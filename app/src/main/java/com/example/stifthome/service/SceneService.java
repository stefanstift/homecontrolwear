package com.example.stifthome.service;

import android.content.Context;

public class SceneService extends HomeService {


    public SceneService(Context context) {
        super(context);
    }


    public void cinema() {
        switchIt("scene/cinema");
    }

}
