package com.siemionczyk.inspotle.ui;

import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import java.util.HashSet;

/**
 * Created by michalsiemionczyk on 03/10/14.
 */
public class SportActivitiesIconContainerManySelected extends SportActivitiesIconContainer {

    private HashSet<Integer> currentlySelectedActivities = new HashSet<Integer>();

    @Override
    public ImageView insertActivities(final int activityId, String drawablePrssedUrl, String drawableNonPressedUrl, LinearLayout containerView) {
        ImageView imageView = super.insertActivities(activityId, drawablePrssedUrl, drawableNonPressedUrl, containerView);

        if (currentlySelectedActivities.size() == 0) {
            currentlySelectedActivities.add(activityId);
            Picasso.with(imageView.getContext())
                    .load(drawablePrssedUrl)
                    .into(imageView);
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
        setProperDrawablesForIconsView();
    }

    protected void setProperDrawablesForIconsView() {
        for (SportActivityDrawable sportActivityDrawable : activitiesDrawables) {
            String resourceUrl = getClickedOrNonClickedUrl(
                    sportActivityDrawable);
            ImageView imageView = sportActivityDrawable.getImageView();

            Picasso.with(imageView.getContext())
                    .load(resourceUrl)
                    .into(imageView);
        }
    }

    private String getClickedOrNonClickedUrl(SportActivityDrawable sportActivityDrawable) {
        String resourceUrl;
        if (currentlySelectedActivities.contains(sportActivityDrawable.getActivityId())) {
            resourceUrl = sportActivityDrawable.getDrawablePressed();
        } else {
            resourceUrl = sportActivityDrawable.getDrawableNonPressed();
        }
        return resourceUrl;
    }
}
