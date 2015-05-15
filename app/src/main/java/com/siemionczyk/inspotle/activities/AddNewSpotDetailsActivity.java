package com.siemionczyk.inspotle.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.gms.maps.model.LatLng;
import com.siemionczyk.inspotle.R;
import com.siemionczyk.inspotle.api.InspotleApiClient;
import com.siemionczyk.inspotle.events.SpotUpdatedEvent;
import com.siemionczyk.inspotle.model.Activity;
import com.siemionczyk.inspotle.model.Spot;
import com.siemionczyk.inspotle.ui.SportActivitiesIconContainerManySelected;
import com.siemionczyk.inspotle.utils.ResourcesUtils;
import com.siemionczyk.inspotle.utils.ViewUtils;

import de.greenrobot.event.EventBus;

/**
 * Created by michalsiemionczyk on 05/10/14.
 */
public class AddNewSpotDetailsActivity extends FragmentActivity {


    public static final String BUNDLE_KEY_NEW_POSITION = "bundle_key_position";

    SportActivitiesIconContainerManySelected iconContainer = new SportActivitiesIconContainerManySelected();

    public static void start(Context ctx, LatLng newMarkerPosition){
        Intent intent = new Intent(ctx, AddNewSpotDetailsActivity.class);
        intent.putExtra(AddNewSpotDetailsActivity.BUNDLE_KEY_NEW_POSITION, newMarkerPosition);
        ctx.startActivity(intent);
    }

    public static Intent newIntent(Context ctx) {
        return new Intent(ctx, AddNewSpotDetailsActivity.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_spot_details);

        LatLng position = getIntent().getParcelableExtra(BUNDLE_KEY_NEW_POSITION);

        fillViewData(position);

        configureViews();


    }

    @SuppressWarnings("ununsed")
    public void onEvent(SpotUpdatedEvent event) {

    }

    private void configureViews() {
        ViewUtils.setOnClickListener(this, R.id.button_save_place, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSavePlaceButtonPressed();
            }
        });
    }

    private void onSavePlaceButtonPressed() {
        String name = ViewUtils.getText(this, R.id.ed_name);
//        InspotleApiClient.getInstance().updateSpot(, name);
    }

    private void fillViewData(LatLng spot) {
//        fillActivitiesIcons(spot);

/*


        ViewUtils.setText(spot.getName(), this, R.id.ed_name);
        ViewUtils.setText(spot.getAddress1(), this, R.id.ed_street);
        ViewUtils.setText(spot.getAddress2(), this, R.id.ed_city);
        ViewUtils.setText(spot.getShort_description(), this, R.id.ed_description);*/
    }

    private void fillActivitiesIcons(Spot spot) {
        LinearLayout activitiesIconsLayout = ViewUtils.findView(this, R.id.layout_sport_activities);
        for (Activity activity : spot.getActivities()) {
            int pressedDrawableId = ResourcesUtils.convertAcitivityIdIntoPressedIconDrawableId(activity.getId());
            int nonPressedDrawableId = ResourcesUtils.convertAcitivityIdIntoNonPressedIconDrawableId(activity.getId());
            iconContainer.insertActivities(activity.getId(), pressedDrawableId, nonPressedDrawableId, activitiesIconsLayout);
        }
    }
}
