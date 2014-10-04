package com.siemionczyk.inspotle.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.siemionczyk.inspotle.R;
import com.siemionczyk.inspotle.activities.SingleSpotActivity;
import com.siemionczyk.inspotle.adapters.SpotsAdapter;
import com.siemionczyk.inspotle.api.InspotleApiClient;
import com.siemionczyk.inspotle.events.SpotsResponseEvent;
import com.siemionczyk.inspotle.model.Spot;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by michalsiemionczyk on 18/09/14.
 */
public class SpotsListFragment extends Fragment {


    public static final String TAG = SpotsListFragment.class.getSimpleName();
    ListView spotList;

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_spots_list, container, false);

        Log.d(TAG, "onCreateView");

        InspotleApiClient.getInstance().getSpots();

//        if (spotList == null) {
            spotList = (ListView) rootView.findViewById(R.id.listview_spots);
//        }

        return rootView;
    }

    @SuppressWarnings("unused")
    public void onEvent(SpotsResponseEvent event) {
        Log.d(TAG, "onEvent SpotResponse:" + event.getSpots().size());
        populateList(event.getSpots());
    }

    private void populateList(List<Spot> spots) {
        spotList.setAdapter(new SpotsAdapter(spots, getActivity()));
        spotList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Spot spot = (Spot) parent.getItemAtPosition(position);
//                Toast.makeText(getActivity(), "Clicked on:" + spot.getName(), Toast.LENGTH_SHORT).show();
                launchSingleSpotActivity(spot);
            }
        });
    }

    private void launchSingleSpotActivity(Spot spot){
        Intent intent = new Intent(getActivity(), SingleSpotActivity.class);
        intent.putExtra(SingleSpotActivity.BUNDLE_SPOT_KEY, spot);
        startActivity(intent);
    }


}
