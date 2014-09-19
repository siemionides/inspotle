package com.siemionczyk.inspotle.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.siemionczyk.inspotle.R;
import com.siemionczyk.inspotle.model.Spot;
import com.siemionczyk.inspotle.utils.ViewUtils;

import java.util.List;

/**
 * Created by michalsiemionczyk on 19/09/14.
 */
public class SpotsAdapter extends BaseAdapter {

    List<Spot> spots;
    Context context;

    public SpotsAdapter(List<Spot> spots, Context ctx){
        this.spots = spots;
        this.context = ctx;
    }


    @Override
    public int getCount() {
        return spots.size();
    }

    @Override
    public Spot getItem(int position) {
        return spots.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = createIfNeeded(convertView, parent);
        bind(convertView, getItem(position));
        return convertView;
    }

    private View createIfNeeded(View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.spot_item, parent, false);
        }
        return convertView;
    }

    private void bind(View view, Spot spot) {
        ViewUtils.setText(spot.getName(), view, R.id.tv_spot_name);
        ViewUtils.setText(spot.getLatitude() + " " + spot.getLongitude(), view, R.id.tv_distance_from);
        ViewUtils.setText(getNrEvents(spot.getEvents().size()), view, R.id.tv_events_nr);
    }


    private String getNrEvents(int nrEvents){
        Resources res = this.context.getResources();
        return res.getQuantityString(R.plurals.numberOfEvents, nrEvents, nrEvents);
    }
}
