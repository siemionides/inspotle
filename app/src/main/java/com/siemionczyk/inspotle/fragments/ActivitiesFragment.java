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

            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            LinearLayout cellItem;

            if (convertView == null) {

                // get layout from text_item.xml
                cellItem = (LinearLayout) inflater.inflate(R.layout.text_item, null);

                int parentHeight = parent.getHeight();
                cellItem.setLayoutParams(new GridView.LayoutParams(GridView.LayoutParams.MATCH_PARENT, parentHeight / 3));

                // set value into textview
                TextView textView = (TextView) cellItem
                        .findViewById(R.id.grid_item_label);
                textView.setText(mobileValues[position]);

            } else {
                cellItem = (LinearLayout) convertView;
            }

            return cellItem;
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


}
