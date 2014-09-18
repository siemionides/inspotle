package com.siemionczyk.inspotle.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.siemionczyk.inspotle.R;
import com.siemionczyk.inspotle.utils.ViewUtils;

/**
 * Created by michalsiemionczyk on 18/09/14.
 */
public class SpotsFragment extends Fragment {

    Fragment spotsMapFragment = new SpotsMapFragment();

    Fragment spotsListFragment = new SpotsListFragment();


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_spots, container, false);

        ViewUtils.setOnClickListener(rootView, R.id.tab_map, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(spotsMapFragment);
            }
        });

        ViewUtils.setOnClickListener(rootView, R.id.tab_list, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(spotsListFragment);
            }
        });

        return rootView;
    }


    private void replaceFragment(Fragment fragment){
        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();

    }
}
