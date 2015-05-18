package com.siemionczyk.inspotle.ui;

import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

/**
 * Created by michalsiemionczyk on 03/10/14.
 */
public class SportActivitiesIconContainerOneOneSelected extends SportActivitiesIconContainer {

    private int currentlySelectedActivity = -1;

    @Override
    public ImageView insertActivities(final int activityId, String drawablePrssedUrl, String drawableNonPressedUrl, LinearLayout containerView) {
        ImageView imageView = super.insertActivities(activityId, drawablePrssedUrl, drawableNonPressedUrl, containerView);

        if (currentlySelectedActivity == -1) {
            currentlySelectedActivity = activityId;
            Picasso.with(imageView.getContext())
                    .load(drawablePrssedUrl)
                    .into(imageView);
        }
        return imageView;
    }


    protected void onIconClick(int clickedActivityId) {
        currentlySelectedActivity = clickedActivityId;
        setProperDrawablesForIconsView(clickedActivityId);
    }

    protected void setProperDrawablesForIconsView(int clickedActivityId) {
        for (SportActivityDrawable sportActivityDrawable : activitiesDrawables) {
            String resourceUrl = getClickedOrNoClickedDrawableUrl(clickedActivityId, sportActivityDrawable);
            ImageView imageView = sportActivityDrawable.getImageView();
            Picasso.with(imageView.getContext())
                    .load(resourceUrl)
                    .into(imageView);

        }
    }

    private String getClickedOrNoClickedDrawableUrl(int clickedActivityId, SportActivityDrawable sportActivityDrawables) {
        String drawableUrl = "";
        if (sportActivityDrawables.getActivityId() == clickedActivityId) {
            drawableUrl = sportActivityDrawables.getDrawablePressed();
        } else {
            drawableUrl = sportActivityDrawables.getDrawableNonPressed();
        }
        return drawableUrl;
    }

}
