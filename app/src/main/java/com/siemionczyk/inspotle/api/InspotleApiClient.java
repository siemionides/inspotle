package com.siemionczyk.inspotle.api;

import android.util.Log;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.siemionczyk.inspotle.events.ActivitiesResponseEvent;
import com.siemionczyk.inspotle.events.SpotsResponseEvent;
import com.siemionczyk.inspotle.model.Activity;
import com.siemionczyk.inspotle.model.Spot;

import java.util.List;

import de.greenrobot.event.EventBus;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

/**
 * Created by michalsiemionczyk on 18/09/14.
 */


public final class InspotleApiClient {

    private static final String ENDPOINT = "http://inspottle.herokuapp.com:80/api";
    public static final String TAG = InspotleApiClient.class.getSimpleName();

    private InspotleApi api;

    private static InspotleApiClient instance;

    public static InspotleApiClient getInstance(){
        if (instance == null){
            instance = new InspotleApiClient();
        }
        return  instance;
    }

    private InspotleApiClient(){

        Gson gson =  new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();


        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(ENDPOINT)
//                .setRequestInterceptor(createRequestInterceptor(prefs))
                .setConverter(new GsonConverter(gson))
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        api = restAdapter.create(InspotleApi.class);
    }


    public void getActivities(){
        api.getActivities(new Callback<List<Activity>>() {
            @Override
            public void success(List<Activity> activities, Response response) {
                Log.d(TAG, "success");
                EventBus.getDefault().post( new ActivitiesResponseEvent(activities));
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(TAG, "failure:" + error.getMessage());

            }
        });
    }


    public void getSpots(){
        api.getSpots(new Callback<List<Spot>>() {
            @Override
            public void success(List<Spot> spots, Response response) {
                Log.d(TAG, "success");
                EventBus.getDefault().post( new SpotsResponseEvent(spots));

            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(TAG, "failure:" + error.getMessage());

            }
        });

    }



}
