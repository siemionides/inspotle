package com.siemionczyk.inspotle.utils;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by michalsiemionczyk on 22/09/14.
 */
public class MapUtils {

    public static final float MAP_ZOOM_LEVEL = 10f;
    public static final float MAP_ZOOM_LEVEL_STRONGER = 13f;


    public static void centerMapOn(GoogleMap map, LatLng location, float mapZoomLevel ) {
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(location, mapZoomLevel));
    }
}
