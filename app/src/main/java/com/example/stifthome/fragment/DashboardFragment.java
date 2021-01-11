package com.example.stifthome.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.stifthome.R;
import com.example.stifthome.service.MessCallback;
import com.example.stifthome.service.MessService;

public class DashboardFragment extends Fragment {

    private MessService messService;

    private TextView feeledTemp;

    public DashboardFragment() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);

        messService = new MessService(getContext());

        feeledTemp = rootView.findViewById(R.id.feeledTemp);

        messService.getFeeledTemp(new MessCallback() {
            @Override
            public boolean value(float f) {
                feeledTemp.setText(f + "°C");
                return true;
            }
        });

        return rootView;
    }

}