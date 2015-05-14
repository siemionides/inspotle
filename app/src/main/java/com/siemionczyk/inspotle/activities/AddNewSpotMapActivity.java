package com.siemionczyk.inspotle.activities;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.siemionczyk.inspotle.R;
import com.siemionczyk.inspotle.api.InspotleApiClient;
import com.siemionczyk.inspotle.events.SpotsResponseEvent;
import com.siemionczyk.inspotle.model.SessionModel;
import com.siemionczyk.inspotle.model.Spot;
import com.siemionczyk.inspotle.utils.AnimationUtils;
import com.siemionczyk.inspotle.utils.MapUtils;
import com.siemionczyk.inspotle.utils.ViewUtils;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by michalsiemionczyk on 04/10/14.
 */
public class AddNewSpotMapActivity extends FragmentActivity implements GoogleMap.OnInfoWindowClickListener {

    SessionModel sessionModel = SessionModel.getInstance();

    public static Intent newIntent(Context ctx) {
        return new Intent(ctx, AddNewSpotMapActivity.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_place_map);
        initializeMap();
        InspotleApiClient.getInstance().getSpots();
    }


    @SuppressWarnings("unused")
    public void onEvent(SpotsResponseEvent event) {
        addDataToMap(event);
    }

    private void addDataToMap(final SpotsResponseEvent event) {
        final LatLng latLngOfLast =
                getLatLngOfLast(event.getSpots());

        MapUtils.performOnMap(getMapFragment(), new MapUtils.PerformOnMap() {
            @Override
            public void perform(GoogleMap googleMap) {
                for (Spot spot : event.getSpots()) {
                    Marker marker = googleMap.addMarker(new MarkerOptions()
                            .position(spot.getLatLng())
                            .snippet(spot.getShort_description())
                            .title(spot.getName()));
                    sessionModel.getNewSpots().putMarker(marker, spot);
                }

                MapUtils.centerMapOn(googleMap, latLngOfLast, MapUtils.MAP_ZOOM_LEVEL);
            }
        });

    }

    private SupportMapFragment getMapFragment(){
        return (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Spot spotClicked = getSpotAssociated(marker);
        launchDetailsActivity(spotClicked);
    }

    private Spot getSpotAssociated(Marker marker) {
        return sessionModel.getNewSpots().getSpot(marker);
    }

    private void launchDetailsActivity(Spot spotClicked) {
        Intent intent = AddNewSpotDetailsActivity.newIntent(this);
        intent.putExtra(AddNewSpotDetailsActivity.BUNDLE_KEY_SPOT, spotClicked);
        startActivity(intent);
    }

    private void initializeMap() {
        MapUtils.performOnMap(getMapFragment(), new MapUtils.PerformOnMap() {
            @Override
            public void perform(final GoogleMap googleMap) {
                googleMap.setOnInfoWindowClickListener(AddNewSpotMapActivity.this);
                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {
                        onMapClickForNewSpot(latLng, googleMap);
                    }
                });
            }
        });
    }

    private void onMapClickForNewSpot(LatLng latLng, GoogleMap googleMap) {
        sessionModel.getNewSpots().removeSelectedSpotIfExists();
        Marker selectedNewSpot = googleMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        sessionModel.getNewSpots().addNewSelectedSpot(selectedNewSpot);
        AnimationUtils.showButtonNext((Button) ViewUtils.findView(this, R.id.button_add_new_spot));
    }



    protected LatLng getLatLngOfLast(List<Spot> spots) {
        return spots.get(spots.size() - 1).getLatLng();
    }
}
