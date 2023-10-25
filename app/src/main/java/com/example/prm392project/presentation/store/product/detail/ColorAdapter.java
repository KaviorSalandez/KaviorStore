package com.example.prm392project.presentation.store.product.detail;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.prm392project.R;

public class ColorAdapter extends BaseAdapter {
    Context context;
    int[] colors = {R.color.black, R.color.blue, R.color.bluer, R.color.dark_orange, R.color.green, R.color.green_alternative, R.color.grey, R.color.light_black, R.color.light_orange};

    public ColorAdapter() {
    }

    public ColorAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return colors.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView;

        if (view == null) {
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        } else {
            imageView = (ImageView) view;
        }
        imageView.setImageResource(colors[i]);
        return imageView;
    }
}
