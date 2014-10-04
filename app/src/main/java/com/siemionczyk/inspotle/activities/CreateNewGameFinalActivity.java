package com.siemionczyk.inspotle.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.siemionczyk.inspotle.R;
import com.siemionczyk.inspotle.ui.SportActivitiesIconContainer;
import com.siemionczyk.inspotle.utils.ViewUtils;

/**
 * Created by michalsiemionczyk on 29/09/14.
 */
public class CreateNewGameFinalActivity extends FragmentActivity {


    SportActivitiesIconContainer iconContainer = new SportActivitiesIconContainer();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_game_final);
        bindControls();
        insertImages();

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

    private void insertImages() {
        LinearLayout activitiesIconsLayout = ViewUtils.findView(this, R.id.layout_sport_activities);

        iconContainer.insertActivities(1, R.drawable.icon_activity_basketball_pressed, R.drawable.icon_activity_basketball, activitiesIconsLayout );
        iconContainer.insertActivities(2, R.drawable.icon_activity_volleyball_pressed, R.drawable.icon_activity_volleyball, activitiesIconsLayout );
        iconContainer.insertActivities(3, R.drawable.icon_activity_football_pressed, R.drawable.icon_activity_football, activitiesIconsLayout );
    }
}
