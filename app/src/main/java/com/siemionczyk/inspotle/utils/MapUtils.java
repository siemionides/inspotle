package com.siemionczyk.inspotle.utils;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.siemionczyk.inspotle.R;

/**
 * Created by michalsiemionczyk on 22/09/14.
 */
public class MapUtils {

    public static interface PerformOnMap{
        public void perform(GoogleMap googleMap);
    }

    public static final float MAP_ZOOM_LEVEL = 10f;
    public static final float MAP_ZOOM_LEVEL_STRONGER = 13f;


    public static void centerMapOn(GoogleMap map, LatLng location, float mapZoomLevel ) {
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(location, mapZoomLevel));
    }

    public static void performOnMap(SupportMapFragment supportMapFragment, final PerformOnMap p){
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                p.perform(googleMap);
            }
        });

    }
}

