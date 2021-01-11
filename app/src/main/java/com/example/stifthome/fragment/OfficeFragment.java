package com.example.stifthome.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.fragment.app.Fragment;

import com.example.stifthome.R;
import com.example.stifthome.component.LightButton;
import com.example.stifthome.service.OfficeService;

public class OfficeFragment extends Fragment {


    private OfficeService officeService;

    public OfficeFragment() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_office, container, false);
        officeService = new OfficeService(getContext());

        new LightButton("room/office/light/main", (ToggleButton) rootView.findViewById(R.id.officelightmain), officeService);

        return rootView;
    }

}