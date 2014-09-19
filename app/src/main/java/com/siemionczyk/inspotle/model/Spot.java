package com.siemionczyk.inspotle.model;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import lombok.Value;

/**
 * Created by michalsiemionczyk on 19/09/14.
 */
@Value
public class Spot {
    int id;
    String name;
    String latitude;
    String longitude;
    String short_description;
    String address1;
    String address2;
    String post_code;
    String city;
    String country;
    List<Event> events;


    public LatLng getLatLng(){
        double latitude = Double.valueOf(this.latitude);
        double longitude = Double.valueOf(this.longitude);
        return new LatLng(latitude, longitude);
    }
}
