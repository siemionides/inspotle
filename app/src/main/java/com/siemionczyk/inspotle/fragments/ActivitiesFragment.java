package com.siemionczyk.inspotle.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.siemionczyk.inspotle.activities.CreateNewGameActivity;
import com.siemionczyk.inspotle.R;
import com.siemionczyk.inspotle.api.InspotleApiClient;
import com.siemionczyk.inspotle.events.ActivitiesResponseEvent;
import com.siemionczyk.inspotle.model.Activity;
import com.siemionczyk.inspotle.utils.ViewUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by michalsiemionczyk on 18/09/14.
 */
public class ActivitiesFragment extends Fragment {

//    static final String[] numbers = new String[]{
//            "Koszykówka", "Siatkówka", "Badmington",
//            "Piłka nożna", "Tenis", "Dwa ognie",
//            "Hokej", "Karate", "Taekwondo",
//            "Hokej", "Karate", "Taekwondo"
//    };

    GridView gridView;

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
        View rootView = inflater.inflate(R.layout.fragment_activities, container, false);

        initializeViews(rootView);

        InspotleApiClient.getInstance().getActivities();

        return rootView;
    }


    private void initializeViews(View rootView) {

        if (gridView == null) {
            gridView = (GridView) rootView.findViewById(R.id.gridView1);
        }

        ViewUtils.setOnClickListener(rootView, R.id.button_create_new_game, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchCreateNewGameActivity();
            }
        });
    }

    private void launchCreateNewGameActivity() {
        startActivity(CreateNewGameActivity.newIntent(getActivity()));
    }


    @SuppressWarnings("unused")
    public void onEvent(ActivitiesResponseEvent event) {
        populateGrid(event.getActivities());
    }

    private void populateGrid(final List<Activity> activities) {
        gridView.setAdapter(new MyGridAdapter(getActivity(), activities));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(getActivity(),
                        activities.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public class MyGridAdapter extends BaseAdapter {

        private Context context;
        private List<Activity> activities;

        public MyGridAdapter(Context context, List<Activity> activities) {
            this.context = context;
            this.activities = activities;
        }


        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolderItem viewHolder;
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (convertView == null) {

                // get layout from text_item.xml
                convertView = inflater.inflate(R.layout.text_item, parent, false);
                convertView.setLayoutParams(new GridView.LayoutParams(GridView.LayoutParams.MATCH_PARENT, getItemHeight()));

                viewHolder = new ViewHolderItem();
                viewHolder.textViewItem = (TextView) convertView
                        .findViewById(R.id.grid_item_label);

                viewHolder.imageView = (ImageView) convertView.findViewById(R.id.iv_icon);

                convertView.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolderItem) convertView.getTag();
            }

            bindViews(activities.get(position), viewHolder);
            return convertView;
        }

        private void bindViews(Activity activity, ViewHolderItem viewHolder) {
            String activityName = activity.getName().toUpperCase();

            viewHolder.textViewItem.setText(activityName);
            Picasso.with(context).load(activity.getIconNonPressed()).into(viewHolder.imageView);
        }


        private int getItemHeight() {
            final float scale = context.getResources().getDisplayMetrics().density;
            int dps = (int) context.getResources().getDimension(R.dimen.activity_cell_height);
            int pixels = (int) (dps * scale + 0.5f);
            return pixels;
        }

        @Override
        public int getCount() {
            return activities.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }
    }

    static class ViewHolderItem {
        TextView textViewItem;
        ImageView imageView;
    }


}
