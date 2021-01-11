package com.example.stifthome.service;

import android.content.Context;

public class MessService extends HomeService {


    public MessService(Context context) {
        super(context);
    }


    public void getFeeledTemp(MessCallback messCallback) {
        getFloat("mess/feeledtemp", messCallback);
    }

}
