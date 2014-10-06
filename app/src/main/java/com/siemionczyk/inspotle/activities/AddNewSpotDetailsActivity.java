package com.siemionczyk.inspotle.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.LinearLayout;

import com.siemionczyk.inspotle.R;
import com.siemionczyk.inspotle.ui.SportActivitiesIconContainerManySelected;
import com.siemionczyk.inspotle.utils.ViewUtils;

/**
 * Created by michalsiemionczyk on 05/10/14.
 */
public class AddNewSpotDetailsActivity extends FragmentActivity {


    SportActivitiesIconContainerManySelected iconContainer = new SportActivitiesIconContainerManySelected();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_spot_details);

        insertImages();


    }


    private void insertImages() {
        LinearLayout activitiesIconsLayout = ViewUtils.findView(this, R.id.layout_sport_activities);

        iconContainer.insertActivities(1, R.drawable.icon_activity_basketball_pressed, R.drawable.icon_activity_basketball, activitiesIconsLayout );
        iconContainer.insertActivities(2, R.drawable.icon_activity_volleyball_pressed, R.drawable.icon_activity_volleyball, activitiesIconsLayout );
        iconContainer.insertActivities(3, R.drawable.icon_activity_football_pressed, R.drawable.icon_activity_football, activitiesIconsLayout );
    }
}
