package com.example.stifthome.service;

public interface LightService {

    void switchLight(String path);

    void getLightState(String path, LightCallback stateCallback);


}
