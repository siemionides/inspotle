package com.siemionczyk.inspotle.fragments;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.siemionczyk.inspotle.events.SpotsResponseEvent;
import com.siemionczyk.inspotle.model.Spot;
import com.siemionczyk.inspotle.utils.MapUtils;

/**
 * Created by michalsiemionczyk on 18/09/14.
 */
public class CreateNewGameMapFragment extends TabMapFragment {


    @Override
    public void onEvent(SpotsResponseEvent event) {

        for (Spot spot : event.getSpots()) {
            Marker marker = getMap().addMarker(new MarkerOptions()
                    .position(spot.getLatLng())
                    .snippet(spot.getShort_description())
                    .title(spot.getName()));
        }

        LatLng latLngOfLast =
                getLatLngOfLast(event.getSpots());
        MapUtils.centerMapOn(getMap(), latLngOfLast, MapUtils.MAP_ZOOM_LEVEL);
    }
}
