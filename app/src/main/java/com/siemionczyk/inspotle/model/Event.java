package com.siemionczyk.inspotle.model;

import java.util.List;

import hrisey.Parcelable;
import lombok.Value;

/**
 * Created by michalsiemionczyk on 19/09/14.
 */
@Value
@Parcelable
public class Event implements android.os.Parcelable {
    int id;
    String event_type;
    String starts_at;
    User user;
    Activity activity;
    List<User> users;
}
