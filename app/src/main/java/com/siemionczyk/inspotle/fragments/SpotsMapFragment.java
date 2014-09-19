package com.siemionczyk.inspotle.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.siemionczyk.inspotle.R;
import com.siemionczyk.inspotle.api.InspotleApiClient;
import com.siemionczyk.inspotle.events.SpotsResponseEvent;
import com.siemionczyk.inspotle.model.Spot;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by michalsiemionczyk on 18/09/14.
 */
public class SpotsMapFragment extends Fragment {


    private GoogleMap mMap;

    private static final float MAP_ZOOM_LEVEL = 10f;



    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);

    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_spots_map, container, false);

        //inject views
        mMap = ((SupportMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.map)).getMap();

        InspotleApiClient.getInstance().getSpots();


        return rootView;
    }


    @SuppressWarnings("unused")
    public void onEvent(SpotsResponseEvent event) {


        for (Spot spot : event.getSpots()) {
            Marker marker = mMap.addMarker(new MarkerOptions()
                    .position(spot.getLatLng())
                    .snippet(spot.getShort_description())
                    .title(spot.getName()));
//                    .icon(BitmapDescriptorFactory.fromBitmap(markerWithText))
//                    .data(notification)
        }




        LatLng latLngOfLast =
            getLatLngOfLast(event.getSpots());

        centerMapOn(latLngOfLast);

    }

    private LatLng getLatLngOfLast(List<Spot> spots) {
        return spots.get( spots.size() - 1).getLatLng();
    }


    private void centerMapOn(LatLng location) {
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, MAP_ZOOM_LEVEL));
    }


}
