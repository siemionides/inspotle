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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.siemionczyk.inspotle.R;

/**
 * Created by michalsiemionczyk on 18/09/14.
 */
public class ActivitiesFragment extends Fragment {

    static final String[] numbers = new String[]{
            "Koszykówka", "Siatkówka", "Badmington",
            "Piłka nożna", "Tenis", "Dwa ognie",
            "Hokej", "Karate", "Taekwondo",
            "Hokej", "Karate", "Taekwondo"
    };

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_activities, container, false);

        initializeGrid(rootView);

        return rootView;
    }

    private void initializeGrid(View rootView) {
        GridView gridView = (GridView) rootView.findViewById(R.id.gridView1);
        gridView.setAdapter(new MyGridAdapter(getActivity(), numbers));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                Toast.makeText(getActivity(),
                        numbers[position], Toast.LENGTH_SHORT).show();
            }
        });

    }

    public class MyGridAdapter extends BaseAdapter {

        private Context context;
        private String[] mobileValues;

        public MyGridAdapter(Context context, String[] arrayEmpty) {
            this.context = context;
            this.mobileValues = arrayEmpty;
        }


        public View getView(int position, View convertView, ViewGroup parent) {

            if (position == 0) {
                int a = 5;
            }
            ViewHolderItem viewHolder;
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (convertView == null) {

                // get layout from text_item.xml
                convertView = inflater.inflate(R.layout.text_item, parent, false);
//                int parentHeight = parent.getHeight();

                convertView.setLayoutParams(new GridView.LayoutParams(GridView.LayoutParams.MATCH_PARENT, getItemHeight()));

                viewHolder = new ViewHolderItem();
                viewHolder.textViewItem = (TextView) convertView
                        .findViewById(R.id.grid_item_label);

                convertView.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolderItem) convertView.getTag();
            }
            // set value into textview
            viewHolder.textViewItem.setText(mobileValues[position]);

            return convertView;
        }


        private int getItemHeight() {
            final float scale = context.getResources().getDisplayMetrics().density;
            int dps = (int) context.getResources().getDimension(R.dimen.activity_cell_height);
            int pixels = (int) (dps * scale + 0.5f);
            return pixels;
        }

        @Override
        public int getCount() {
            return mobileValues.length;
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
        LinearLayout linearLayout;

    }


}
