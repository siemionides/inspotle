package com.siemionczyk.inspotle;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;

import com.siemionczyk.inspotle.fragments.CreateNewGameListFragment;
import com.siemionczyk.inspotle.fragments.CreateNewGameMapFragment;
import com.siemionczyk.inspotle.utils.ViewUtils;

/**
 * Created by michalsiemionczyk on 19/09/14.
 */
public class CreateNewGameActivity extends FragmentActivity {


    private final static String TAG = CreateNewGameActivity.class.getSimpleName();


    Fragment mapFragment = new CreateNewGameMapFragment();

    Fragment listFragment = new CreateNewGameListFragment();

    private final static String TAG_MAP = "tag_map";
    private final static String TAG_LIST = "tag_list";

    public static Intent newIntent(Context ctx){
        return new Intent(ctx, CreateNewGameActivity.class);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_game);


        ViewUtils.setOnClickListener(this, R.id.tab_map, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                changeTabsBackgroudColor(
                        ViewUtils.findView(CreateNewGameActivity.this, R.id.tab_map),
                        ViewUtils.findView(CreateNewGameActivity.this, R.id.tab_list)
                );
                replaceFragment(mapFragment, TAG_MAP);
            }
        });

        ViewUtils.setOnClickListener(this, R.id.tab_list, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeTabsBackgroudColor(
                        ViewUtils.findView(CreateNewGameActivity.this, R.id.tab_list),
                        ViewUtils.findView(CreateNewGameActivity.this, R.id.tab_map)
                );
                replaceFragment(listFragment, TAG_LIST);
            }
        });

        replaceFragment(mapFragment, TAG_MAP);
    }




    private void changeTabsBackgroudColor(View activeTab, View nonActiveTAb) {
        activeTab.setBackgroundColor(getResources().getColor(R.color.button_pressed));
        nonActiveTAb.setBackgroundColor(getResources().getColor(R.color.button_not_pressed));
    }

    private void replaceFragment(Fragment fragment, String tag) {

        FragmentManager fm = getSupportFragmentManager();

        Fragment fragmentFound = fm.findFragmentByTag(tag);
        if (fragmentFound != null) {
            Log.d(TAG, "Fragment Was found:" + fragmentFound);
            fragment = fragmentFound;
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_create_new_game_container, fragment, tag)
                .commit();
    }

}
