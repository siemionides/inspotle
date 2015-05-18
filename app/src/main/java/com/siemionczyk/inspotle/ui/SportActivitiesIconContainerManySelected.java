package com.siemionczyk.inspotle.ui;

import android.widget.ImageView;
import android.widget.LinearLayout;

import com.siemionczyk.inspotle.R;
import com.siemionczyk.inspotle.model.Activity;
import com.squareup.picasso.Picasso;

import java.util.HashSet;

/**
 * Created by michalsiemionczyk on 03/10/14.
 */
public class SportActivitiesIconContainerManySelected extends SportActivitiesIconContainer {

    private HashSet<Integer> currentlySelectedActivities = new HashSet<Integer>();

    @Override
    public ImageView insertActivities(final Activity activity, LinearLayout containerView) {
        ImageView imageView = super.insertActivities(activity, containerView);

        if (currentlySelectedActivities.size() == 0) {
            currentlySelectedActivities.add(activity.getId());
            Picasso.with(imageView.getContext())
                    .load(activity.getIconPressed())
                    .into(imageView);
        }
        return imageView;
    }
    protected void onIconClick(ImageView imageView, Activity clickedActivity) {

        boolean wasSelected = currentlySelectedActivities.contains(clickedActivity.getId());
        if (wasSelected) {
            currentlySelectedActivities.remove(clickedActivity.getId());
        } else {
            currentlySelectedActivities.add(clickedActivity.getId());
        }
        setProperDrawablesForIconsView(wasSelected, imageView, clickedActivity);
    }
}
