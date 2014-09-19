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
public class SpotsFragment extends Fragment {

    public static final String TAG = SpotsFragment.class.getSimpleName();
    Fragment spotsMapFragment = new SpotsMapFragment();

    Fragment spotsListFragment = new SpotsListFragment();

    private final static String TAG_MAP = "tag_map";
    private final static String TAG_LIST = "tag_list";




    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_spots, container, false);

//        initializeFragments();

        ViewUtils.setOnClickListener(rootView, R.id.tab_map, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                changeTabsBackgroudColor(
                        ViewUtils.findView(rootView, R.id.tab_map),
                        ViewUtils.findView(rootView, R.id.tab_list)
                );
                replaceFragment(spotsMapFragment, TAG_MAP);
            }
        });

        ViewUtils.setOnClickListener(rootView, R.id.tab_list, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeTabsBackgroudColor(
                        ViewUtils.findView(rootView, R.id.tab_list),
                        ViewUtils.findView(rootView, R.id.tab_map)
                );
                replaceFragment(spotsListFragment, TAG_LIST);
            }
        });

        replaceFragment(spotsMapFragment, TAG_MAP);

        return rootView;
    }

    private void changeTabsBackgroudColor(View activeTab, View nonActiveTAb) {
        activeTab.setBackgroundColor(getResources().getColor(R.color.tab_background_pressed));
        nonActiveTAb.setBackgroundColor(getResources().getColor(R.color.tab_background_not_pressed));
    }

    private void initializeFragments() {
        FragmentManager fm = getChildFragmentManager();
        if (fm.findFragmentByTag(TAG_MAP) == null){
            Fragment f= new SpotsFragment();
            fm.beginTransaction().add(f, TAG_MAP).commit();
        }

        if (fm.findFragmentByTag(TAG_LIST) == null){
            Fragment f = new SpotsListFragment();
            fm.beginTransaction().add(f, TAG_LIST).hide(f).commit();
        }
    }

    private void showMapFragment(){
        FragmentManager fm = getChildFragmentManager();
        SpotsMapFragment mapFragment = (SpotsMapFragment)fm.findFragmentByTag(TAG_MAP);
        SpotsListFragment listFragment = (SpotsListFragment)fm.findFragmentByTag(TAG_LIST);
        showHideFragment(mapFragment, listFragment);
    }

    private void showListFragment(){
        FragmentManager fm = getChildFragmentManager();
        SpotsMapFragment mapFragment = (SpotsMapFragment)fm.findFragmentByTag(TAG_MAP);
        SpotsListFragment listFragment = (SpotsListFragment)fm.findFragmentByTag(TAG_LIST);
        showHideFragment(listFragment, mapFragment);
    }

    private void showHideFragment(Fragment toShow, Fragment toHide){
        FragmentManager fm = getChildFragmentManager();
        fm.beginTransaction()
                .show(toShow)
                .hide(toHide)
                .commit();
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
