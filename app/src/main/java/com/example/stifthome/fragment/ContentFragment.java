package com.example.stifthome.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.stifthome.R;

public class ContentFragment extends Fragment {

    public static final String ARG_SELECTED_MENU_ITEM = "meun_item_id";

    private TextView roomName;

    public ContentFragment() {
        // Empty constructor required for fragment subclasses
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_content, container, false);

        roomName = rootView.findViewById(R.id.roomName);
        int selectedMenuItem = getArguments() != null
                ? getArguments().getInt(ARG_SELECTED_MENU_ITEM)
                : 0;

        switch (selectedMenuItem) {
            case 0:
                roomName.setText("Dashboard");
                break;
            case 1:
                roomName.setText("EG");
                break;
            case 2:
                roomName.setText("OG");
                break;
            case 3:
                roomName.setText("Büro");
                break;
        }

        return rootView;
    }

    public void updateSelected(int selectedMenuItem) {
        switch (selectedMenuItem) {
            case 0:
                roomName.setText("Dashboard");
                break;
            case 1:
                roomName.setText("EG");
                break;
            case 2:
                roomName.setText("OG");
                break;
            case 3:
                roomName.setText("Büro");
                break;
        }
    }

}