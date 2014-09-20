package com.siemionczyk.inspotle.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.siemionczyk.inspotle.R;
import com.siemionczyk.inspotle.utils.ViewUtils;

/**
 * Created by michalsiemionczyk on 18/09/14.
 */
public class SingleSpotFragment extends Fragment {

    public static final String TAG = SingleSpotFragment.class.getSimpleName();
    Fragment singleSpotInfoFragment = new SingleSpotInfoFragment();

    Fragment singleSpotEventsFragment = new SingleSpotEventsFragment();

    private final static String TAG_INFO = "tag_info";
    private final static String TAG_EVENTS = "tag_events";

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.activity_single_spot, container, false);

//        initializeFragments();

        ViewUtils.setOnClickListener(rootView, R.id.tab_info, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                changeTabsBackgroudColor(
                        ViewUtils.findView(rootView, R.id.tab_info),
                        ViewUtils.findView(rootView, R.id.tab_events)
                );
                replaceFragment(singleSpotInfoFragment, TAG_INFO);
            }
        });

        ViewUtils.setOnClickListener(rootView, R.id.tab_events, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeTabsBackgroudColor(
                        ViewUtils.findView(rootView, R.id.tab_events),
                        ViewUtils.findView(rootView, R.id.tab_info)
                );
                replaceFragment(singleSpotEventsFragment, TAG_EVENTS);
            }
        });

        replaceFragment(singleSpotInfoFragment, TAG_INFO);

        return rootView;
    }

    private void changeTabsBackgroudColor(View activeTab, View nonActiveTAb) {
        activeTab.setBackgroundColor(getResources().getColor(R.color.button_pressed));
        nonActiveTAb.setBackgroundColor(getResources().getColor(R.color.button_not_pressed));
    }


    private void replaceFragment( Fragment fragment, String tag ){

        FragmentManager fm = getChildFragmentManager();

        Fragment fragmentFound = fm.findFragmentByTag(tag);
        if (fragmentFound != null){
            Log.d(TAG, "Fragment Was found:" + fragmentFound);
            fragment = fragmentFound;
        }


        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment, tag )
                .commit();

    }
}
