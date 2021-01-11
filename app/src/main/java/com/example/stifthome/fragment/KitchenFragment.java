package com.example.stifthome.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

import androidx.fragment.app.Fragment;

import com.example.stifthome.R;
import com.example.stifthome.component.LightButton;
import com.example.stifthome.service.KitchenService;
import com.example.stifthome.service.OfficeService;

public class KitchenFragment extends Fragment {


    private KitchenService kitchenService;

    public KitchenFragment() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_kitchen, container, false);

        kitchenService = new KitchenService(getContext());

        new LightButton("room/kitchen/light/eatingcorner", (ToggleButton) rootView.findViewById(R.id.kitchenessecke), kitchenService);
        new LightButton("room/kitchen/light/spots/front", (ToggleButton) rootView.findViewById(R.id.kitchenspotsfront), kitchenService);
        new LightButton("room/kitchen/light/spots/middle", (ToggleButton) rootView.findViewById(R.id.kitchenspotsinsel), kitchenService);
        new LightButton("room/kitchen/light/spots/back", (ToggleButton) rootView.findViewById(R.id.kitchenspotswand), kitchenService);

        return rootView;
    }

}