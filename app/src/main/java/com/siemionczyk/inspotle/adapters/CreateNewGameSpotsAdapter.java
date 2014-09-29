package com.siemionczyk.inspotle.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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

        Viewholder viewholder = null;
        Spot spot = getItem(position);

        if (convertView == null) {
            convertView = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.create_new_game_spot_item, parent, false);
            viewholder = createAndConnectViewholder(convertView, spot);
        } else {
            viewholder = (Viewholder) convertView.getTag();
        }

        bind(viewholder, spot);
        return convertView;
    }

    private Viewholder createAndConnectViewholder(View convertView, Spot spot) {
        Viewholder viewholder;
        viewholder = new Viewholder();
        viewholder.photosLayout = ViewUtils.findView(convertView, R.id.layout_photos_activities);
        viewholder.spotName = ViewUtils.findView(convertView, R.id.tv_spot_name);
        viewholder.tvDistanceFrom = ViewUtils.findView(convertView, R.id.tv_distance_from);
        viewholder.tvEventsNr = ViewUtils.findView(convertView, R.id.tv_events_nr);
        viewholder.ivPhotos = getInitializedArray(context, spot.getActivities().size());
        convertView.setTag(viewholder);
        return viewholder;
    }

    private void injectImageViewsToLayout(LinearLayout photosLayout, ImageView[] ivPhotos) {
        for (ImageView imageView : ivPhotos) {
            photosLayout.addView(imageView);
        }
    }



    private ImageView[] getInitializedArray(Context ctx, int nrImages) {
        ImageView[] ivArray = new ImageView[nrImages];
        for (int i = 0; i < ivArray.length; i++) {
            ivArray[i] = new ImageView(ctx);
        }
        return ivArray;
    }

    private void bind(Viewholder viewHolder, Spot spot) {
        viewHolder.tvEventsNr.setText("" + spot.getEvents().size());
        viewHolder.tvDistanceFrom.setText(spot.getLatitude() + " " + spot.getLongitude());
        viewHolder.spotName.setText(spot.getName());

        Log.i(TAG, "name: " + spot.getName() + ", activieis:" + spot.getActivities().size());

        viewHolder.photosLayout.removeAllViews();
        for (int i = 0; i < spot.getActivities().size(); i++) {
            ImageView iv = new ImageView(context);
            LinearLayout.LayoutParams params =
                    new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(15, 0, 15, 0);
            iv.setLayoutParams(params);

            ViewUtils.addView(viewHolder.photosLayout, iv);
            Activity activity = spot.getActivities().get(i);
            Picasso.with(context).load(activity.getIcon_blue_url()).into(iv);
        }
    }

    private static class Viewholder {
        TextView spotName;  //tv_spot_name
        TextView tvDistanceFrom; //tv_distance_from
        TextView tvEventsNr; //tv_events_nr
        ImageView[] ivPhotos;
        LinearLayout photosLayout; //layout_photos_activities
    }
}
