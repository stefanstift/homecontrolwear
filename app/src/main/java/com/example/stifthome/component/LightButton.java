package com.example.stifthome.component;

import android.view.View;
import android.widget.ToggleButton;

import com.example.stifthome.service.LightCallback;
import com.example.stifthome.service.LightService;

public class LightButton {

    private ToggleButton button;
    private LightService lightService;
    private boolean state;
    private String path;

    public LightButton(String path, ToggleButton button, LightService lightService) {
        this.button = button;
        this.lightService = lightService;
        this.path = path;
        init();
    }

    private void init() {
        getState();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lightService.switchLight(path);
                state = !state;
                button.setChecked(state);
            }
        });
    }

    private void getState() {
        lightService.getLightState(path, new LightCallback() {
            @Override
            public boolean state(boolean b) {
               state = b;
               button.setChecked(state);
               return true;
            }
        });
    }


}
