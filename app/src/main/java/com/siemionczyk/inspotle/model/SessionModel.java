package com.siemionczyk.inspotle.model;

public class SessionModel {

    private static SessionModel instance;

    private static NewSpots newSpots;

    private SessionModel(){
        newSpots = new NewSpots();
    }

    public static SessionModel getInstance(){
        if (instance == null) instance = new SessionModel();
        return instance;
    }

    public NewSpots getNewSpots(){
        return newSpots;
    }





}
