package com.siemionczyk.inspotle.api;

import com.siemionczyk.inspotle.model.Activities;
import com.siemionczyk.inspotle.model.Spots;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by michalsiemionczyk on 18/09/14.
 */
public interface InspotleApi {


    @GET("/activities.json")
    void getActivities(Callback<Activities> callback);

    @GET("/spots.json")
    void getSpots(Callback<Spots> callback);

}
