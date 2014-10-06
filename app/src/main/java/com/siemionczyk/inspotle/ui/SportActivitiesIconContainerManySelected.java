package com.siemionczyk.inspotle.ui;

import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.HashSet;

/**
 * Created by michalsiemionczyk on 03/10/14.
 */
public class SportActivitiesIconContainerManySelected extends SportActivitiesIconContainer {

    private HashSet<Integer> currentlySelectedActivities = new HashSet<Integer>();

    @Override
    public ImageView insertActivities(final int activityId, int drawablePrssedId, int drawableNonPressedId, LinearLayout containerView) {
        ImageView imageView = super.insertActivities(activityId, drawablePrssedId, drawableNonPressedId, containerView);


        if (currentlySelectedActivities.size() == 0) {
            currentlySelectedActivities.add(activityId);
            imageView.setImageResource(drawablePrssedId);
        }
        return imageView;
    }


    protected void onIconClick(int clickedActivityId) {

        boolean wasSelected = currentlySelectedActivities.contains(clickedActivityId);
        if (wasSelected) {
            currentlySelectedActivities.remove(clickedActivityId);
        } else {
            currentlySelectedActivities.add(clickedActivityId);
        }
        setProperDrawablesForIconsView(currentlySelectedActivities);
    }

    protected void setProperDrawablesForIconsView(HashSet<Integer> clickedActivitiesIds) {
        for (SportActivityDrawables sportActivityDrawables : activitiesDrawables) {
            if (clickedActivitiesIds.contains(sportActivityDrawables.getActivityId())) {
                int clickedResource = sportActivityDrawables.getDrawablePressed();
                sportActivityDrawables.getImageView().setImageResource(clickedResource);
            } else {
                int nonClickedResource = sportActivityDrawables.getDrawableNonPressed();
                sportActivityDrawables.getImageView().setImageResource(nonClickedResource);
            }
        }
    }

    public HashSet<Integer> getCurrentlySelectedActivity() {
        return currentlySelectedActivities;
    }
}
