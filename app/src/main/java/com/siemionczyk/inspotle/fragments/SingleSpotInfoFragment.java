package com.siemionczyk.inspotle.fragments;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.siemionczyk.inspotle.R;
import com.siemionczyk.inspotle.SingleSpotActivity;
import com.siemionczyk.inspotle.model.Spot;
import com.siemionczyk.inspotle.utils.ViewUtils;

/**
 * Created by michalsiemionczyk on 19/09/14.
 */
public class SingleSpotInfoFragment extends Fragment {

    public static final String TAG = SingleSpotInfoFragment.class.getSimpleName();

    SupportMapFragment mMapFragment;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_single_spot_info, container, false);


        mMapFragment = SupportMapFragment.newInstance();
        getFragmentManager()
                .beginTransaction()
                .add(R.id.map_container, mMapFragment)
                .commit();

        Log.d(TAG, "onCreateView");


        Spot spot = ((SingleSpotActivity)getActivity()).getSpot();
        fillInValues(spot, rootView);

        return rootView;
    }

    private void fillInValues(Spot spot, View rootView) {
        ViewUtils.setText(spot.getName(), rootView, R.id.tv_spot_name);
        ViewUtils.setText(spot.getAddress1() + " " + spot.getAddress2(), rootView, R.id.tv_spot_address);
        ViewUtils.setText(spot.getLatitude() + " " + spot.getLongitude(), rootView, R.id.tv_distance_from);
        ViewUtils.setText(getNrEvents(spot.getEvents().size()), rootView, R.id.tv_events_nr);
        ViewUtils.setText(spot.getShort_description(), rootView, R.id.tv_short_description);


//        Marker marker = mMapFragment.getMap().addMarker(new MarkerOptions()
//                .position(spot.getLatLng())
//                .snippet(spot.getShort_description())
//                .title(spot.getName()));

    }

    private String getNrEvents(int nrEvents){
        Resources res = getActivity().getResources();
        return res.getQuantityString(R.plurals.numberOfEvents, nrEvents, nrEvents);
    }
}
