package com.siemionczyk.inspotle.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;

import com.siemionczyk.inspotle.R;
import com.siemionczyk.inspotle.fragments.SingleSpotEventsFragment;
import com.siemionczyk.inspotle.fragments.SingleSpotInfoFragment;
import com.siemionczyk.inspotle.model.Spot;
import com.siemionczyk.inspotle.utils.ViewUtils;

/**
 * Created by michalsiemionczyk on 19/09/14.
 */
public class SingleSpotActivity extends FragmentActivity {


    private final static String TAG = SingleSpotActivity.class.getSimpleName();

    public static final String BUNDLE_SPOT_KEY = "Spot Key";

    Fragment singleSpotInfoFragment = new SingleSpotInfoFragment();

    Fragment singleSpotEventsFragment = new SingleSpotEventsFragment();

    private Spot spot;

    private final static String TAG_INFO = "tag_info";

    private final static String TAG_EVENTS = "tag_events";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_spot);


        ViewUtils.setOnClickListener(this, R.id.tab_info, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                changeTabsBackgroudColor(
                        ViewUtils.findView(SingleSpotActivity.this, R.id.tab_info),
                        ViewUtils.findView(SingleSpotActivity.this, R.id.tab_events)
                );
                replaceFragment(singleSpotInfoFragment, TAG_INFO);
            }
        });

        ViewUtils.setOnClickListener(this, R.id.tab_events, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeTabsBackgroudColor(
                        ViewUtils.findView(SingleSpotActivity.this, R.id.tab_events),
                        ViewUtils.findView(SingleSpotActivity.this, R.id.tab_info)
                );
                replaceFragment(singleSpotEventsFragment, TAG_EVENTS);
            }
        });

        replaceFragment(singleSpotInfoFragment, TAG_INFO);

        spot = getIntent().getParcelableExtra(BUNDLE_SPOT_KEY);
    }



    public Spot getSpot() {
        return spot;
    }

    private void changeTabsBackgroudColor(View activeTab, View nonActiveTAb) {
        activeTab.setBackgroundColor(getResources().getColor(R.color.button_pressed));
        nonActiveTAb.setBackgroundColor(getResources().getColor(R.color.button_not_pressed));
    }

    private void replaceFragment(Fragment fragment, String tag) {

        FragmentManager fm = getSupportFragmentManager();

        Fragment fragmentFound = fm.findFragmentByTag(tag);
        if (fragmentFound != null) {
            Log.d(TAG, "Fragment Was found:" + fragmentFound);
            fragment = fragmentFound;
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_single_spot_container, fragment, tag)
                .commit();
    }

}
