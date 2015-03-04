package com.siemionczyk.inspotle.model;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import hrisey.Parcelable;
import lombok.Value;

/**
 * Created by michalsiemionczyk on 19/09/14.
 */
@Value
@Parcelable
public class Spot implements android.os.Parcelable {
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
    List<Activity> activities;
    List<Event> events;

    public LatLng getLatLng(){
        double latitude = Double.valueOf(this.latitude);
        double longitude = Double.valueOf(this.longitude);
        return new LatLng(latitude, longitude);
    }
}
