package com.siemionczyk.inspotle.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.siemionczyk.inspotle.R;
import com.siemionczyk.inspotle.api.InspotleApiClient;
import com.siemionczyk.inspotle.events.ActivitiesResponseEvent;
import com.siemionczyk.inspotle.model.Activity;
import com.siemionczyk.inspotle.ui.SportActivitiesIconContainerOneOneSelected;
import com.siemionczyk.inspotle.utils.ViewUtils;

import java.util.List;

/**
 * Created by michalsiemionczyk on 29/09/14.
 */
public class CreateNewGameFinalActivity extends FragmentActivity {


    SportActivitiesIconContainerOneOneSelected iconContainer = new SportActivitiesIconContainerOneOneSelected();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_game_final);
        bindControls();
        bindViews();
    }

    private void bindViews() {
        insertImagesForActivitiesAsynchronously();
    }

    private void insertImagesForActivitiesAsynchronously() {
        InspotleApiClient.getInstance().getActivities();
    }

    @SuppressWarnings("ununsed")
    public void onEvent(ActivitiesResponseEvent event) {
        insertImagesForActivities(event.getActivities());
    }

    private void bindControls() {
        bindOnEditDateDecheckRadio();
    }

    private void bindOnEditDateDecheckRadio() {
        ViewUtils.setOnClickListener(this, R.id.et_date_edittext, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((RadioButton) ViewUtils.findView(CreateNewGameFinalActivity.this, R.id.radio_now)).setChecked(false);
            }
        });
    }

    private void insertImagesForActivities(List<Activity> activities) {
        LinearLayout activitiesIconsLayout = ViewUtils.findView(this, R.id.layout_sport_activities);
        for (Activity activity : activities) {
            iconContainer.insertActivities(activity, activitiesIconsLayout);
        }
    }
}
