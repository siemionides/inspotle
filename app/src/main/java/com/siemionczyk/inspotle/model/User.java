package com.siemionczyk.inspotle.model;

import hrisey.Parcelable;
import lombok.Value;

/**
 * Created by michalsiemionczyk on 19/09/14.
 */
@Value
@Parcelable
public class User implements android.os.Parcelable {
    int id;
    String name;
}
