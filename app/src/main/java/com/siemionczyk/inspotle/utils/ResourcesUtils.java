package com.siemionczyk.inspotle.utils;

import android.content.res.Resources;

import com.siemionczyk.inspotle.R;

/**
 * Created by michalsiemionczyk on 29/09/14.
 */
public class ResourcesUtils {
    public static String getNrEventsText(Resources resources, int nrEvents) {
        return resources.getQuantityString(R.plurals.numberOfEvents, nrEvents, nrEvents);
    }
}
