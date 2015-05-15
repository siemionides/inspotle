package com.siemionczyk.inspotle.model;

import com.google.android.gms.maps.model.Marker;

import java.util.HashMap;

public class NewSpots {
    HashMap<Marker, Spot> markerData = new HashMap<Marker, Spot>();
    Marker selectedNewMarker = null;

    public void putMarker(Marker marker, Spot spot){
        markerData.put(marker, spot);
    }

    public Spot getSpot(Marker m){
        return markerData.get(m);
    }

    public Spot getSpotForSelectedMarker(){
        return getSpot(getSelectedNewMarker());
    }

    public Marker getSelectedNewMarker(){
        return selectedNewMarker;
    }

    public void removeSelectedMarkerIfExists(){
        if (selectedNewMarker != null){
            selectedNewMarker.remove();
        }
    }

    public void addNewSelectedMarker(Marker marker){
        selectedNewMarker = marker;
    }

}
