package com.example.stifthome;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.wear.ambient.AmbientModeSupport;
import androidx.wear.widget.drawer.WearableActionDrawerView;
import androidx.wear.widget.drawer.WearableNavigationDrawerView;

import com.example.stifthome.fragment.DashboardFragment;
import com.example.stifthome.fragment.KitchenFragment;
import com.example.stifthome.fragment.OfficeFragment;
import com.example.stifthome.model.HomeMenuItem;
import com.example.stifthome.service.FloorService;
import com.example.stifthome.service.MessCallback;
import com.example.stifthome.service.MessService;
import com.example.stifthome.service.SceneService;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements
        AmbientModeSupport.AmbientCallbackProvider,
        MenuItem.OnMenuItemClickListener,
        WearableNavigationDrawerView.OnItemSelectedListener {

    private static final String TAG = "MainActivity";

    private WearableNavigationDrawerView mWearableNavigationDrawer;
    private WearableActionDrawerView mWearableActionDrawer;

    private ArrayList<HomeMenuItem> menuItems;

    private SceneService sceneService;
    private FloorService floorService;
    private MessService messService;

    private Fragment dashboardFragment;
    private OfficeFragment officeFragment;
    private KitchenFragment kitchenFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        enforceWlan();

        // Enables Ambient mode.
        AmbientModeSupport.attach(this);

        menuItems = initializeMenu();

        messService = new MessService(getApplicationContext());
        sceneService = new SceneService(getApplicationContext());
        floorService = new FloorService(getApplicationContext());

        setTitle();

        // Top Navigation Drawer
        mWearableNavigationDrawer = findViewById(R.id.top_navigation_drawer);
        mWearableNavigationDrawer.setAdapter(new NavigationAdapter(this));
        // Peeks navigation drawer on the top.
        mWearableNavigationDrawer.getController().peekDrawer();
        mWearableNavigationDrawer.addOnItemSelectedListener(this);

        // Bottom Action Drawer
        mWearableActionDrawer = findViewById(R.id.bottom_action_drawer);
        // Peeks action drawer on the bottom.
        mWearableActionDrawer.getController().peekDrawer();
        mWearableActionDrawer.setOnMenuItemClickListener(this);

        /* Action Drawer Tip: If you only have a single action for your Action Drawer, you can use a
         * (custom) View to peek on top of the content by calling
         * mWearableActionDrawer.setPeekContent(View). Make sure you set a click listener to handle
         * a user clicking on your View.
         */
        onItemSelected(0);
    }

    private void enforceWlan() {

        final ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        ConnectivityManager.NetworkCallback networkCallback = new ConnectivityManager.NetworkCallback() {
            @Override
            public void onAvailable(Network network) {
                if (connectivityManager.bindProcessToNetwork(network)) {
                    // socket connections will now use this network
                } else {
                    // app doesn't have android.permission.INTERNET permission
                }
            }
        };

        NetworkRequest request = new NetworkRequest.Builder()
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                .build();

        connectivityManager.requestNetwork(request, networkCallback);
    }

    private void setTitle() {
        messService.getFeeledTemp(new MessCallback() {
            @Override
            public boolean value(float f) {
                setTitle(f + "°C");
                return true;
            }
        });
    }

    private ArrayList<HomeMenuItem> initializeMenu() {
        ArrayList<HomeMenuItem> solarSystem = new ArrayList<>();

        solarSystem.add(new HomeMenuItem("Dashboard"));
        solarSystem.add(new HomeMenuItem("Büro"));
        solarSystem.add(new HomeMenuItem("Küche"));

        return solarSystem;
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {

        final int itemId = menuItem.getItemId();

        String toastMessage = "";

        switch (itemId) {
            case R.id.menu_all_lights_off:
                floorService.allLightsOff();
                break;
            case R.id.menu_scene_cinema:
                sceneService.cinema();
                break;
            case R.id.menu_eg_lights_off:
                floorService.egLightsOff();
                break;
        }

        mWearableActionDrawer.getController().closeDrawer();

        if (toastMessage.length() > 0) {
            Toast toast = Toast.makeText(
                    getApplicationContext(),
                    toastMessage,
                    Toast.LENGTH_SHORT);
            toast.show();
            return true;
        } else {
            return false;
        }
    }

    // Updates content when user changes between items in the navigation drawer.
    @Override
    public void onItemSelected(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, getFragment(position)).commit();
    }

    private final class NavigationAdapter extends WearableNavigationDrawerView.WearableNavigationDrawerAdapter {

        private final Context mContext;

        NavigationAdapter(Context context) {
            mContext = context;
        }

        @Override
        public int getCount() {
            return menuItems.size();
        }

        @Override
        public String getItemText(int pos) {
            return menuItems.get(pos).getName();
        }

        @Override
        public Drawable getItemDrawable(int pos) {
            return mContext.getDrawable(R.drawable.ic_baseline_info_24);
        }
    }

    private Fragment getFragment(int position) {

        switch (position) {
            case 0:
                if(dashboardFragment == null) {
                    dashboardFragment = new DashboardFragment();
                }
                return dashboardFragment;
            case 1:
                if(officeFragment == null) {
                    officeFragment = new OfficeFragment();
                }
                return officeFragment;
            case 2:
                if(kitchenFragment == null) {
                    kitchenFragment = new KitchenFragment();
                }
                return kitchenFragment;
        }

        return null;
    }





    @Override
    public AmbientModeSupport.AmbientCallback getAmbientCallback() {
        return new MyAmbientCallback();
    }

    private class MyAmbientCallback extends AmbientModeSupport.AmbientCallback {
        /**
         * Prepares the UI for ambient mode.
         */
        @Override
        public void onEnterAmbient(Bundle ambientDetails) {
            super.onEnterAmbient(ambientDetails);

            mWearableNavigationDrawer.getController().closeDrawer();
            mWearableActionDrawer.getController().closeDrawer();
        }

        /**
         * Restores the UI to active (non-ambient) mode.
         */
        @Override
        public void onExitAmbient() {
            super.onExitAmbient();

            mWearableActionDrawer.getController().peekDrawer();
        }
    }
}