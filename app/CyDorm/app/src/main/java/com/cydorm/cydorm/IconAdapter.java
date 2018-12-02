package com.cydorm.cydorm;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * An icon adapter for the home screen
 * @author Malcolm Boyd
 */
public class IconAdapter extends BaseAdapter {
    private Context mContext;
    private IconMapping mapper;

    /** Init a new icon adapter
     * @param Context c Android context
     * @param IconMapping IconMapping to activity
     */
    public IconAdapter(Context c, IconMapping mapper) {
        mContext = c;
        this.mapper = mapper;
    }

    /**
     * @return int Count of the icons
     */
    public int getCount() {
        return this.mapper.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    /** Create a new ImageView for each item referenced by the Adapter.
     *
     * @param int position Position of the icon
     * @param View convertView The view to set to the returned view
     * @param ViewGroup The group of views
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(300, 300));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(this.mapper.getDrawable(position));
        return imageView;
    }
}
