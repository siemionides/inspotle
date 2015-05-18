package com.siemionczyk.inspotle.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.siemionczyk.inspotle.R;
import com.siemionczyk.inspotle.utils.ViewUtils;
import com.squareup.picasso.Picasso;

import java.util.HashSet;

import lombok.Value;

/**
 * Created by michalsiemionczyk on 03/10/14.
 */
public abstract class SportActivitiesIconContainer {

    protected HashSet<SportActivityDrawable> activitiesDrawables;

    public SportActivitiesIconContainer() {
        activitiesDrawables = new HashSet<SportActivityDrawable>();
    }


    public ImageView insertActivities(final int activityId, String drawablePrssedUrl, String drawableNonPressedUrl, LinearLayout containerView) {
        ImageView imageVIew = insertAndReturnImage(drawableNonPressedUrl, containerView);
        imageVIew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onIconClick(activityId);
            }
        });
        activitiesDrawables.add(new SportActivityDrawable(activityId, drawablePrssedUrl, drawableNonPressedUrl, imageVIew));
        return imageVIew;
    }


    private ImageView insertAndReturnImage(String imageDrawableUrl, LinearLayout containerView) {
        ImageView iv = new ImageView(containerView.getContext());

        int iconSize = (int) containerView.getResources().getDimension(R.dimen.sport_activity_icon_size);

        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(iconSize, iconSize);
        params.setMargins(15, 0, 15, 0);
        iv.setLayoutParams(params);

        Picasso.with(iv.getContext())
                .load(imageDrawableUrl)
                .into(iv);

        ViewUtils.addView(containerView, iv);
        return iv;
    }

    protected abstract void onIconClick(int clickedActivityId);

    @Value
    protected class SportActivityDrawable {
        int activityId;
        String drawablePressed;
        String drawableNonPressed;
        ImageView imageView;
    }


}
