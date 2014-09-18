package com.siemionczyk.inspotle.events;

import com.siemionczyk.inspotle.model.Activity;

import java.util.List;

import lombok.Value;

/**
 * Created by michalsiemionczyk on 18/09/14.
 */
@Value
public class ActivitiesResponseEvent {

    List<Activity> activities;
}
