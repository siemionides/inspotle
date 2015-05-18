package com.siemionczyk.inspotle.model;

import com.google.gson.annotations.SerializedName;

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
    @SerializedName("icon_white_url") String iconPressed;
    @SerializedName("icon_blue_url") String iconNonPressed;
}
