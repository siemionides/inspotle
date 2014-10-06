package com.siemionczyk.inspotle.ui;

import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by michalsiemionczyk on 03/10/14.
 */
public class SportActivitiesIconContainerOneOneSelected extends SportActivitiesIconContainer {

    private int currentlySelectedActivity = -1;

    @Override
    public ImageView insertActivities(final int activityId, int drawablePrssedId, int drawableNonPressedId, LinearLayout containerView) {
        ImageView imageView = super.insertActivities(activityId, drawablePrssedId, drawableNonPressedId, containerView);

        if (currentlySelectedActivity == -1) {
            currentlySelectedActivity = activityId;
            imageView.setImageResource(drawablePrssedId);
        }
        return imageView;
    }


    protected void onIconClick(int clickedActivityId) {
        currentlySelectedActivity = clickedActivityId;
        setProperDrawablesForIconsView(clickedActivityId);
    }

    protected void setProperDrawablesForIconsView(int clickedActivityId) {
        for (SportActivityDrawables sportActivityDrawables : activitiesDrawables) {
            if (sportActivityDrawables.getActivityId() == clickedActivityId) {
                int clickedResource = sportActivityDrawables.getDrawablePressed();
                sportActivityDrawables.getImageView().setImageResource(clickedResource);
            } else {
                int nonClickedResource = sportActivityDrawables.getDrawableNonPressed();
                sportActivityDrawables.getImageView().setImageResource(nonClickedResource);
            }
        }
    }

    public int getCurrentlySelectedActivity() {
        return currentlySelectedActivity;
    }
}
