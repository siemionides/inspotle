package com.siemionczyk.inspotle.utils;

import android.animation.ObjectAnimator;
import android.os.Build;
import android.widget.Button;
import android.widget.RelativeLayout;

public class AnimationUtils {

    public static void showButtonNext(Button buttonNextLayout) {
        // hack for now - for apis youger than
        //depending on the API, choose relevant version
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion < Build.VERSION_CODES.HONEYCOMB) {
            //just "show" the button from bottom

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);

            params.bottomMargin = 0;
            params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

            buttonNextLayout.setLayoutParams(params);

        } else {
            int nextButtonHeight = buttonNextLayout.getHeight() - 1;
            ObjectAnimator animation = ObjectAnimator.ofFloat(buttonNextLayout, "translationY", -nextButtonHeight);
            animation.start();
        }

//        buttonNextLayout.setOnClickListener( listener );
    }
}
