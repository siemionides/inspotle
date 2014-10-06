package com.siemionczyk.inspotle.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.siemionczyk.inspotle.R;
import com.siemionczyk.inspotle.utils.ViewUtils;

import java.util.HashSet;

import lombok.Value;

/**
 * Created by michalsiemionczyk on 03/10/14.
 */
public abstract class SportActivitiesIconContainer {

    protected HashSet<SportActivityDrawables> activitiesDrawables;

    public SportActivitiesIconContainer() {
        activitiesDrawables = new HashSet<SportActivityDrawables>();
    }


    public ImageView insertActivities(final int activityId, int drawablePrssedId, int drawableNonPressedId, LinearLayout containerView) {
        ImageView imageVIew = insertAndReturnImage(drawableNonPressedId, containerView);
        imageVIew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onIconClick(activityId);
            }
        });
        activitiesDrawables.add(new SportActivityDrawables(activityId, drawablePrssedId, drawableNonPressedId, imageVIew));
        return imageVIew;
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

    protected abstract void onIconClick(int clickedActivityId);

    @Value
    protected class SportActivityDrawables {
        int activityId;
        int drawablePressed;
        int drawableNonPressed;
        ImageView imageView;
    }


}
