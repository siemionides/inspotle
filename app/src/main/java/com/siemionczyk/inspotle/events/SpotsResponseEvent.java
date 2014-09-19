package com.siemionczyk.inspotle.events;

import com.siemionczyk.inspotle.model.Spot;

import java.util.List;

import lombok.Value;

/**
 * Created by michalsiemionczyk on 19/09/14.
 */
@Value
public class SpotsResponseEvent {
    List<Spot> spots;
}
