package com.siemionczyk.inspotle.fragments;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.siemionczyk.inspotle.R;
import com.siemionczyk.inspotle.SingleSpotActivity;
import com.siemionczyk.inspotle.model.Spot;
import com.siemionczyk.inspotle.utils.MapUtils;
import com.siemionczyk.inspotle.utils.ViewUtils;

/**
 * Created by michalsiemionczyk on 19/09/14.
 */
public class SingleSpotInfoFragment extends Fragment {

    public static final String TAG = SingleSpotInfoFragment.class.getSimpleName();

    SupportMapFragment mMapFragment;

    GoogleMap gMap;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_single_spot_info, container, false);


        mMapFragment = SupportMapFragment.newInstance();
        getFragmentManager()
                .beginTransaction()
                .add(R.id.map_container, mMapFragment)
                .commit();

        fillInValues(getSpot(), rootView);

        return rootView;
    }

    private void fillInValues(Spot spot, View rootView) {
        ViewUtils.setText(spot.getName(), rootView, R.id.tv_spot_name);
        ViewUtils.setText(spot.getAddress1() + " " + spot.getAddress2(), rootView, R.id.tv_spot_address);
        ViewUtils.setText(spot.getLatitude() + " " + spot.getLongitude(), rootView, R.id.tv_distance_from);
        ViewUtils.setText(getNrEvents(spot.getEvents().size()), rootView, R.id.tv_events_nr);
        ViewUtils.setText(spot.getShort_description(), rootView, R.id.tv_short_description);

        Log.i(TAG, "on CreateVIew:" + mMapFragment.getMap());
    }


    @Override
    public void onResume() {
        super.onResume();
        doMapCheck();
        Log.i(TAG, "onResume:" + mMapFragment.getMap());
    }

    private void doMapCheck() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (gMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            gMap = mMapFragment.getMap();
            // Check if we were successful in obtaining the map.
            if (gMap != null) {

                setUpMap( getSpot() );
            }
        }
    }

    public GoogleMap getgMap() {
        return gMap;
    }

    private void setUpMap(Spot spot) {
        Marker marker = getgMap().addMarker(new MarkerOptions()
                .position(spot.getLatLng())
                .snippet(spot.getShort_description())
                .title(spot.getName()));

        MapUtils.centerMapOn(getgMap(), spot.getLatLng(), MapUtils.MAP_ZOOM_LEVEL_STRONGER );
    }



    private String getNrEvents(int nrEvents){
        Resources res = getActivity().getResources();
        return res.getQuantityString(R.plurals.numberOfEvents, nrEvents, nrEvents);
    }

    private Spot getSpot(){
        return ((SingleSpotActivity)getActivity()).getSpot();
    }
}
