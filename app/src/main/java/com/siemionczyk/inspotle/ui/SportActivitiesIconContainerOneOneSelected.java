package com.siemionczyk.inspotle.ui;

import android.widget.ImageView;
import android.widget.LinearLayout;

import com.siemionczyk.inspotle.model.Activity;
import com.squareup.picasso.Picasso;

/**
 * Created by michalsiemionczyk on 03/10/14.
 */
public class SportActivitiesIconContainerOneOneSelected extends SportActivitiesIconContainer {

    private int currentlySelectedActivityId = -1;

    @Override
    public ImageView insertActivities(final Activity activity, LinearLayout containerView) {
        ImageView imageView = super.insertActivities(activity, containerView);

        if (currentlySelectedActivityId == -1) {
            currentlySelectedActivityId = activity.getId();
            Picasso.with(imageView.getContext())
                    .load(activity.getIconPressed())
                    .into(imageView);
        }
        return imageView;
    }


    protected void onIconClick(ImageView imageView, Activity clickedActivity) {
        boolean wasSelected = clickedActivity.getId() == currentlySelectedActivityId;
        currentlySelectedActivityId = clickedActivity.getId();
        setProperDrawablesForIconsView(wasSelected, imageView, clickedActivity);
    }
}
