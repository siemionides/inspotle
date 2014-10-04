package com.siemionczyk.inspotle.ui;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.siemionczyk.inspotle.R;
import com.siemionczyk.inspotle.utils.ViewUtils;

import java.util.HashSet;

import lombok.Value;

/**
 * Created by michalsiemionczyk on 03/10/14.
 */
public class SportActivitiesIconContainer {


    HashSet<SportActivityDrawables> activitiesDrawables;


    private int currentlySelectedActivity = -1;


    public SportActivitiesIconContainer() {
        activitiesDrawables = new HashSet<SportActivityDrawables>();
    }


    public void insertActivities(final int activityId, int drawablePrssedId, int drawableNonPressedId, LinearLayout containerView) {
        ImageView imageVIew = insertAndReturnImage(drawableNonPressedId, containerView);

        imageVIew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onIconClick(activityId);
            }
        });

        activitiesDrawables.add(new SportActivityDrawables(activityId, drawablePrssedId, drawableNonPressedId, imageVIew));


        if (currentlySelectedActivity == -1){
            currentlySelectedActivity = activityId;
            imageVIew.setImageResource(drawablePrssedId);
        }

    }


    private ImageView insertAndReturnImage(int imageDrawable, LinearLayout containerView) {
        ImageView iv = new ImageView(containerView.getContext());

        int iconSize = (int) containerView.getResources().getDimension(R.dimen.sport_activity_icon_size);



        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(iconSize, iconSize);
        params.setMargins(15, 0, 15, 0);
        iv.setLayoutParams(params);
        iv.setImageResource(imageDrawable);
        ViewUtils.addView(containerView, iv);
        return iv;
    }

    private void onIconClick(int clickedActivityId) {
        currentlySelectedActivity = clickedActivityId;
        setProperDrawablesForIconsView(clickedActivityId);
    }

    private void setProperDrawablesForIconsView(int clickedActivityId) {
        for (SportActivityDrawables sportActivityDrawables : activitiesDrawables) {
            if (sportActivityDrawables.activityId == clickedActivityId) {
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

    @Value
    private class SportActivityDrawables {
        int activityId;
        int drawablePressed;
        int drawableNonPressed;
        ImageView imageView;
    }


}
