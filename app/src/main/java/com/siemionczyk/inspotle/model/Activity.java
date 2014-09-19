package com.siemionczyk.inspotle.model;

import hrisey.Parcelable;
import lombok.Value;

/**
 * Created by michalsiemionczyk on 18/09/14.
 */
@Value
@Parcelable
public class Activity implements android.os.Parcelable {

    int id;
    String name;
    String icon_white_url;
    String icon_blue_url;
}
