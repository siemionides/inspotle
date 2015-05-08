package com.siemionczyk.inspotle.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.siemionczyk.inspotle.R;
import com.siemionczyk.inspotle.api.InspotleApiClient;
import com.siemionczyk.inspotle.events.SpotsResponseEvent;
import com.siemionczyk.inspotle.model.Spot;
import com.siemionczyk.inspotle.utils.MapUtils;

import java.util.HashMap;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by michalsiemionczyk on 04/10/14.
 */
public class AddNewSpotMapActivity extends FragmentActivity implements GoogleMap.OnInfoWindowClickListener {

    SupportMapFragment mMapFragment;

    GoogleMap gMap;

    HashMap<Marker, Spot> markerData = new HashMap<Marker, Spot>();

    Marker selectedNewSpot = null;


    public static Intent newIntent(Context ctx) {
        return new Intent(ctx, AddNewSpotMapActivity.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        doMapCheck();
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
        bindViews();
        InspotleApiClient.getInstance().getSpots();
    }

    protected GoogleMap getMap() {
        return gMap;
    }

    @SuppressWarnings("unused")
    public void onEvent(SpotsResponseEvent event) {
        for (Spot spot : event.getSpots()) {
            Marker marker = getMap().addMarker(new MarkerOptions()
                    .position(spot.getLatLng())
                    .snippet(spot.getShort_description())
                    .title(spot.getName()));
            markerData.put(marker, spot);
        }
        centerMapOnLastSpot(event);
    }

    @Override
    public void onInfoWindowClick(Marker marker) {

        Spot spotClicked = getSpotAssociated(marker);
        launchDetailsActivity(spotClicked);
    }

    private Spot getSpotAssociated(Marker marker) {
        return markerData.get(marker);
    }

    public void setgMap(GoogleMap gMap) {
        this.gMap = gMap;
    }


    private void bindViews() {
        mMapFragment = SupportMapFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.map_container, mMapFragment)
                .commit();
    }

    private void centerMapOnLastSpot(SpotsResponseEvent event) {
        LatLng latLngOfLast =
                getLatLngOfLast(event.getSpots());
        MapUtils.centerMapOn(getMap(), latLngOfLast, MapUtils.MAP_ZOOM_LEVEL);
    }

    private void doMapCheck() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (getMap() == null) {
            // Try to obtain the map from the SupportMapFragment.
            setgMap(mMapFragment.getMap());
            // Check if we were successful in obtaining the map.
            if (getMap() != null) {
                initializeMap();
            }
        }
    }

    private void launchDetailsActivity(Spot spotClicked) {
        Intent intent = AddNewSpotDetailsActivity.newIntent(this);
        intent.putExtra(AddNewSpotDetailsActivity.BUNDLE_KEY_SPOT, spotClicked);
        startActivity(intent);
    }

    private void initializeMap() {
        getMap().setOnInfoWindowClickListener(this);
        getMap().setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                onMapClickForNewSpot(latLng);
            }
        });
    }

    private void onMapClickForNewSpot(LatLng latLng){
        if (selectedNewSpot != null ){ selectedNewSpot.remove();}
        selectedNewSpot = getMap().addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
    }

    protected LatLng getLatLngOfLast(List<Spot> spots) {
        return spots.get(spots.size() - 1).getLatLng();
    }
}
