package com.siemionczyk.inspotle.model;

import java.util.List;

import lombok.Value;

/**
 * Created by michalsiemionczyk on 19/09/14.
 */
@Value
public class Event {
    int id;
    String event_type;
    String starts_at;
    User user;
    Activity activity;
    List<User> users;
}
