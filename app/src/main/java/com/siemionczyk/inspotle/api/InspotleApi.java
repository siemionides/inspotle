package com.siemionczyk.inspotle.api;

import com.siemionczyk.inspotle.model.Activity;
import com.siemionczyk.inspotle.model.Spot;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Created by michalsiemionczyk on 18/09/14.
 */
public interface InspotleApi {


    @GET("/activities.json")
    void getActivities(Callback<List<Activity>> callback);

    @GET("/spots.json")
    void getSpots(Callback<List<Spot>> callback);

    @PUT("/spots/{id}.json")
    void updateSpot(@Path("id") int spotId, String spotName,  Callback callback);
}
