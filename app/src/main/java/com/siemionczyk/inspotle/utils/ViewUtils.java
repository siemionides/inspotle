package com.siemionczyk.inspotle.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public final class ViewUtils {

    private ViewUtils() {
    }

    public static void addView(LinearLayout parent, View child){
        parent.addView(child);
    }

    public static <T extends View> T findView(View parent, @IdRes int viewId) {
        return (T) parent.findViewById(viewId);
    }

    public static <T extends View> T findView(Activity activity, @IdRes int viewId) {
        return (T) activity.findViewById(viewId);
    }

    public static void setText(CharSequence text, View parent, @IdRes int viewId) {
        TextView view = findView(parent, viewId);
        view.setText(text);
    }

    public static void setText(CharSequence text, Activity activity, @IdRes int viewId) {
        TextView view = findView(activity, viewId);
        view.setText(text);
    }

    public static void setText(@StringRes int stringRes, Activity activity, @IdRes int viewId) {
        TextView view = findView(activity, viewId);
        view.setText(stringRes);
    }

    public static void setOnClickListener(Activity activity, @IdRes int viewId, View.OnClickListener listener) {
        View view = findView(activity, viewId);
        view.setOnClickListener(listener);
    }

    public static void setOnClickListener(View parent, @IdRes int viewId, View.OnClickListener listener) {
        View view = findView(parent, viewId);
        view.setOnClickListener(listener);
    }

    public static void setVisible(boolean visible, View parent, @IdRes int viewId) {
        View view = findView(parent, viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    public static void setVisible(boolean visible, Activity activity, @IdRes int viewId) {
        View view = findView(activity, viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    public static void setImage(Bitmap bitmap, View parent, @IdRes int viewId) {
        ImageView imageView = findView(parent, viewId);
        imageView.setImageBitmap(bitmap);
    }

    private static void setViewId(View view, @LayoutRes int id){
        view.setId(id);
    }

    public static void setBackground(@DrawableRes int drawableId, View parent, @IdRes int viewId) {
        View view = findView(parent, viewId);
        view.setBackgroundResource(drawableId);
    }

    public static void setImage(@DrawableRes int drawableId, View parent, @IdRes int viewId) {
        ImageView view = findView(parent, viewId);
        view.setImageResource(drawableId);
    }

    public static void setSelected(Activity activity, @IdRes int viewId) {
        View view = findView(activity, viewId);
        view.setSelected(true);
    }

    public static void setNotSelected(Activity activity, @IdRes int viewId) {
        View view = findView(activity, viewId);
        view.setSelected(false);
    }

    public static void setTextColor(@ColorRes int colorRes, Activity activity, @IdRes int viewId) {
        TextView view = findView(activity, viewId);
        view.setTextColor(activity.getResources().getColor(colorRes));
    }
}
