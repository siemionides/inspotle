package com.siemionczyk.inspotle.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.siemionczyk.inspotle.R;
import com.siemionczyk.inspotle.model.Activity;
import com.siemionczyk.inspotle.utils.ViewUtils;
import com.squareup.picasso.Picasso;

import java.util.HashSet;

import lombok.Value;

/**
 * Created by michalsiemionczyk on 03/10/14.
 */
public abstract class SportActivitiesIconContainer {

    public ImageView insertActivities(final Activity activity, LinearLayout containerView) {
        ImageView imageVIew = insertAndReturnImage(activity.getIconNonPressed(), containerView);
        imageVIew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onIconClick((ImageView) v, activity);
            }
        });
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

    protected abstract void onIconClick(ImageView imageView, Activity clickedActivity);

    protected void setProperDrawablesForIconsView(boolean wasSelected, ImageView imageView, Activity activity) {
        String urlWithDrawable = "";
        if (wasSelected){
            urlWithDrawable = activity.getIconPressed();
            imageView.setBackgroundColor(imageView.getResources().getColor(R.color.button_pressed));
        } else {
            urlWithDrawable = activity.getIconNonPressed();
            imageView.setBackgroundColor(imageView.getResources().getColor(android.R.color.transparent));
        }

        Picasso.with(imageView.getContext())
                .load(urlWithDrawable)
                .into(imageView);
    }
}
