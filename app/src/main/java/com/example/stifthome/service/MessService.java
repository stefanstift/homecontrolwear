package com.example.stifthome.service;

import android.content.Context;

public class MessService extends HomeService {


    public MessService(Context context) {
        super(context);
    }


    public void getFeeledTemp(MessCallback messCallback) {
        getFloat("mess/feeledtemp", messCallback);
    }

    public void getTemp(MessCallback messCallback) {
        getFloat("mess/temp", messCallback);
    }

    public void getWind(MessCallback messCallback) {
        getFloat("mess/windspeed", messCallback);
    }

}
