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

    public static int convertAcitivityIdIntoPressedIconDrawableId(int activityId){
        switch (activityId){
            case 1: return R.drawable.icon_activity_football_pressed;
            case 2: return R.drawable.icon_activity_basketball_pressed;
            case 3: return R.drawable.icon_activity_volleyball_pressed;
            default: return R.drawable.icon_activity_default_pressed;
        }
    }

    public static int convertAcitivityIdIntoNonPressedIconDrawableId(int activityId){
        switch (activityId){
            case 1: return R.drawable.icon_activity_football;
            case 2: return R.drawable.icon_activity_basketball;
            case 3: return R.drawable.icon_activity_volleyball;
            default: return R.drawable.icon_activity_default;
        }
    }
}
