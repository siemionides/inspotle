package com.siemionczyk.inspotle.model;

import com.google.android.gms.maps.model.Marker;

import java.util.HashMap;

public class NewSpots {
    HashMap<Marker, Spot> markerData = new HashMap<Marker, Spot>();
    Marker selectedNewSpot = null;

    public void putMarker(Marker marker, Spot spot){
        markerData.put(marker, spot);
    }

    public Spot getSpot(Marker m){
        return markerData.get(m);
    }

    public Marker getSelectedNewSpot(){
        return selectedNewSpot;
    }

    public void removeSelectedSpotIfExists(){
        if (selectedNewSpot != null){
            selectedNewSpot.remove();
        }
    }

    public void addNewSelectedSpot(Marker marker){
        selectedNewSpot = marker;
    }

}
