package com.example.stifthome.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.stifthome.R;
import com.example.stifthome.service.MessCallback;
import com.example.stifthome.service.MessService;

import java.text.DecimalFormat;

public class DashboardFragment extends Fragment {

    private MessService messService;

    private TextView temp;
    private TextView wind;
    private ImageButton refresh;

    public DashboardFragment() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);

        messService = new MessService(getContext());

        temp = rootView.findViewById(R.id.temp);
        wind = rootView.findViewById(R.id.wind);
        refresh = rootView.findViewById(R.id.refresh);

        fetchValues();

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchValues();
            }
        });

        return rootView;
    }

    private void fetchValues() {
        messService.getTemp(new MessCallback() {
            @Override
            public boolean value(float f) {
                temp.setText(f + " °C");
                return true;
            }
        });
        messService.getWind(new MessCallback() {
            @Override
            public boolean value(float f) {
                DecimalFormat df = new DecimalFormat("#.##");
                wind.setText(df.format(f * 3.6f) + " km/h");
                return true;
            }
        });
    }

}