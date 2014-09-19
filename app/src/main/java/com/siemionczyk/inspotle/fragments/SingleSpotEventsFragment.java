package com.siemionczyk.inspotle.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.siemionczyk.inspotle.R;

/**
 * Created by michalsiemionczyk on 19/09/14.
 */
public class SingleSpotEventsFragment extends Fragment {

    public static final String TAG = SingleSpotEventsFragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_single_spot_events, container, false);

        Log.d(TAG, "onCreateView");
        return rootView;
    }
}
