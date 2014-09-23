package com.siemionczyk.inspotle.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.siemionczyk.inspotle.R;
import com.siemionczyk.inspotle.model.Activity;
import com.siemionczyk.inspotle.model.Spot;
import com.siemionczyk.inspotle.utils.ViewUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by michalsiemionczyk on 19/09/14.
 */
public class CreateNewGameSpotsAdapter extends BaseAdapter {

    public static final String TAG = CreateNewGameSpotsAdapter.class.getSimpleName();
    List<Spot> spots;
    Context context;

    public CreateNewGameSpotsAdapter(List<Spot> spots, Context ctx) {
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
            convertView = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.create_new_game_spot_item, parent, false);
        }
        return convertView;
    }

    static int  id = 45435433;


    private void bind(View view, Spot spot) {
        ViewUtils.setText(spot.getName(), view, R.id.tv_spot_name);
        ViewUtils.setText(spot.getLatitude() + " " + spot.getLongitude(), view, R.id.tv_distance_from);
        ViewUtils.setText(getNrEvents(spot.getEvents().size()), view, R.id.tv_events_nr);


        LinearLayout photosLayout = ViewUtils.findView(view, R.id.layout_photos_activities);

        Log.i(TAG, "name: " + spot.getName() + ", activieis:" + spot.getActivities().size());

        for (Activity activity : spot.getActivities()) {
            ImageView iv = new ImageView(context);
            LinearLayout.LayoutParams params =
                    new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(15, 0, 15, 0);
            iv.setLayoutParams(params);


//            iv.setId(id);

            ViewUtils.addView(photosLayout, iv);
            Picasso.with(context).load(activity.getIcon_blue_url()).into(iv);
            id++;
        }
    }


    private void addPhotos() {


//        ImageView iv = new ImageView( getActivity());
//        LinearLayout.LayoutParams params =
//                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        params.setMargins( 15, 0, 15, 0 );
//
//
//        iv.setLayoutParams( params);
//        iv.setId(id );
//
//
//
//
//        photosLayout.addView( iv);
//
//        aq.id( id).image( OtwarteZabytkiClient.HOST + pJ.file.midi.url);
    }


    private String getNrEvents(int nrEvents) {
        Resources res = this.context.getResources();
        return res.getQuantityString(R.plurals.numberOfEvents, nrEvents, nrEvents);
    }
}
